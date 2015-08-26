package org.sagebionetworks.remotefilepreviewgenerator.service;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
	public void generateFilePreview(String sourceKey, String destinationKey) {
		log.debug("In FilePreviewGeneratorManager.generatePreview().");
		filePreviewGeneratorMgr.generateFilePreview(sourceKey, sourceKey);
	}
}
