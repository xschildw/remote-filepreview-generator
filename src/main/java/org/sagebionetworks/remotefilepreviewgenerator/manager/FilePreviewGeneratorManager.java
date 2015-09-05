package org.sagebionetworks.remotefilepreviewgenerator.manager;

import org.sagebionetworks.repo.model.file.S3FileHandle;

public interface FilePreviewGeneratorManager {

	public S3FileHandle generateFilePreview(S3FileHandle src, S3FileHandle dest);
}
