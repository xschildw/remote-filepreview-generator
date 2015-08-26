package org.sagebionetworks.remotefilepreviewgenerator.dao;

import com.google.inject.AbstractModule;

public class ImageMagickDaoModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(ImageMagickDao.class).to(ImageMagickDaoImpl.class);
	}
	
}
