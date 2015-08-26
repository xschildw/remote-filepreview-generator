package org.sagebionetworks.remotefilepreviewgenerator.dao;

import com.google.inject.AbstractModule;

public class OpenOfficeDaoModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(OpenOfficeDao.class).to(OpenOfficeDaoImpl.class);
	}
	
}
