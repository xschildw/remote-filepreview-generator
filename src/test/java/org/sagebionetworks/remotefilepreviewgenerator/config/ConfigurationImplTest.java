package org.sagebionetworks.remotefilepreviewgenerator.config;

import java.io.IOException;
import java.util.Properties;
import org.jdom.JDOMException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ConfigurationImplTest {
	
	private ConfigurationImpl config;
	
	public ConfigurationImplTest() {
	}
	
	@Before
	public void setUp() throws IOException, JDOMException {
		Properties props = System.getProperties();
		props.put("org.sagebionetworks.remote.filepreview.generator.execpath.openoffice", "openofficePath");
		config = new ConfigurationImpl();
	}
	
	@After
	public void tearDown() {
		
	}

	@Test
	public void testConfiguration() {
		String s = config.getProperty("org.sagebionetworks.remote.filepreview.generator.execpath.imagemagick");
		assertNotNull(s);
		s = config.getProperty("org.sagebionetworks.remote.filepreview.generator.execpath.openoffice");
		assertNotNull(s);
		assertEquals("openofficePath", s);
	}
}
