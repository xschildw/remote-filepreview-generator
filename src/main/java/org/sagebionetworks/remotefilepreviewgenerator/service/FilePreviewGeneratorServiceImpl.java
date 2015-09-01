package org.sagebionetworks.remotefilepreviewgenerator.service;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.json.JSONObject;

import org.sagebionetworks.remotefilepreviewgenerator.manager.FilePreviewGeneratorManagerImpl;
import org.sagebionetworks.remotefilepreviewgenerator.utils.MessagePayload;

@Singleton
public class FilePreviewGeneratorServiceImpl implements FilePreviewGeneratorService {

	private static final Logger log = LogManager.getLogger(FilePreviewGeneratorServiceImpl.class);
	
	private FilePreviewGeneratorManagerImpl filePreviewGeneratorMgr;
	@Inject
	public void setFilePreviewGeneratorManager(FilePreviewGeneratorManagerImpl mgr) {
		this.filePreviewGeneratorMgr = mgr;
	}
	
	// TODO: This is where the message coming from the daemon will be upacked
	@Override
	public void generateFilePreview(String sourceBucketName, String sourceKey, String destinationBucketName, String destinationKey) {
		log.debug("In FilePreviewGeneratorService.generatePreview().");
		filePreviewGeneratorMgr.generateFilePreview(sourceBucketName, sourceKey, destinationBucketName, destinationKey);
	}

	@Override
	public void generateFilePreview(JSONObject body) {
		log.debug("In FilePreviewGeneratorService.generatePreview().");
		MessagePayload p = new MessagePayload(body);
		this.generateFilePreview(p.getSourceBucketName(), p.getSourceKey(), p.getDestinationBucketName(), p.getDestinationKey());
	}
}
