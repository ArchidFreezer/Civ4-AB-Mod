package org.archid.utils;

public interface IPropertyHandler {

	public final String APP_PROPERTY_FILE = "app";
	
	public enum PropertyFileTypes{
		PROP_USER, PROP_DEFAULT, PROP_ALL;
	}
	
	String getAppProperty(String property);

	String getProperty(String file, String property);

	String getAppProperty(String property, String defaultVal);

	String getProperty(String file, String property, String defaultVal);
	
	void removeAppProperty(String property);

	void removeProperty(String file, String property);

	void setAppProperty(String property, String value);

	void setProperty(String file, String property, String value);

	boolean hasProperty(String property);

	boolean hasProperty(String file, String property);

	boolean hasProperty(String file, String property, PropertyFileTypes types);

	void save();

	void init();

}