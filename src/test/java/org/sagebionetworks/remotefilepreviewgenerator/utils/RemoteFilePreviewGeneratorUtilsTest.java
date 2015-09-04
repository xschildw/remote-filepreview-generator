package org.sagebionetworks.remotefilepreviewgenerator.utils;

import java.io.BufferedReader;
import java.io.StringReader;
import org.json.JSONException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.sagebionetworks.repo.model.file.RemoteFilePreviewGenerationRequest;
import org.sagebionetworks.repo.model.file.S3FileHandle;
import org.sagebionetworks.schema.adapter.JSONObjectAdapter;
import org.sagebionetworks.schema.adapter.JSONObjectAdapterException;
import org.sagebionetworks.schema.adapter.org.json.JSONObjectAdapterImpl;

public class RemoteFilePreviewGeneratorUtilsTest {
	
	public RemoteFilePreviewGeneratorUtilsTest() {
	}
	
	@Before
	public void setUp() {
	}
	
	@After
	public void tearDown() {
	}
	
	@Test(expected=JSONObjectAdapterException.class)
	public void testGetGenerationRequestFromHttpRequestInvalidJSON() throws Exception {
		String s = "this is not JSON";
		StringReader sr = new StringReader(s);
		BufferedReader br = new BufferedReader(sr);
		RemoteFilePreviewGeneratorUtils.getGenerationRequestFromHttpRequest(br);
	}
	
	@Test
	public void testGetGenerationRequestFromHttpRequest() throws Exception {
		//	Build a request
		S3FileHandle src = new S3FileHandle();
		src.setBucketName("SRC");
		src.setKey("src");
		S3FileHandle dest = new S3FileHandle();
		dest.setBucketName("DEST");
		dest.setKey("dest");
		RemoteFilePreviewGenerationRequest req = new RemoteFilePreviewGenerationRequest();
		req.setSource(src);
		req.setDestination(dest);
		JSONObjectAdapter joa = new JSONObjectAdapterImpl();
		req.writeToJSONObject(joa);
		//	Make it a JSON string
		String json = joa.toJSONString();
		//	And pass it as reader to fct
		StringReader sr = new StringReader(json);
		BufferedReader br = new BufferedReader(sr);
		RemoteFilePreviewGenerationRequest clone = RemoteFilePreviewGeneratorUtils.getGenerationRequestFromHttpRequest(br);
		assertEquals(req, clone);
	}
	
	
}
