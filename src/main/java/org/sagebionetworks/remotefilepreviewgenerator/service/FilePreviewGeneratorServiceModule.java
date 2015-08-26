package org.sagebionetworks.remotefilepreviewgenerator.service;

import com.google.inject.AbstractModule;

public class FilePreviewGeneratorServiceModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(FilePreviewGeneratorService.class).to(FilePreviewGeneratorServiceImpl.class);
	}
	
}
