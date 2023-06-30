package org.archid.civ4.info.bonusclass;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.archid.civ4.info.AbstractExporter;
import org.archid.civ4.info.EInfo;
import org.archid.civ4.info.IInfos;
import org.archid.civ4.info.bonusclass.IBonusClassWorkbook.SheetHeaders;

public class BonusClassExporter extends AbstractExporter<IInfos<IBonusClassInfo>, IBonusClassInfo> {

	/** Logging facility */
	static Logger log = Logger.getLogger(BonusClassExporter.class.getName());

	public BonusClassExporter(EInfo infoEnum) {
		super(infoEnum);
	}

	@Override
	public List<String> getHeaders() {
		List<String> headers = new ArrayList<String>();
		for (SheetHeaders header: SheetHeaders.values()) {
			headers.add(header.toString());
		}
		return headers;
	}

	@Override
	protected int getNumCols() {
		return IBonusClassWorkbook.SheetHeaders.values().length;
	}

	@Override
	protected String getInfoListSheetName() {
		return IBonusClassWorkbook.SHEETNAME_LIST;
	}

	@Override
	protected void populateRow(Row row, IBonusClassInfo info) {
		int maxHeight = 1;
		int colNum = 0;
		addSingleCell(row.createCell(colNum++), info.getType());
		addSingleCell(row.createCell(colNum++), info.getUnique());

		row.setHeightInPoints(maxHeight * row.getSheet().getDefaultRowHeightInPoints());
	}
}