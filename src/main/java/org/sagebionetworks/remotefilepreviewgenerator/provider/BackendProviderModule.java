		package org.sagebionetworks.remotefilepreviewgenerator.provider;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

public class BackendProviderModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(BackendService.class).to(ImageMagickProviderImpl.class);
		bind(BackendService.class).to(OpenOfficeProviderImpl.class);
		
	}
	
}
