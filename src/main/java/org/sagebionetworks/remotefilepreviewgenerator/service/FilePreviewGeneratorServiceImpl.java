package org.sagebionetworks.remotefilepreviewgenerator.service;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sagebionetworks.remotefilepreviewgenerator.manager.FilePreviewGeneratorManager;

import org.sagebionetworks.repo.model.file.RemoteFilePreviewGenerationRequest;
import org.sagebionetworks.repo.model.file.S3FileHandle;

import org.sagebionetworks.remotefilepreviewgenerator.manager.FilePreviewGeneratorManagerImpl;

@Singleton
public class FilePreviewGeneratorServiceImpl implements FilePreviewGeneratorService {

	private static final Logger log = LogManager.getLogger(FilePreviewGeneratorServiceImpl.class);
	
	private FilePreviewGeneratorManager filePreviewGeneratorMgr;
	
	@Inject
	public FilePreviewGeneratorServiceImpl(FilePreviewGeneratorManager mgr) {
		this.filePreviewGeneratorMgr = mgr;
	}
	
	public void setFilePreviewGeneratorManager(FilePreviewGeneratorManager mgr) {
		this.filePreviewGeneratorMgr = mgr;
	}
	
	private void generateFilePreview(S3FileHandle src, S3FileHandle dest) {
		log.debug("In FilePreviewGeneratorService.generatePreview().");
		if ((src == null) || (dest == null)) {
			throw new IllegalArgumentException("Request source and destination cannot be null.");
		}
		filePreviewGeneratorMgr.generateFilePreview(src, dest);
	}

	@Override
	public void generateFilePreview(RemoteFilePreviewGenerationRequest req) {
		log.debug("In FilePreviewGeneratorService.generatePreview().");
		if (req == null) {
			throw new IllegalArgumentException("Request cannot be null.");
		}
		generateFilePreview(req.getSource(), req.getDestination());
	}
}
