package org.sagebionetworks.remotefilepreviewgenerator.provider;

import com.google.inject.AbstractModule;

public class BackendProviderModule extends AbstractModule {

	@Override
	protected void configure() {
	}
	
	public ImageMagickProviderImpl getImageMagickProvider() {
		return new ImageMagickProviderImpl();
	}
	
	public OpenOfficeProviderImpl getOpenOfficeProvider() {
		return new OpenOfficeProviderImpl();
	}
}
