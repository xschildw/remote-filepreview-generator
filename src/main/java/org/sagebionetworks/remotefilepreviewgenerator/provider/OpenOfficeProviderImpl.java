package org.sagebionetworks.remotefilepreviewgenerator.provider;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.io.File;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sagebionetworks.remotefilepreviewgenerator.config.Configuration;

@Singleton
public class OpenOfficeProviderImpl implements BackendService {
	
	private static final Logger log = LogManager.getLogger(OpenOfficeProviderImpl.class);
	
	@Inject
	public OpenOfficeProviderImpl(Configuration cfg) {
		this.setConfiguration(cfg);
	}
	
	private Configuration config;
	public void setConfiguration(Configuration cfg) {
		log.debug("OpenOfficeProviderImpl.setConfiguration().");
		this.config = cfg;
	}

	@Override
	public void start() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void stop() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void restart() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void status() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public boolean isInstalled() {
		String path = config.getProperty("org.sagebionetworks.remote.filepreview.generator.execpath.openoffice") + "/"
			+ config.getProperty("org.sagebionetworks.remote.filepreview.generator.execname.openoffice");
		File f = new File(path);
		return f.exists();	}

}
