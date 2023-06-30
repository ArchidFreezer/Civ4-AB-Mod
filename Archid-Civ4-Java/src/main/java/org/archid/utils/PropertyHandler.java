/**
 * 
 */
package org.archid.utils;

/**
 * @author Jim
 *
 */
public class PropertyHandler extends AbstractPropertyHandler {

	private volatile static PropertyHandler instance;
	
	private PropertyHandler() {};
	
	public static PropertyHandler getInstance() {
		synchronized (PropertyHandler.class) {
			if (instance == null) {
				instance = new PropertyHandler();
				instance.loadPropertyFile(APP_PROPERTY_FILE);
			}
		}
		return instance;
	}
	
}
