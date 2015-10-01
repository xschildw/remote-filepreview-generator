package org.sagebionetworks.remotefilepreviewgenerator.provider;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sagebionetworks.remotefilepreviewgenerator.config.Configuration;

@Singleton
public class ImageMagickProviderImpl implements BackendService {
	
	private static final Logger log = LogManager.getLogger(ImageMagickProviderImpl.class);

	private Configuration config;
	
	@Inject
	public ImageMagickProviderImpl(Configuration cfg) {
		this.setConfiguration(cfg);
	}
	
	public void setConfiguration(Configuration cfg) {
		log.debug("ImageMagickProviderImpl.setConfiguration.");
		this.config = cfg;
	}
	
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
	
}
