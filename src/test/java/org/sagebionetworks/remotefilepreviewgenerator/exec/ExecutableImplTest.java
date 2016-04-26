package org.sagebionetworks.remotefilepreviewgenerator.exec;

import org.sagebionetworks.remotefilepreviewgenerator.utils.ClockImpl;
import java.util.LinkedList;
import java.util.List;
import org.apache.commons.exec.ExecuteException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import org.mockito.Mockito;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.when;
import org.sagebionetworks.common.util.Clock;
import org.sagebionetworks.remotefilepreviewgenerator.utils.MemoryCountingSemaphoreImpl;
import org.sagebionetworks.repo.model.semaphore.LockReleaseFailedException;
import org.sagebionetworks.repo.model.semaphore.MemoryCountingSemaphore;

public class ExecutableImplTest {
	
	private static String dummyExecPath;
	private static String javaExecPath;
	private static String sleepPath;
	private Clock clock;
	private MemoryCountingSemaphore sema;
	private MemoryCountingSemaphore mockSema;
	
	public ExecutableImplTest() {
	}
	

	@BeforeClass
	public static void setupClass() {
		dummyExecPath = "./dummyExec";
		String javaHome = System.getProperty("java.home");
		javaExecPath = javaHome + "/bin/java";
		sleepPath = "/bin/sleep";		
	}
	
	@Before
	public void setUp() {
		clock = new ClockImpl();
		sema = new MemoryCountingSemaphoreImpl(clock);
		mockSema = Mockito.mock(MemoryCountingSemaphore.class);
	}
	
	@After
	public void tearDown() {
	}

	@Test
	public void testIsInstalled() throws Exception {
		Executable exec1 = new ExecutableImpl(dummyExecPath, mockSema);
		assertFalse(exec1.isInstalled());
		Executable exec2 = new ExecutableImpl(javaExecPath, mockSema);
		assertTrue(exec2.isInstalled());
	}
	
	@Test
	public void testRunOK() throws Exception {
		when(mockSema.attemptToAcquireLock(eq(sleepPath), anyLong(), anyInt())).thenReturn("token");
		doNothing().when(mockSema).releaseLock(eq(sleepPath), eq("token"));
		Executable exec = new ExecutableImpl(sleepPath, mockSema);
		List<String> args = new LinkedList<>();
		args.add("5");
		int exitCode = exec.run(args, 10000L);
		assertEquals(0, exitCode);
	}
	
	@Test(expected=ExecuteException.class)
	public void testRunError() throws Exception {
		when(mockSema.attemptToAcquireLock(eq(sleepPath), anyLong(), anyInt())).thenReturn("token");
		Executable exec = new ExecutableImpl(sleepPath, mockSema);
		List<String> args = new LinkedList<String>();
		args.add("10");
		int exitCode = exec.run(args, 5000L);
	}
	
	@Test(expected=RuntimeException.class)
	public void testRunCannotAcquireLock() throws Exception {
		when(mockSema.attemptToAcquireLock(eq(sleepPath), anyLong(), anyInt())).thenReturn(null);
		Executable exec = new ExecutableImpl(sleepPath, mockSema);
		List<String> args = new LinkedList<String>();
		args.add("5");
		int exitCode = exec.run(args, 10000L);
	}
	
}
