package org.sagebionetworks.remotefilepreviewgenerator.service;

import org.json.JSONObject;

public interface FilePreviewGeneratorService {

	public void generateFilePreview(String sourceBucketName, String sourceKey, String destinationBucketName, String destinationKey);

	public void generateFilePreview(JSONObject body);
	
}
