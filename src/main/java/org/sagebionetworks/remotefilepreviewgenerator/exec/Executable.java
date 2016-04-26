package org.sagebionetworks.remotefilepreviewgenerator.exec;

import java.io.IOException;
import java.util.List;

public interface Executable {
	public boolean isInstalled();
	public int run(List<String> args, Long timeoutMs) throws Exception;
	public void kill();
}
