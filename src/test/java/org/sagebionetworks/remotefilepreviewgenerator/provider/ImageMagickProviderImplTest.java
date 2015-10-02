package org.sagebionetworks.remotefilepreviewgenerator.provider;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import org.jdom.JDOMException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.sagebionetworks.remotefilepreviewgenerator.config.ConfigurationImpl;

public class ImageMagickProviderImplTest {

	ConfigurationImpl config;
	ImageMagickProviderImpl provider;
	String exePath;
	
	public ImageMagickProviderImplTest() {
	}
	
	@Before
	public void setUp() throws IOException, JDOMException {
		URL url = this.getClass().getResource("imagemagickExec");
		String fPath = "";
		fPath = fPath.substring(0, fPath.lastIndexOf("/"));
		Properties props = System.getProperties();
		props.put("org.sagebionetworks.remote.filepreview.generator.imagemagick.path", fPath);
		props.put("org.sagebionetworks.remote.filepreview.generator.imagemagick.name", "imagemagickExec");
		config = new ConfigurationImpl();
		provider = new ImageMagickProviderImpl(config);
	}
	
	@After
	public void tearDown() {
	}
	
	@Test
	public void testInfo() {
		provider.info();
	}
	
	@Test(expected=UnsupportedOperationException.class)
	public void testStart() {
		provider.start();
	}
	
	@Test(expected=UnsupportedOperationException.class)
	public void testReStart() {
		provider.restart();
	}
	
	@Test(expected=UnsupportedOperationException.class)
	public void testStop() {
		provider.stop();
	}
	
	@Test(expected=UnsupportedOperationException.class)
	public void testStatus() {
		provider.status();
	}
	
	@Test
	public void testIsInstalledInstalled() {
		assertTrue(provider.isInstalled());
	}
	
	@Test
	public void testExecConvert() {
	}
}
