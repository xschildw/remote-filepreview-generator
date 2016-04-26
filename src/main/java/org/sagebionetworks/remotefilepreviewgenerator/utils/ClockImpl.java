package org.sagebionetworks.remotefilepreviewgenerator.utils;

import com.google.inject.Singleton;
import org.sagebionetworks.common.util.Clock;

@Singleton
public class ClockImpl implements Clock {

	@Override
	public void sleep(long l) throws InterruptedException {
		Thread.sleep(l);
	}

	@Override
	public long currentTimeMillis() {
		return System.currentTimeMillis();
	}
	
}
