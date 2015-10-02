package org.sagebionetworks.remotefilepreviewgenerator.manager;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.common.io.Files;
import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sagebionetworks.remotefilepreviewgenerator.dao.ImageMagickDao;
import org.sagebionetworks.remotefilepreviewgenerator.dao.OpenOfficeDao;
import org.sagebionetworks.remotefilepreviewgenerator.utils.RemoteFilePreviewGeneratorUtils;
import org.sagebionetworks.repo.model.file.S3FileHandle;

//import org.sagebionetworks.remotefilepreviewgenerator.dao.ImageMagickDao;
//import org.sagebionetworks.remotefilepreviewgenerator.dao.OpenOfficeDao;

@Singleton
public class FilePreviewGeneratorManagerImpl implements FilePreviewGeneratorManager {

	private static final Logger log = LogManager.getLogger(FilePreviewGeneratorManagerImpl.class);
		
	@Inject
	public FilePreviewGeneratorManagerImpl(AmazonS3Client client,
		ImageMagickDao imDao, OpenOfficeDao ooDao) {
		this.amznS3Client = client;
		
	}
	
	private AmazonS3Client amznS3Client;
	public void setFilePreviewGeneratorManagerImpl(AmazonS3Client client) {
		this.amznS3Client = client;
	}
	
	private ImageMagickDao imageMagickDao;
	public void setImageMagickDao(ImageMagickDao imDao) {
		this.imageMagickDao = imDao;
	}
	
	private OpenOfficeDao openOfficeDao;
	public void setOpenOfficeDao(OpenOfficeDao ooDao) {
		this.openOfficeDao = ooDao;
	}
	
	private ObjectMetadata generateFilePreview(String srcBucketName, String srcKey, String destBucketName, String destKey) {
		log.debug("In FilePreviewGeneratorManager.generateFilePreview().");
		// Copy source file from  S3
		amznS3Client.copyObject(srcBucketName, srcKey, destBucketName, destKey);
		ObjectMetadata omd = amznS3Client.getObjectMetadata(destBucketName, destKey);
		return omd;
	}

	@Override
	public S3FileHandle generateFilePreview(S3FileHandle src, S3FileHandle dest) {
		log.debug("In FilePreviewGeneratorManager.generateFilePreview().");
		RemoteFilePreviewGeneratorUtils.validateSourceS3FileHandle(src);
		RemoteFilePreviewGeneratorUtils.validateDestinationS3FileHandle(dest);
		//	For now just copy the object
		ObjectMetadata o = generateFilePreview(src.getBucketName(), src.getKey(), dest.getBucketName(), dest.getKey());
		dest = RemoteFilePreviewGeneratorUtils.initS3FileHandle(o, dest);
		return dest;
	}
	
}
