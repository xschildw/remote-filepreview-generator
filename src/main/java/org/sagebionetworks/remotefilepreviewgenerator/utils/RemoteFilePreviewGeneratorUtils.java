package org.sagebionetworks.remotefilepreviewgenerator.utils;

import com.amazonaws.services.s3.model.ObjectMetadata;
import java.io.BufferedReader;
import java.io.IOException;
import org.sagebionetworks.repo.model.file.RemoteFilePreviewGenerationRequest;
import org.sagebionetworks.repo.model.file.S3FileHandle;
import org.sagebionetworks.schema.adapter.JSONObjectAdapter;
import org.sagebionetworks.schema.adapter.JSONObjectAdapterException;
import org.sagebionetworks.schema.adapter.org.json.JSONObjectAdapterImpl;

public class RemoteFilePreviewGeneratorUtils {
	
	public static RemoteFilePreviewGenerationRequest getGenerationRequestFromHttpRequest(BufferedReader r) throws IOException, JSONObjectAdapterException {
		StringBuilder sb = new StringBuilder();
		try {
			String l;
			while ((l = r.readLine()) != null)
				sb.append(l).append('\n');
		} finally {
			r.close();
		}
		String s = sb.toString();
		JSONObjectAdapter joa = (new JSONObjectAdapterImpl()).createNew(s);
		RemoteFilePreviewGenerationRequest rfpgReq = new RemoteFilePreviewGenerationRequest();
		rfpgReq.initializeFromJSONObject(joa);
		return rfpgReq;
	}
	
	public static boolean validateSourceS3FileHandle(S3FileHandle h) {
		if (h == null) {
			throw new IllegalArgumentException("Source S3 file handle cannot be null.");
		}
		if ((h.getBucketName() == null) || (h.getKey() == null) || (h.getFileName() == null)) {
			throw new IllegalArgumentException("BucketName, Key and FileName cannot be null in source S3 file handle.");
		}
		//	Should we also double-check content-type etc.?
		return true;
	}
	
	public static boolean validateDestinationS3FileHandle(S3FileHandle h) {
		if (h == null) {
			throw new IllegalArgumentException("Destination S3 file handle cannot be null.");
		}
		//	Need at least these to create an S3 object
		if ((h.getBucketName() == null) || (h.getKey() == null) || (h.getFileName() == null)) {
			throw new IllegalArgumentException("BucketName, Key and FileName cannot be null in destination S3 file handle.");
		}
		return true;
	}
}
