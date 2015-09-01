package org.sagebionetworks.remotefilepreviewgenerator.servlet;

import javax.servlet.http.HttpServlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.inject.Singleton;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Singleton
public class HealthCheckServlet extends HttpServlet {

	private static final Logger log = LogManager.getLogger(HealthCheckServlet.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		log.debug("In HealthCheckServlet.doGet().");
		
		resp.setStatus(HttpServletResponse.SC_OK);
		resp.getWriter().append("OK");
		resp.getWriter().close();

	}


}
