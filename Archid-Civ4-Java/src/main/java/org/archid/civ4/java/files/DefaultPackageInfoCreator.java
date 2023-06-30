package org.archid.civ4.java.files;

import org.archid.civ4.java.IJavaFileCreator;
import org.archid.civ4.java.JavaCodeGeneratorData;
import org.archid.utils.PropertyHandler;
import org.archid.utils.PropertyKeys;

public class DefaultPackageInfoCreator implements IJavaFileCreator {

	@Override
	public String getFileContent() {
		StringBuilder sb = new StringBuilder();
		sb.append("@javax.xml.bind.annotation.XmlSchema(namespace=\"x-schema:" + PropertyHandler.getInstance().getAppProperty(PropertyKeys.PROPERTY_KEY_MOD_SCHEMA) + "\", elementFormDefault = javax.xml.bind.annotation.XmlNsForm.QUALIFIED)");
		sb.append(NEWLINE + JavaCodeGeneratorData.getInstance().getPackageDef());
		return sb.toString();
	}

}
