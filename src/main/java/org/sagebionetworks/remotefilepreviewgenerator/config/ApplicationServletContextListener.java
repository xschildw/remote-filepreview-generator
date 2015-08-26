package org.sagebionetworks.remotefilepreviewgenerator.config;

import javax.servlet.ServletContextEvent;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import org.sagebionetworks.remotefilepreviewgenerator.aws.AwsModule;
import org.sagebionetworks.remotefilepreviewgenerator.service.FilePreviewGeneratorServiceModule;

import org.sagebionetworks.remotefilepreviewgenerator.servlet.FilePreviewGeneratorServletModule;

public class ApplicationServletContextListener extends GuiceServletContextListener {

	ApplicationMain main;
	
	public static Injector createNewGuiceInjector() {
		return  Guice.createInjector(new FilePreviewGeneratorServletModule(), new FilePreviewGeneratorServiceModule());
	}

	@Override
	protected Injector getInjector() {
		Injector injector = ApplicationServletContextListener.createNewGuiceInjector();
//		main = new ApplicationMain(injector);
		return injector;
	}

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
//		// cleanup happens here.
//		if(main != null){
//			main.shutdown();
//		}
		super.contextDestroyed(servletContextEvent);
	}


	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		super.contextInitialized(servletContextEvent);
//		if(main != null){
//			main.startup();
//		}
	}
	

}
