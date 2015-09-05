package org.sagebionetworks.remotefilepreviewgenerator.dao;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import org.mockito.Mockito;
import org.sagebionetworks.remotefilepreviewgenerator.provider.BackendService;

public class ImageMagickDaoImplTest {
	
	private ImageMagickDaoImpl imageMagickDao;
	private BackendService mockImageMagickSvc;
	private Path testFilePath;
	private Path testFolderPath;
	private Path testFileExistingOutput;
	
	public ImageMagickDaoImplTest() {
	}
	
	@Before
	public void setUp() throws URISyntaxException {
		mockImageMagickSvc = Mockito.mock(BackendService.class);
		imageMagickDao = new ImageMagickDaoImpl(mockImageMagickSvc);
		URL testFileUrl = this.getClass().getResource("/testFolder/imagemagickdao.txt");
		testFilePath = Paths.get(testFileUrl.toURI());
		URL testFolderUrl = this.getClass().getResource("/testFolder");
		testFolderPath = Paths.get(testFolderUrl.toURI());
		URL testFileExistingOutputUrl = this.getClass().getResource("/testFolder/invalidoutput.txt");
		testFileExistingOutput = Paths.get(testFileExistingOutputUrl.toURI());
	}
	
	@After
	public void tearDown() {
	}

	@Test(expected=IllegalArgumentException.class)
	public void testConvertToPdfNullSourcePath() throws Exception{
		imageMagickDao.convertToPng(null, "somePath");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testConvertToPdfSourcePathNotExists() throws Exception{
		imageMagickDao.convertToPng("somePath", "somePath");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testConvertToPdfSourcePathNotFile() throws Exception{
		imageMagickDao.convertToPng(testFolderPath.toString(), "somePath");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testConvertToPdfNullDestinationPath() throws Exception {
		imageMagickDao.convertToPng(testFilePath.toString(), null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testConvertToPdfDestinationPathExists() throws Exception {
		imageMagickDao.convertToPng(testFilePath.toString(), testFileExistingOutput.toString());
	}
	
	@Test
	public void testConvertToPdf() throws Exception {
		String outPath = testFolderPath+"/imagemagickdao_out.txt";
		imageMagickDao.convertToPng(testFilePath.toString(), outPath);
		File f = new File(outPath);
		assertTrue(f.exists() && f.isFile());
		f.delete();
	}

}
