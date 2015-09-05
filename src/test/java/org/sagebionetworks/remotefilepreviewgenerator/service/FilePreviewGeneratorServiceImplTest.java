package org.sagebionetworks.remotefilepreviewgenerator.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import org.sagebionetworks.remotefilepreviewgenerator.manager.FilePreviewGeneratorManager;
import org.sagebionetworks.repo.model.file.RemoteFilePreviewGenerationRequest;
import org.sagebionetworks.repo.model.file.S3FileHandle;

public class FilePreviewGeneratorServiceImplTest {
	
	private FilePreviewGeneratorManager mockMgr;
	
	public FilePreviewGeneratorServiceImplTest() {
	}
	
	@Before
	public void setUp() {
		mockMgr = Mockito.mock(FilePreviewGeneratorManager.class);
	}
	
	@After
	public void tearDown() {
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testGenerateFilePreviewNullReq() {
		FilePreviewGeneratorService svc = new FilePreviewGeneratorServiceImpl(mockMgr);
		svc.generateFilePreview(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testGenerateFilePreviewNullSourceAndDest() {
		RemoteFilePreviewGenerationRequest req = new RemoteFilePreviewGenerationRequest();
		FilePreviewGeneratorService svc = new FilePreviewGeneratorServiceImpl(mockMgr);
		svc.generateFilePreview(req);
	}
}
