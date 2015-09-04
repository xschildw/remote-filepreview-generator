package org.sagebionetworks.remotefilepreviewgenerator.service;

import org.json.JSONObject;
import org.sagebionetworks.repo.model.file.RemoteFilePreviewGenerationRequest;
import org.sagebionetworks.repo.model.file.S3FileHandle;

public interface FilePreviewGeneratorService {

	public void generateFilePreview(RemoteFilePreviewGenerationRequest req);
	
}
