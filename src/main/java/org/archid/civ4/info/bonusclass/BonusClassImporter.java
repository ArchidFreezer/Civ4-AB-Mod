package org.archid.civ4.info.bonusclass;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.archid.civ4.info.AbstractImporter;
import org.archid.civ4.info.DefaultXmlFormatter;
import org.archid.civ4.info.EInfo;
import org.archid.civ4.info.IInfos;

public class BonusClassImporter extends AbstractImporter<IInfos<IBonusClassInfo>, IBonusClassInfo> {

	/** Logging facility */
	static Logger log = Logger.getLogger(BonusClassImporter.class.getName());

	public BonusClassImporter(EInfo infoEnum) {
		super(infoEnum, new DefaultXmlFormatter("BonusClass"));
	}

	@Override
	public String getListSheetName() {
		return IBonusClassWorkbook.SHEETNAME_LIST;
	}

	@Override
	protected IBonusClassInfo parseRow(Row row) {
		int colNum = 0;
		String type = row.getCell(0).getStringCellValue();
		// Handle cells that have been moved
		if (type.isEmpty())
			return null;

		IBonusClassInfo info = BonusClassInfos.createInfo(type);
		parseCell(row.getCell(colNum++), String.class, info::setType);
		parseCell(row.getCell(colNum++), Integer.class, info::setUnique);

		return info;
	}
}