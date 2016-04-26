package org.sagebionetworks.remotefilepreviewgenerator.exec;

import com.google.inject.Inject;
import java.io.File;
import java.util.List;

import org.apache.logging.log4j.LogManager;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteWatchdog;
import org.apache.commons.exec.Executor;

import org.sagebionetworks.repo.model.semaphore.MemoryCountingSemaphore;


public class ExecutableImpl implements Executable {
	private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(ExecutableImpl.class);
	
	private MemoryCountingSemaphore sema;
	private String executablePath;
	
	@Inject
	public ExecutableImpl(String path, MemoryCountingSemaphore sema) {
		this.executablePath = path;
		this.sema = sema;
	}
	
	public String getExecutablePath() {
		return this.executablePath;
	}
	
	public void setExecutablePath(String path) {
		this.executablePath = path;
	}

	@Override
	public boolean isInstalled() {
		File f = new File(this.executablePath);
		return ((f.exists()) && (! f.isDirectory()));
	}

	@Override
	public int run(List<String> args, Long timeoutMs) throws Exception {
		logger.debug("Executing run() on " + this.executablePath);
		
		String token = sema.attemptToAcquireLock(executablePath, timeoutMs/1000+1, 1);
		if (token == null) {
			throw new RuntimeException("Could not acquire lock " + executablePath);
		}
		
		CommandLine cmdLine = new CommandLine(this.executablePath);
		for (String arg: args) {
			cmdLine.addArgument(arg);
		}
		DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
		ExecuteWatchdog watchDog = new ExecuteWatchdog(timeoutMs);
		Executor executor = new DefaultExecutor();
		executor.setExitValue(0);
		executor.setWatchdog(watchDog);
		
		executor.execute(cmdLine, resultHandler);
		
		resultHandler.waitFor();

		sema.releaseLock(executablePath, token);
		
		if (resultHandler.getException() != null) {
			throw resultHandler.getException();
		}
		int exitCode = resultHandler.getExitValue();
		return exitCode;
	}

	@Override
	public void kill() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
	
}
