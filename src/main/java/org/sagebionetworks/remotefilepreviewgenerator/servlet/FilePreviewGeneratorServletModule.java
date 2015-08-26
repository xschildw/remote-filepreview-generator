package org.sagebionetworks.remotefilepreviewgenerator.servlet;

import com.google.inject.servlet.ServletModule;

public class FilePreviewGeneratorServletModule extends ServletModule {
	
	@Override
	protected void configureServlets() {
		super.configureServlets();
		// Bind the path to the preview generation servlet.
		serve("/preview").with(FilePreviewGeneratorServlet.class);
	}
}
