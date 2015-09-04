package org.sagebionetworks.remotefilepreviewgenerator.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.sagebionetworks.repo.model.file.RemoteFilePreviewGenerationRequest;

public class FilePreviewGeneratorServiceImplTest {
	
	public FilePreviewGeneratorServiceImplTest() {
	}
	
	@Before
	public void setUp() {
	}
	
	@After
	public void tearDown() {
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testGenerateFilePreviewNullReq() {
		FilePreviewGeneratorService svc = new FilePreviewGeneratorServiceImpl();
		svc.generateFilePreview(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testGenerateFilePreviewNullSourceAndDest() {
		RemoteFilePreviewGenerationRequest req = new RemoteFilePreviewGenerationRequest();
		FilePreviewGeneratorService svc = new FilePreviewGeneratorServiceImpl();
		svc.generateFilePreview(req);
	}

}
