package org.sagebionetworks.remotefilepreviewgenerator.config;

import com.google.inject.Inject;
import com.google.inject.Injector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ApplicationMain {

	private static final Logger log = LogManager.getLogger(ApplicationMain.class);
	
	Injector injector;
	
	@Inject
	public ApplicationMain(Injector injector){
		this.injector = injector;
	}

	void shutdown() {
		log.info("Shutting down the worker application");
		if(injector == null){
			// there is nothing to do if we did not start.
			return;
		}
	}

	void startup() {
		try {
			log.info("Starting worker application...");
			if(injector == null){
				log.error("Injector is null.  Cannot start the application.");
				return;
			}
		} catch (Exception e) {
			log.error("Failed to start application: "+e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * This main can be used to debug the application.
	 * Simply start the application with all of the required configuration options in the "VM Arguments"
	 * <ul>
	 * <li>-Dorg.sagebionetworks.stack.iam.id=your_aws_id</li>
	 * <li>-Dorg.sagebionetworks.stack.iam.key=your_aws_key</li>
	 * <li>-Dorg.sagebionetworks.warehouse.worker.stack=your_stack</li>
	 * <li>-Dorg.sagebionetworks.warehouse.workers.jdbc.connection.url =jdbc:mysql://your_db_host/your_db_schema</li>
	 * <li>-Dorg.sagebionetworks.warehouse.workers.jdbc.user.password =your_password</li>
	 * <li>-Dorg.sagebionetworks.warehouse.workers.jdbc.user.username =your_db_username</li>
	 * </ul>
	 * 
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException{
		// Setup the container
		Injector injector = ApplicationServletContextListener.createNewGuiceInjector();
		ApplicationMain main = new ApplicationMain(injector);
		// start the application
		main.startup();
		// enter a wait state
		try{
			while(true){
				log.info("Main thread running...");
				Thread.sleep(10*1000);
			}
		}finally{
			main.shutdown();
		}
	}
}
