package org.sagebionetworks.remotefilepreviewgenerator.manager;

import com.amazonaws.services.s3.AmazonS3Client;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.common.io.Files;
import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//import org.sagebionetworks.remotefilepreviewgenerator.dao.ImageMagickDao;
//import org.sagebionetworks.remotefilepreviewgenerator.dao.OpenOfficeDao;

@Singleton
public class FilePreviewGeneratorManagerImpl implements FilePreviewGeneratorManager {

	private static final Logger log = LogManager.getLogger(FilePreviewGeneratorManagerImpl.class);
	
	private AmazonS3Client amznS3Client;
	@Inject
	public void setAmazonS3Client(AmazonS3Client client) {
		this.amznS3Client = client;
	}
	
//	private ImageMagickDao imageMagickDao;
//	@Inject
//	public void setImageMagickDao(ImageMagickDao imDao) {
//		this.imageMagickDao = imDao;
//	}
//	
//	private OpenOfficeDao openOfficeDao;
//	@Inject
//	public void setOpenOfficeDao(OpenOfficeDao ooDao) {
//		this.openOfficeDao = ooDao;
//	}
//	
	@Override
	public void generateFilePreview(String srcBucketName, String srcKey, String destBucketName, String destKey) {
		log.debug("In FilePreviewGeneratorManager.generateFilePreview().");
		//	Download source file from S3
		//	
		
	}
	
}
