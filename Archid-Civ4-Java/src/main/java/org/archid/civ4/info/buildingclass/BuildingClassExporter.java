package org.archid.civ4.info.buildingclass;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.archid.civ4.info.AbstractExporter;
import org.archid.civ4.info.EInfo;
import org.archid.civ4.info.IInfos;
import org.archid.civ4.info.buildingclass.IBuildingClassWorkbook.SheetHeaders;

public class BuildingClassExporter extends AbstractExporter<IInfos<IBuildingClassInfo>, IBuildingClassInfo> {

	/** Logging facility */
	static Logger log = Logger.getLogger(BuildingClassExporter.class.getName());

	public BuildingClassExporter(EInfo infoEnum) {
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
		return IBuildingClassWorkbook.SheetHeaders.values().length;
	}

	@Override
	protected String getInfoListSheetName() {
		return IBuildingClassWorkbook.SHEETNAME_LIST;
	}

	@Override
	protected void populateRow(Row row, IBuildingClassInfo info) {
		int maxHeight = 1;
		int colNum = 0;
		addSingleCell(row.createCell(colNum++), info.getType());
		addSingleCell(row.createCell(colNum++), info.getDescription());
		addSingleCell(row.createCell(colNum++), info.getCategory());
		addSingleCell(row.createCell(colNum++), info.getMaxGlobalInstances());
		addSingleCell(row.createCell(colNum++), info.getMaxTeamInstances());
		addSingleCell(row.createCell(colNum++), info.getMaxPlayerInstances());
		addSingleCell(row.createCell(colNum++), info.getExtraPlayerInstances());
		addSingleCell(row.createCell(colNum++), info.isNoLimit());
		addSingleCell(row.createCell(colNum++), info.isMonument());
		addSingleCell(row.createCell(colNum++), info.getDefaultBuilding());
		maxHeight = addRepeatingPairCell(row.createCell(colNum++), info.getVictoryThresholds(), maxHeight);

		row.setHeightInPoints(maxHeight * row.getSheet().getDefaultRowHeightInPoints());
	}
}