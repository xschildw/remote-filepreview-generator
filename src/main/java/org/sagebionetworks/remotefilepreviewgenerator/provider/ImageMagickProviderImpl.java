package org.sagebionetworks.remotefilepreviewgenerator.provider;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.io.File;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sagebionetworks.remotefilepreviewgenerator.config.Configuration;

@Singleton
public class ImageMagickProviderImpl implements BackendService {
	
	private static final Logger logger = LogManager.getLogger(ImageMagickProviderImpl.class);

	private Configuration config;
	
	@Inject
	public ImageMagickProviderImpl(Configuration cfg) {
		this.setConfiguration(cfg);
	}
	
	public void setConfiguration(Configuration cfg) {
		logger.debug("ImageMagickProviderImpl.setConfiguration.");
		this.config = cfg;
	}
	
	public void start() {
		throw new UnsupportedOperationException("Not supported.");
	}

	@Override
	public void stop() {
		throw new UnsupportedOperationException("Not supported.");
	}

	@Override
	public void restart() {
		this.stop();
		this.start();
	}

	@Override
	public void status() {
		throw new UnsupportedOperationException("Not supported.");
	}
	
	public void info() {
		System.out.println(config.getProperty("org.sagebionetworks.remote.filepreview.generator.execpath.imagemagick"));
	}
	
	@Override
	public boolean isInstalled() {
		String path = config.getProperty("org.sagebionetworks.remote.filepreview.generator.execpath.imagemagick")
			+ config.getProperty("org.sagebionetworks.remote.filepreview.generator.execname.imagemagick");
		File f = new File(path);
		return f.exists();
	}
}
