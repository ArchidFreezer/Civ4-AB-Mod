package org.archid.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


import org.apache.log4j.Logger;

public abstract class AbstractPropertyHandler implements IPropertyHandler {
		
	/** Internal path where the default property files are stored */
	private final String DEFAULT_PATH = "/res/";
	
	/** Logging facility */
	Logger log = Logger.getLogger(AbstractPropertyHandler.class.getName());

	/** Property files */
	private Map<String, Properties> defaultFiles = new HashMap<String, Properties>();
	private Map<String, Properties> appFiles = new HashMap<String, Properties>();
	
	/* (non-Javadoc)
	 * @see org.archid.civ4.utils.IPropertyHandler#getAppProperty(java.lang.String)
	 */
	@Override
	public String getAppProperty(String property) {
		return getProperty(APP_PROPERTY_FILE, property, null);
	}
	
	/* (non-Javadoc)
	 * @see org.archid.civ4.utils.IPropertyHandler#getProperty(java.lang.String, java.lang.String)
	 */
	@Override
	public String getProperty(String file, String property) {
		return getProperty(file, property, null);
	}
	
	/* (non-Javadoc)
	 * @see org.archid.civ4.utils.IPropertyHandler#getAppProperty(java.lang.String, java.lang.String)
	 */
	@Override
	public String getAppProperty(String property, String defaultVal) {
		return getProperty(APP_PROPERTY_FILE, property, defaultVal);
	}
	
	/* (non-Javadoc)
	 * @see org.archid.civ4.utils.IPropertyHandler#getProperty(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String getProperty(String file, String property, String defaultVal) {
		String result = null;
		if (hasProperty(file, property, PropertyFileTypes.PROP_USER))
			result = getPropertyFile(file, PropertyFileTypes.PROP_USER).getProperty(property);
		else
			result = getPropertyFile(file, PropertyFileTypes.PROP_DEFAULT).getProperty(property, defaultVal);
		return result;
	}

	/* (non-Javadoc)
	 * @see org.archid.utils.IPropertyHandler#removeAppProperty(java.lang.String)
	 */
	@Override
	public void removeAppProperty(String property) {
		getPropertyFile(APP_PROPERTY_FILE, PropertyFileTypes.PROP_USER).remove(property);
	}

	/* (non-Javadoc)
	 * @see org.archid.utils.IPropertyHandler#removeProperty(java.lang.String, java.lang.String)
	 */
	@Override
	public void removeProperty(String file, String property) {
		getPropertyFile(file, PropertyFileTypes.PROP_USER).remove(property);
	}
	
	/* (non-Javadoc)
	 * @see org.archid.civ4.utils.IPropertyHandler#setAppProperty(java.lang.String, java.lang.String)
	 */
	@Override
	public void setAppProperty(String property, String value) {
		setProperty(APP_PROPERTY_FILE, property, value);
	}
	
	/* (non-Javadoc)
	 * @see org.archid.civ4.utils.IPropertyHandler#setProperty(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void setProperty(String file, String property, String value) {
		getPropertyFile(file, PropertyFileTypes.PROP_USER).setProperty(property, value);
	}
	
	@Override
	public boolean hasProperty(String property) {
		return hasProperty(APP_PROPERTY_FILE, property);
	}

	/* (non-Javadoc)
	 * @see org.archid.civ4.utils.IPropertyHandler#hasProperty(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean hasProperty(String file, String property) {
		return hasProperty(file, property, PropertyFileTypes.PROP_ALL);
	}
	
	public boolean hasProperty(String file, String property, PropertyFileTypes types) {
		boolean found = false;
		switch (types) {
		case PROP_ALL:
			found =  getPropertyFile(file, PropertyFileTypes.PROP_USER).containsKey(property) || getPropertyFile(file, PropertyFileTypes.PROP_DEFAULT).containsKey(property);
			break;
		case PROP_DEFAULT:
			found =  getPropertyFile(file, PropertyFileTypes.PROP_DEFAULT).containsKey(property);
			break;
		case PROP_USER:
			found =  getPropertyFile(file, PropertyFileTypes.PROP_USER).containsKey(property);
			break;
		default:
			break;
		}
		
		return found;
	}
	
	protected Properties getPropertyFile(String name, PropertyFileTypes files) {
		Properties props = null;
		
		switch(files) {
		case PROP_USER:
			if (appFiles.containsKey(name))
				props = appFiles.get(name);
			break;
		case PROP_DEFAULT:
			if (defaultFiles.containsKey(name))
				props = defaultFiles.get(name);
			break;
		default:
			break;
		}
		if (props == null)
			props = loadPropertyFile(name);
		
		return props;
	}
	
	protected Properties loadPropertyFile(String name) {
		Properties defaultProps = new Properties();
		InputStream defaultIn = null;
		try {
			defaultIn = AbstractPropertyHandler.class.getResourceAsStream(DEFAULT_PATH + name + ".properties");
			if (defaultIn != null)
				defaultProps.load(defaultIn);
		} catch (IOException e) {
			log.error("Error reading default properties file: " + name + ".properties", e);
		} finally {
			if (defaultIn != null) {
				try {
					defaultIn.close();
				} catch (IOException e) {
					log.error("Error closing default properties file: " + name + ".properties", e);
				}
			}
		}
		defaultFiles.put(name, defaultProps);
		
		Properties appProps = new Properties();
		InputStream appIn = null; 
		String userPath = getUserFilePath(name);
		try {
			appIn = new FileInputStream(userPath);
			appProps.load(appIn);
		} catch (FileNotFoundException e) {
			log.debug("Could not find user properties file: " + userPath + " using defaults");
		} catch (IOException e) {
			log.error("Error reading user properties file: " + userPath + ".properties", e);
		} finally {
			if (appIn != null) {
				try {
					appIn.close();
				} catch (IOException e) {
					log.error("Error closing user properties file: " + userPath + ".properties", e);
				}
			}
		}
		appFiles.put(name, appProps);
		return appProps;

	}
	
	/* (non-Javadoc)
	 * @see org.archid.civ4.utils.IPropertyHandler#save()
	 */
	@Override
	public void save() {
		for (String file : appFiles.keySet()) {
			OutputStream out = null;
			try {
				Properties prop = appFiles.get(file);
				if (!prop.isEmpty()) {
					out = new FileOutputStream(getUserFilePath(file));
					appFiles.get(file).store(out, "Schema Help Generator Properties File");
				}
			} catch (IOException e) {
				log.error("Error writing properties file", e);
			} finally {
				if (out != null) {
					try {
						out.close();
					} catch (IOException e) {
						log.error("Error closing properties file", e);
					}
				}
			}
		}
	}
	
	protected String getUserFilePath(String name) {
		String userDir = null;
		if (name.equals(APP_PROPERTY_FILE))
			userDir = "./";
		else {
			userDir = getAppProperty(PropertyKeys.PROPERTY_KEY_USER_PROPERTY_FILES_DIR);
			if (!userDir.endsWith("\\") && !userDir.endsWith("/"))
				userDir = userDir + "/";
		}
		userDir = userDir + name + ".properties";
		return userDir;
	}
	
	/* (non-Javadoc)
	 * @see org.archid.civ4.utils.IPropertyHandler#init()
	 */
	@Override
	public void init() {
		loadPropertyFile(APP_PROPERTY_FILE);
	}

}
