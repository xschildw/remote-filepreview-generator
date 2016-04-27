package org.sagebionetworks.remotefilepreviewgenerator.exec;

import java.io.File;
import java.util.List;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteResultHandler;
import org.apache.commons.exec.ExecuteWatchdog;
import org.apache.commons.exec.Executor;
import org.apache.logging.log4j.LogManager;

public class ExecutableImpl implements Executable {
	private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(ExecutableImpl.class);
	
	private String executablePath;
	
	public ExecutableImpl(String executablePath) {
		this.executablePath = executablePath;
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
	public ExecuteResultHandler run(List<String> args, Long timeoutMs, ExecuteResultHandler handler) throws Exception {
		CommandLine cmdLine = new CommandLine(this.executablePath);
		for (String arg: args) {
			cmdLine.addArgument(arg);
		}

		ExecuteWatchdog watchDog = new ExecuteWatchdog(timeoutMs);
		Executor executor = new DefaultExecutor();
		executor.setExitValue(0);
		executor.setWatchdog(watchDog);
		
		executor.execute(cmdLine, handler);
		
		return handler;
	}

	@Override
	public void kill() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
	
}
