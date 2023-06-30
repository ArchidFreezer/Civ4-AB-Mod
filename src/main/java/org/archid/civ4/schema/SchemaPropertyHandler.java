package org.archid.civ4.schema;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.archid.utils.AbstractPropertyHandler;

public class SchemaPropertyHandler extends AbstractPropertyHandler implements ISchemaPropertyhandler {

	private volatile static SchemaPropertyHandler instance;
	
	private SchemaPropertyHandler() {};
	
	public static SchemaPropertyHandler getInstance() {
		synchronized (SchemaPropertyHandler.class) {
			if (instance == null) {
				instance = new SchemaPropertyHandler();
				instance.loadPropertyFile(APP_PROPERTY_FILE);
			}
		}
		return instance;
	}
	
	public Set<String> getIgnores() {
		Set<String> ignoresSet = new HashSet<String>();
		String defaultIgnores = getPropertyFile(APP_PROPERTY_FILE, PropertyFileTypes.PROP_DEFAULT).getProperty(SchemaPropertyKeys.PROPERTY_KEY_IGNORE_TYPES, "");
		if (!defaultIgnores.equals(""))
			ignoresSet.addAll(Arrays.asList(defaultIgnores.split(",")));
		String userIgnores = getPropertyFile(APP_PROPERTY_FILE, PropertyFileTypes.PROP_USER).getProperty(SchemaPropertyKeys.PROPERTY_KEY_IGNORE_TYPES, "");
		if (!userIgnores.equals(""))
			ignoresSet.addAll(Arrays.asList(userIgnores.split(",")));
		return ignoresSet;
	}
	
	public Set<String> getManuals() {
		Set<String> manuals = new HashSet<String>();
		String defaultManuals = getPropertyFile(APP_PROPERTY_FILE, PropertyFileTypes.PROP_DEFAULT).getProperty(SchemaPropertyKeys.PROPERTY_KEY_MANUAL_TYPES, "");
		if (!defaultManuals.equals(""))
			manuals.addAll(Arrays.asList(defaultManuals.split(",")));
		String userManuals = getPropertyFile(APP_PROPERTY_FILE, PropertyFileTypes.PROP_USER).getProperty(SchemaPropertyKeys.PROPERTY_KEY_MANUAL_TYPES, "");
		if (!userManuals.equals(""))
			manuals.addAll(Arrays.asList(userManuals.split(",")));
		return manuals;
	}
	
}
