package org.sagebionetworks.remotefilepreviewgenerator.service;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.sagebionetworks.repo.model.file.RemoteFilePreviewGenerationRequest;
import org.sagebionetworks.repo.model.file.S3FileHandle;

import org.sagebionetworks.remotefilepreviewgenerator.manager.FilePreviewGeneratorManagerImpl;

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
	public void generateFilePreview(S3FileHandle src, S3FileHandle dest) {
		log.debug("In FilePreviewGeneratorService.generatePreview().");
		
		filePreviewGeneratorMgr.generateFilePreview(src, dest);
	}

	@Override
	public void generateFilePreview(RemoteFilePreviewGenerationRequest req) {
		log.debug("In FilePreviewGeneratorService.generatePreview().");
		if ((req.getSource() == null) || (req.getDestination() == null)) {
			throw new IllegalArgumentException("Request source and destination cannot be null.");
		}
		this.generateFilePreview(req.getSource(), req.getDestination());
	}
}
