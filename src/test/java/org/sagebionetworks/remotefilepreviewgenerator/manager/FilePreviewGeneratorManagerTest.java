package org.sagebionetworks.remotefilepreviewgenerator.manager;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;

import org.mockito.Mockito;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.sagebionetworks.remotefilepreviewgenerator.dao.ImageMagickDao;
import org.sagebionetworks.remotefilepreviewgenerator.dao.OpenOfficeDao;

import org.sagebionetworks.repo.model.file.S3FileHandle;

public class FilePreviewGeneratorManagerTest {
	
	private FilePreviewGeneratorManagerImpl mgr;
	private AmazonS3Client mockS3Client;
	private ImageMagickDao mockImageMagickDao;
	private OpenOfficeDao mockOpenOfficeDao;
	
	public FilePreviewGeneratorManagerTest() {
	}
	
	@Before
	public void setUp() {
		mockS3Client = Mockito.mock(AmazonS3Client.class);
		mockImageMagickDao = Mockito.mock(ImageMagickDao.class);
		mockOpenOfficeDao = Mockito.mock(OpenOfficeDao.class);
		mgr = new FilePreviewGeneratorManagerImpl(mockS3Client, mockImageMagickDao, mockOpenOfficeDao);
	}
	
	@After
	public void tearDown() {
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testGenerateFilePreviewNullSourceAndDest() {
		mgr.generateFilePreview(null, null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testGenerateFilePreviewInvalidSource() {
		S3FileHandle src = new S3FileHandle();
		src.setBucketName("SRC");
		src.setFileName("file");
		S3FileHandle dest = new S3FileHandle();
		dest.setBucketName("DEST");
		dest.setKey("key");
		dest.setFileName("file");
		mgr.generateFilePreview(src, dest);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testGenerateFilePreviewInvalidDestination() {
		S3FileHandle src = new S3FileHandle();
		src.setBucketName("SRC");
		src.setKey("src");
		src.setFileName("file");
		S3FileHandle dest = new S3FileHandle();
		dest.setKey("key");
		dest.setFileName("file");
		mgr.generateFilePreview(src, dest);
	}
	
	/**
	 * Temp test, no need for complicated checks
	 * TODO:	Move no-op to DAOs and check all the way through
	 */
	@Test
	public void testGenerateFilePreview() {
		S3FileHandle src = new S3FileHandle();
		src.setBucketName("SRC");
		src.setKey("src");
		src.setFileName("file");
		S3FileHandle dest = new S3FileHandle();
		dest.setBucketName("DEST");
		dest.setKey("dest");
		dest.setFileName("file");
		ObjectMetadata expectedOmd = new ObjectMetadata();
		expectedOmd.setContentDisposition("file");
		when(mockS3Client.getObjectMetadata(anyString(), anyString())).thenReturn(expectedOmd);
		mgr.generateFilePreview(src, dest);
		verify(mockS3Client).copyObject(any(String.class),any(String.class),any(String.class),any(String.class));
	}
}
