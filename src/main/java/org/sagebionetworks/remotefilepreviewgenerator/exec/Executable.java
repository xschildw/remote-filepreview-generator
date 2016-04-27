package org.sagebionetworks.remotefilepreviewgenerator.exec;

import java.util.List;
import org.apache.commons.exec.ExecuteResultHandler;

public interface Executable {
	public boolean isInstalled();
	public ExecuteResultHandler run(List<String> args, Long timeoutMs, ExecuteResultHandler handler) throws Exception;
	public void kill();
}
