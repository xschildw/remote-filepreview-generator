package org.sagebionetworks.remotefilepreviewgenerator.utils;

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
		//	TODO: Add validation code
		return true;
	}
	
	public static boolean validateDestinationS3FileHandle(S3FileHandle h) {
		//	TODO: Add validation code
		return true;
	}
}
