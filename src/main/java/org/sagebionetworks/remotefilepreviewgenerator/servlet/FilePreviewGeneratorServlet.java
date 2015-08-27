package org.sagebionetworks.remotefilepreviewgenerator.servlet;

import com.google.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.inject.Singleton;
import java.io.BufferedReader;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.sagebionetworks.remotefilepreviewgenerator.service.FilePreviewGeneratorService;

@Singleton
public class FilePreviewGeneratorServlet extends HttpServlet {

	private static final Logger log = LogManager.getLogger(FilePreviewGeneratorServlet.class);
	
	
	private FilePreviewGeneratorService generationSvc;
	@Inject
	public void setFilePreviewGenerationService(FilePreviewGeneratorService svc) {
		this.generationSvc = svc;
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		log.debug("In FilePreviewGeneratorServlet.doGet().");
		
		//TODO: This is where we just check that the message from the daemon is not null.
		String srcKey = req.getParameter("sourceKey");
		String destKey = req.getParameter("destinationKey");
		String srcBucketName = req.getParameter("sourceBucketName");
		String destBucketName = req.getParameter("destinationBucketName");
		if ((srcKey == null) || (destKey == null)) {
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			resp.getWriter().append("Source and destination cannot be null.");
			resp.getWriter().close();
		} else {
			generationSvc.generateFilePreview(srcBucketName, srcKey, destBucketName, destKey);
			resp.setStatus(HttpServletResponse.SC_OK);
			resp.getWriter().append("Source: " + srcKey + ", Destination: " + destKey);
			resp.getWriter().close();
		}
		
//		//	Actual code that should run
//		BufferedReader r = req.getReader();
//		JSONObject body = null;
//		try {
//			body = getBody(r);
//			generationSvc.generateFilePreview(body);
//			resp.setStatus(HttpServletResponse.SC_OK);
//			resp.getWriter().close();
//		} catch (Exception e) {
//			//	TODO: Better error handling
//			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//			resp.getWriter().append("Could not process the request: " + body);
//			resp.getWriter().close();
//		}
//	}
//	
//	private JSONObject getBody(BufferedReader reader) throws IOException {
//		StringBuilder sb = new StringBuilder();
//		try {
//			String l;
//			while ((l = reader.readLine()) != null)
//				sb.append(l).append('\n');
//		} finally {
//			reader.close();
//		}
//		return new JSONObject(sb.toString());
	}
}
