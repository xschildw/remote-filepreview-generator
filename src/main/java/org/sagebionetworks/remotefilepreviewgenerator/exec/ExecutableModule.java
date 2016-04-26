package org.sagebionetworks.remotefilepreviewgenerator.exec;

import com.google.inject.AbstractModule;

public class ExecutableModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(Executable.class).to(ExecutableImpl.class);
	}
	
}
