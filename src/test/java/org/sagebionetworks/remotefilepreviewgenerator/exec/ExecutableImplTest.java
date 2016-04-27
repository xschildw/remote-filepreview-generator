package org.sagebionetworks.remotefilepreviewgenerator.exec;

import java.util.LinkedList;
import java.util.List;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.ExecuteResultHandler;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

public class ExecutableImplTest {
	
	private static String dummyExecPath;
	private static String javaExecPath;
	private static String sleepPath;
	
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
	}
	
	@After
	public void tearDown() {
	}

	@Test
	public void testIsInstalled() throws Exception {
		Executable exec1 = new ExecutableImpl(dummyExecPath);
		assertFalse(exec1.isInstalled());
		Executable exec2 = new ExecutableImpl(javaExecPath);
		assertTrue(exec2.isInstalled());
	}
	
	// Timeout 2s, exec 1s
	@Test
	public void testRunOK() throws Exception {
		Executable exec = new ExecutableImpl(sleepPath);
		List<String> args = new LinkedList<>();
		args.add("1");
		ExecuteResultHandler handler = new DefaultExecuteResultHandler();
		handler = exec.run(args, 2000L, handler);
		assertTrue(handler instanceof DefaultExecuteResultHandler);
		DefaultExecuteResultHandler h = (DefaultExecuteResultHandler)handler;
		h.waitFor(5000);
		assertNull(h.getException());
		assertEquals(0, h.getExitValue());
	}
	
	// Timeout 1s, exec 2s
	@Test
	public void testRunTooLong() throws Exception {
		Executable exec = new ExecutableImpl(sleepPath);
		List<String> args = new LinkedList<String>();
		args.add("2");
		ExecuteResultHandler handler = new DefaultExecuteResultHandler();
		handler = exec.run(args, 1000L, handler);
		assertTrue(handler instanceof DefaultExecuteResultHandler);
		DefaultExecuteResultHandler h = (DefaultExecuteResultHandler)handler;
		h.waitFor(5000);
		assertNotNull(h.getException());
		assertFalse(0 == h.getExitValue());
	}
	
}
