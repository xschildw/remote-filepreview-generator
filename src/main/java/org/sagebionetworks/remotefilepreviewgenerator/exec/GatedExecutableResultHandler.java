package org.sagebionetworks.remotefilepreviewgenerator.exec;

import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.ExecuteResultHandler;

/**
 * An ExecuteResultHandler that releases the associated lock on exit() or failed()
 */
public class GatedExecutableResultHandler implements ExecuteResultHandler {
	
	private ExecutableImpl executableImpl;
	
	public GatedExecutableResultHandler(ExecutableImpl executableImpl) {
		this.executableImpl = executableImpl;
	}

	@Override
	public void onProcessComplete(int i) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void onProcessFailed(ExecuteException ee) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
	
}
