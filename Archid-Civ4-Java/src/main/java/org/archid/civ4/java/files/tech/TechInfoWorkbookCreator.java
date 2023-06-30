package org.archid.civ4.java.files.tech;

import org.archid.civ4.java.IJavaFileCreator;
import org.archid.civ4.java.JavaCodeGeneratorData;
import org.archid.civ4.java.TagInstance;
import org.archid.civ4.schema.XmlTagInstance;
import org.archid.utils.StringUtils;

public class TechInfoWorkbookCreator implements IJavaFileCreator {

	@Override
	public String getFileContent() {
		StringBuilder file = new StringBuilder();
		file.append(JavaCodeGeneratorData.getInstance().getPackageDef());
		file.append(NEWLINE);
		file.append(NEWLINE + "import org.archid.civ4.info.IInfoWorkbook;");
		file.append(NEWLINE + "import org.archid.utils.StringUtils;");
		file.append(NEWLINE);
		file.append(NEWLINE + "public interface ITechWorkbook extends IInfoWorkbook {");
		file.append(NEWLINE);
		file.append(NEWLINET + "public static final String SHEETNAME_TREE = \"TechTree\";");
		file.append(NEWLINET + "public static final String SHEETNAME_LIST = \"TechList\";");
		file.append(NEWLINE);
		file.append(NEWLINET + "public enum SheetHeaders {");
		file.append(NEWLINETT);
		StringBuilder row = new StringBuilder();
		boolean first = true;
		boolean reset = false;
		for (XmlTagInstance mainChild : JavaCodeGeneratorData.getInstance().getInfoChildTags()) {
			TagInstance tag = JavaCodeGeneratorData.getInstance().getTagInstance(mainChild.getTagName());
			if (first) {
				first = false;
			} else if (reset) {
				row.append(", " + NEWLINETT);
				reset = false;
			} else {
				row.append(", ");
			}
			row.append(StringUtils.uCaseSplit(tag.getVarName(), '_'));
			if (row.length() >= 170) {
				file.append(row);
				row.setLength(0);
				reset = true;
			}
		}
		file.append(row + ";");
		file.append(NEWLINE);
		file.append(NEWLINETT + "@Override");
		file.append(NEWLINETT + "public String toString() {");
		file.append(NEWLINETTT + "return StringUtils.titleCaseSpace(this.name(), '_');");
		file.append(NEWLINETT + "}");
		file.append(NEWLINET + "}");
		file.append(NEWLINE + "}");
		
		return file.toString();
	}
}
