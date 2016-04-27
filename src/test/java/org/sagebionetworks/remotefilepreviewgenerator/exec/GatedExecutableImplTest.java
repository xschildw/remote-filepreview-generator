package org.sagebionetworks.remotefilepreviewgenerator.exec;

import java.util.LinkedList;
import java.util.List;
import org.apache.commons.exec.ExecuteResultHandler;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import org.sagebionetworks.repo.model.semaphore.MemoryCountingSemaphore;

public class GatedExecutableImplTest {
	
	private ExecutableImpl mockExecutable;
	private MemoryCountingSemaphore mockSema;
	GatedExecutableImpl exec;
	
	public GatedExecutableImplTest() {
	}
	
	@Before
	public void setUp() {
		mockExecutable = Mockito.mock(ExecutableImpl.class);
		mockSema = Mockito.mock(MemoryCountingSemaphore.class);
		exec = new GatedExecutableImpl(mockExecutable, mockSema);
	}
	
	@After
	public void tearDown() {
	}
	
	@Test
	public void testCanRun() throws Exception {
		GatedExecutableResultHandler expectedHandler = new GatedExecutableResultHandler(mockExecutable);
		
		when(mockExecutable.run(anyListOf(String.class), anyLong(), any(ExecuteResultHandler.class))).thenReturn(expectedHandler);
		when(mockSema.attemptToAcquireLock(anyString(), anyLong(), anyInt())).thenReturn("expectedToken");
		List<String> args = new LinkedList<String>();
		args.add("1");
		GatedExecutableResultHandler h = exec.run(args, 1000L, expectedHandler);
		verify(mockExecutable).run(anyListOf(String.class), anyLong(), any(ExecuteResultHandler.class));
		assertEquals(expectedHandler, h);
	}

}
