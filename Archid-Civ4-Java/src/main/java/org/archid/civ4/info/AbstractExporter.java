package org.archid.civ4.info;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.Comment;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.archid.utils.IPair;
import org.archid.utils.IPropertyHandler;
import org.archid.utils.ITriple;
import org.archid.utils.PropertyHandler;
import org.archid.utils.PropertyKeys;
import org.archid.utils.StringUtils;
import org.archid.utils.civ4.Civ4FileUtils;

public abstract class AbstractExporter<T extends IInfos<S>, S extends IInfo> implements IExporter {

	/** Logging facility */
	static Logger log = Logger.getLogger(AbstractExporter.class.getName());

	protected IPropertyHandler props = PropertyHandler.getInstance();
	protected T infos;
	protected EInfo infoEnum;
	
	protected Workbook wb = null;
	protected CellStyle csWrap = null;
	protected CellStyle csHeader = null;
	
	protected boolean backup = true;
	
	
	public AbstractExporter(EInfo infoEnum) {
		this.infoEnum= infoEnum;
		log.info("Parsing file: " + props.getAppProperty(PropertyKeys.PROPERTY_KEY_FILE_INFOS));
		this.infos = InfosFactory.readInfos(infoEnum, props.getAppProperty(PropertyKeys.PROPERTY_KEY_FILE_INFOS));
	}

	/* (non-Javadoc)
	 * @see org.archid.civ4.info.techinfo.IExporter#createXLSX()
	 */
	@Override
	public void createXLSX() {
		try {
			String outputFile = getOutputFile();
			if(new File(outputFile).isFile()) {
				InputStream input = new FileInputStream(outputFile);
				wb = new XSSFWorkbook(input);
				input.close();
			} else {
				wb = new XSSFWorkbook();
			}
			preCreateCellStyles();
			createSheets();
			OutputStream output =  new FileOutputStream(outputFile);
			log.info("Exporting data to " + outputFile);
			wb.write(output);
			output.close();
			wb.close();
		} catch (IOException e) {
			log.error("Error creating XLSX", e);
		}
		
	}
	
	protected Sheet getSheet(String name) {
		Sheet sheet = wb.getSheet(name);
		if (sheet != null) {
			wb.removeSheetAt(wb.getSheetIndex(sheet));
		}
		return wb.createSheet(name);
	}

	
	protected void createSheets() {
		createInfoListSheet();
	}
	
	protected void createInfoListSheet() {
		
		Sheet sheet = getSheet(getInfoListSheetName());
		int rowNum = 0;
		
		// Create the header row
		Row row = sheet.createRow(rowNum++);
		createSheetHeaders(row);
		
		// Freeze the first row
		sheet.createFreezePane(1, 1);

		// Loop through the infos
		for (S info: infos.getInfos()) {
			populateRow(sheet.createRow(rowNum++), info);
		}
		
		log.info("Wrote " +  infos.getInfos().size() + " infos to " + getInfoListSheetName() + " sheet.");
		
		for (int i = 0; i < getNumCols(); i++) {
			sheet.autoSizeColumn(i);
		}
		
	};
	
	protected abstract void populateRow(Row row, S info);
	
	protected abstract int getNumCols();
	
	protected abstract String getInfoListSheetName();

	protected void preCreateCellStyles() {
		
		// Create the list header style
		Font headerFont = wb.createFont();
		headerFont.setFontHeightInPoints((short) 11);
		headerFont.setBold(true);
		headerFont.setColor(IndexedColors.DARK_BLUE.getIndex());
		csHeader = wb.createCellStyle();
		csHeader.setFont(headerFont);
		csHeader.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		csHeader.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());

		
		// Create word wrap style for multi line cells
		csWrap = wb.createCellStyle();
		csWrap.setWrapText(true);
		csWrap.setVerticalAlignment(VerticalAlignment.CENTER);

	}

	protected <U> void addSingleCell(Cell cell, U value) {
		cell.setCellValue(value.toString());
		cell.setCellStyle(csWrap);
	}

	protected <U> int addRepeatingCell(Cell cell, List<U> list, int maxHeight) {
		
		int currHeight = 0;
		
		cell.setCellStyle(csWrap);
		StringBuilder cellvalue = new StringBuilder();
		for (U value: list) {
			if (currHeight++ > 0) cellvalue.append("\n");
			cellvalue.append(value);
		}
		cell.setCellValue(cellvalue.toString());
		if (currHeight > maxHeight) maxHeight = currHeight;
		
		return maxHeight;
		
	}

	protected <U, V> int addRepeatingPairCell(Cell cell, List<IPair<U, V>> list, int maxHeight) {
		
		int currHeight = 0;
		
		cell.setCellStyle(csWrap);
		StringBuilder cellvalue = new StringBuilder();
		for (IPair<U, V> pair: list) {
			if (currHeight > 0) cellvalue.append("\n");
			cellvalue.append(pair.getKey() + "\n");
			cellvalue.append(pair.getValue());
			currHeight += 2;
		}
		cell.setCellValue(cellvalue.toString());
		if (currHeight > maxHeight) maxHeight = currHeight;
		
		return maxHeight;
		
	}

	protected <U, V, W> int addRepeatingTripleCell(Cell cell, List<ITriple<U, V, W>> list, int maxHeight) {
		
		int currHeight = 0;
		
		cell.setCellStyle(csWrap);
		StringBuilder cellvalue = new StringBuilder();
		for (ITriple<U, V, W> triple: list) {
			if (currHeight > 0) cellvalue.append("\n");
			cellvalue.append(triple.getKey() + "\n");
			cellvalue.append(triple.getValue() + "\n");
			cellvalue.append(triple.getData());
			currHeight += 3;
		}
		cell.setCellValue(cellvalue.toString());
		if (currHeight > maxHeight) maxHeight = currHeight;
		
		return maxHeight;
		
	}

	protected <U, V> int addMapListCell(Cell cell, Map<U, List<V>> map, int maxHeight) {
		
		int currHeight = 0;
		
		cell.setCellStyle(csWrap);
		StringBuilder cellvalue = new StringBuilder();
		for (U key: map.keySet()) {
			if (currHeight > 0) cellvalue.append(IInfoWorkbook.CELL_NEWLINE + IInfoWorkbook.CELL_GROUP_DELIM + IInfoWorkbook.CELL_NEWLINE);
			cellvalue.append(key);
			currHeight++;
			for (V item: map.get(key)) {
				cellvalue.append(IInfoWorkbook.CELL_NEWLINE + IInfoWorkbook.CELL_GROUP_PAD + item);
				currHeight++;
			}
			currHeight++;
		}
		cell.setCellValue(cellvalue.toString());
		if (currHeight > maxHeight) maxHeight = currHeight;
		
		return maxHeight;
		
	}

	protected void createSheetHeaders(Row row) {
		int colNum = 0;
		for (String header: getHeaders()) {
			addHeaderCell(row.createCell(colNum++), header);
		}
	}

	protected void setCellComment(Cell cell, String message) {
    Drawing<?> drawing = cell.getSheet().createDrawingPatriarch();
    CreationHelper factory = cell.getSheet().getWorkbook().getCreationHelper();

    ClientAnchor anchor = factory.createClientAnchor();
    if (StringUtils.isNullOrEmpty(message)) return;
    
    int height = message.split("\n").length;
    int width = 2;
    anchor.setCol1(cell.getColumnIndex());
    anchor.setCol2(cell.getColumnIndex() + width);
    anchor.setRow1(cell.getRowIndex());
    anchor.setRow2(cell.getRowIndex() + height);
    anchor.setDx1(100);
    anchor.setDx2(100);
    anchor.setDy1(100);
    anchor.setDy2(100);


    try {
			Comment comment = drawing.createCellComment(anchor);
	    RichTextString str = factory.createRichTextString(message.toString());
	    comment.setString(str);
	    cell.setCellComment(comment);		
    }
    catch (Exception e) {
    	log.error("Error creating cell comment - comment likely to be incorrect", e);
    }
	}
	
	private void addHeaderCell(Cell cell, String value) {
		addSingleCell(cell, value);
		cell.setCellStyle(csHeader);
	}

	private String getOutputFile() throws IOException {
		String outputFile = props.hasProperty(IPropertyHandler.APP_PROPERTY_FILE, PropertyKeys.PROPERTY_KEY_FILE_XSLX, IPropertyHandler.PropertyFileTypes.PROP_USER) ? props.getAppProperty(PropertyKeys.PROPERTY_KEY_FILE_XSLX) : props.getAppProperty(PropertyKeys.PROPERTY_KEY_FILE_INFOS);
		if (props.hasProperty(IPropertyHandler.APP_PROPERTY_FILE, PropertyKeys.PROPERTY_KEY_FILE_XSLX, IPropertyHandler.PropertyFileTypes.PROP_USER)) {
			outputFile = props.getAppProperty(PropertyKeys.PROPERTY_KEY_FILE_XSLX);
		} else {
			outputFile = props.getAppProperty(PropertyKeys.PROPERTY_KEY_FILE_INFOS);
		}
		return Civ4FileUtils.prepareOutputFile(outputFile, "xlsx", backup);
	}

	public void setBackup(boolean backup) {
		this.backup = backup;
	}
	
}
