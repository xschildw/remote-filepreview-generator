package org.sagebionetworks.remotefilepreviewgenerator.manager;

import org.sagebionetworks.repo.model.file.S3FileHandle;

public interface FilePreviewGeneratorManager {

	void generateFilePreview(String srcBucketName, String srcKey, String destBucketName, String destKey);
	void generateFilePreview(S3FileHandle src, S3FileHandle dest);
}
