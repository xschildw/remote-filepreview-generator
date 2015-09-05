package org.sagebionetworks.remotefilepreviewgenerator.dao;

import com.google.inject.AbstractModule;

public class DaoModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(OpenOfficeDao.class).to(OpenOfficeDaoImpl.class);
		bind(ImageMagickDao.class).to(ImageMagickDaoImpl.class);
	}
	
}
