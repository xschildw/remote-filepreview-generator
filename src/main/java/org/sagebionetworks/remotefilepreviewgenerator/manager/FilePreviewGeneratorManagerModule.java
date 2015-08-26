package org.sagebionetworks.remotefilepreviewgenerator.manager;

import com.google.inject.AbstractModule;

public class FilePreviewGeneratorManagerModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(FilePreviewGeneratorManager.class).to(FilePreviewGeneratorManagerImpl.class);
	}
	
}
