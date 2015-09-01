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
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		log.debug("In FilePreviewGeneratorServlet.doGet().");
		resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		BufferedReader r = req.getReader();
		JSONObject body = null;
		try {
			body = getBody(r);
			if (body != null) {
				log.debug("Processing request: " + body.toString());
				generationSvc.generateFilePreview(body);
				resp.getWriter().append("Processed request.");
				resp.setStatus(HttpServletResponse.SC_OK);
			} else {
				throw new IllegalArgumentException("Body cannot be null");
			}
		} catch (Exception e) {
			log.error("Exception caught processing request.", e);
			//	TODO: Better error handling
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			if (body != null) {
				resp.getWriter().append("Could not process the request: " + body);
			} else {
				resp.getWriter().append("Could not process null request body");
			}
		} finally {
			resp.getWriter().close();
		}
	}
	
	private JSONObject getBody(BufferedReader reader) throws IOException {
		StringBuilder sb = new StringBuilder();
		try {
			String l;
			while ((l = reader.readLine()) != null)
				sb.append(l).append('\n');
		} finally {
			reader.close();
		}
		return new JSONObject(sb.toString());
	}
}
