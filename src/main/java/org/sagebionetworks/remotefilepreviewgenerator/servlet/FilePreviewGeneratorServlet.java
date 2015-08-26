package org.sagebionetworks.remotefilepreviewgenerator.servlet;

import com.google.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.inject.Singleton;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
		String srcKey = req.getParameter("source");
		String destKey = req.getParameter("destination");
		if ((srcKey == null) || (destKey == null)) {
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			resp.getWriter().append("Source and destination cannot be null.");
			resp.getWriter().close();
		} else {
			generationSvc.generateFilePreview(srcKey, destKey);
			resp.setStatus(HttpServletResponse.SC_OK);
			resp.getWriter().append("Source: " + srcKey + ", Destination: " + destKey);
			resp.getWriter().close();
		}
	}}
