package org.sagebionetworks.remotefilepreviewgenerator.provider;

public interface BackendService {

	public void start();
	public void stop();
	public void restart();
	public void status();

}
