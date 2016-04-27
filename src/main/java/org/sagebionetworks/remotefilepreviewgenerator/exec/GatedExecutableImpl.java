package org.sagebionetworks.remotefilepreviewgenerator.exec;

import com.google.inject.Inject;
import java.util.List;

import org.apache.logging.log4j.LogManager;


import org.sagebionetworks.repo.model.semaphore.MemoryCountingSemaphore;


public class GatedExecutableImpl {
	private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(GatedExecutableImpl.class);
	
	private MemoryCountingSemaphore sema;
	private ExecutableImpl executableImpl;
	
	@Inject
	public GatedExecutableImpl(ExecutableImpl executableImpl, MemoryCountingSemaphore sema) {
		this.executableImpl = executableImpl;
		this.sema = sema;
	}
	
	public ExecutableImpl getExecutableImpl() {
		return this.executableImpl;
	}
	
	public void setExecutableImpl(ExecutableImpl executableImpl) {
		this.executableImpl = executableImpl;
	}

	public boolean isInstalled() {
		return this.executableImpl.isInstalled();
	}

	public GatedExecutableResultHandler run(List<String> args, Long timeoutMs, GatedExecutableResultHandler handler) throws Exception {
		logger.debug("Executing run() on " + this.executableImpl);
		
		String executablePath = executableImpl.getExecutablePath();
		String token = sema.attemptToAcquireLock(executablePath, timeoutMs/1000+1, 1);
		if (token == null) {
			throw new RuntimeException("Could not acquire lock " + executablePath);
		}
		
		executableImpl.run(args, timeoutMs, handler);
		
		return handler;
	}

	public void kill() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
	
}
