package org.sagebionetworks.remotefilepreviewgenerator.utils;

import com.google.inject.AbstractModule;
import org.sagebionetworks.common.util.Clock;
import org.sagebionetworks.repo.model.semaphore.MemoryCountingSemaphore;

public class UtilsModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(Clock.class).to(ClockImpl.class);
		bind(MemoryCountingSemaphore.class).to(MemoryCountingSemaphoreImpl.class);
	}
	
}
