package org.sagebionetworks.remotefilepreviewgenerator.manager;

public interface FilePreviewGeneratorManager {

	void generateFilePreview(String srcBucketName, String srcKey, String destBucketName, String destKey);
	
}
