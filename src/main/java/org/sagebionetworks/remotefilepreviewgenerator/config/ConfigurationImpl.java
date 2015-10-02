package org.sagebionetworks.remotefilepreviewgenerator.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.jdom.JDOMException;

import com.google.inject.Singleton;

@Singleton
public class ConfigurationImpl implements Configuration {

	private static final String CONFIGURATION_PROPERTIES = "configuration.properties";
	Properties properties;
	
	public ConfigurationImpl() throws IOException, JDOMException{
		// First load the configuration properties.
		properties = new Properties();
		InputStream in = this.getClass().getClassLoader().getResourceAsStream(CONFIGURATION_PROPERTIES);
		if(in == null){
			throw new IllegalArgumentException("Cannot find: "+CONFIGURATION_PROPERTIES+" on the classpath");
		}
		try{
			properties.load(in);
		}finally{
			in.close();
		}
		// replace the properties from settings
		Properties settings = SettingsLoader.loadSettingsFile();
		if(settings != null){
			for(String key: settings.stringPropertyNames()){
				properties.put(key, settings.get(key));
			}
		}
		// Now override the configuration with the system properties.
		for(String key: System.getProperties().stringPropertyNames()){
			properties.put(key, System.getProperties().get(key));
		}
		// Link and replace references in values.
		properties = PropertyLinker.linkAndReplace(properties);
	}

	/*
	 * (non-Javadoc)
	 * @see org.sagebionetworks.warehouse.workers.config.Configuration#getProperty(java.lang.String)
	 */
	public String getProperty(String key) {
		if(key == null){
			throw new IllegalArgumentException("Key cannot be null");
		}
		String value = properties.getProperty(key);
		if(value == null){
			throw new IllegalArgumentException("Cannot find environmental property: "+key);
		}
		value = value.trim();
		if("".equals(value)){
			throw new IllegalArgumentException("Cannot property: "+key+" was empty");
		}
		return value;
	}
	

}
