package org.archid.civ4.java.files;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.archid.civ4.java.IJavaFileCreator;
import org.archid.civ4.java.JavaCodeGeneratorData;
import org.archid.civ4.java.TagInstance;
import org.archid.civ4.schema.XmlTagInstance;

public class DefaultInfoInterfaceCreator implements IJavaFileCreator {

	@Override
	public String getFileContent() {
		String infoName = JavaCodeGeneratorData.getInstance().getInfoName();

		StringBuilder file = new StringBuilder();
		file.append(JavaCodeGeneratorData.getInstance().getPackageDef());
		file.append(NEWLINE);
		
		Set<String> imports = new HashSet<String>(JavaCodeGeneratorData.getInstance().getDynamicImports());
		imports.add("import org.archid.civ4.info.IInfo;");
		List<String> sortedImports = new ArrayList<String>(imports);
		Collections.sort(sortedImports);
		for (String imp: sortedImports) {
			file.append(NEWLINE + imp);
		}
		
		// Now do the interface
		file.append(NEWLINE + NEWLINE + "public interface I" + infoName + " extends IInfo {");
		for (XmlTagInstance mainChild : JavaCodeGeneratorData.getInstance().getInfoChildTags()) {
			// The Type tag is processed in the IInfo interface 
			if (mainChild.getTagName().equals("Type"))
				continue;
				
			TagInstance tag = JavaCodeGeneratorData.getInstance().getTagInstance(mainChild.getTagName());
			file.append(NEWLINET + tag.getterSignature() + ";");
			file.append(NEWLINET + tag.setterSignature() + ";");
			file.append(NEWLINE);
		}
		file.append(NEWLINE + "}");
		return file.toString();
	}

}
