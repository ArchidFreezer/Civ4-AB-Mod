/**
 * 
 */
package org.archid.civ4.info;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.archid.utils.IPair;
import org.archid.utils.IPropertyHandler;
import org.archid.utils.ITriple;
import org.archid.utils.Pair;
import org.archid.utils.PropertyHandler;
import org.archid.utils.PropertyKeys;
import org.archid.utils.StringUtils;
import org.archid.utils.Triple;
import org.archid.utils.civ4.Civ4FileUtils;

/**
 * @author Jim
 *
 * @param <T> Type that represents the collection of {@code <S>} infos being imported, subclass of {@link IInfos}
 * @param <S> Type of info being imported, must be a subclass of {@link IInfo}
 */
public abstract class AbstractImporter<T extends IInfos<S>, S extends IInfo> implements IImporter {

	/** Logging facility */
	static Logger log = Logger.getLogger(AbstractImporter.class.getName());

	protected static final String newline = System.getProperty("line.separator");
	
	protected static IPropertyHandler props = PropertyHandler.getInstance();
	protected T infos;
	protected EInfo infoEnum;
	protected IXmlFormatter formatter;
	
	/**
	 * Constructor
	 * 
	 * Takes a parameter that defined the type of info being processed and loads the associated list of infos
	 * 
	 * @param infoEnum {@link EInfo} enumeration value defining the type of info being imported
	 */
	public AbstractImporter(EInfo infoEnum, IXmlFormatter formatter) {
		this.infoEnum= infoEnum;
		this.infos = InfosFactory.getInfos(infoEnum);
		this.formatter = formatter;
	}

	/* (non-Javadoc)
	 * @see org.archid.civ4.info.techinfo.IImporter#importXLSX()
	 */
	@Override
	public void importXLSX() {
		
		// Read the xlsx file to create the list of tech infos to update
		if (parseXlsx(getWorkbook())) {
			try {
				String outputFile = Civ4FileUtils.prepareOutputFile(props.getAppProperty(PropertyKeys.PROPERTY_KEY_FILE_INFOS));
				log.info("Importing to " + outputFile);
				InfosFactory.writeInfos(infoEnum, outputFile, infos);
				if (formatter != null) formatter.format(outputFile);
			} catch (IOException e) {
				log.error("Error backing up infos file ... aborting", e);
			}
		}
	}
	
	/**
	 * Gets the workbook that the info data will be imported from
	 *  
	 * @return {@link Workbook} to process
	 */
	private Workbook getWorkbook() {
		String filepath = props.getAppProperty(PropertyKeys.PROPERTY_KEY_FILE_XSLX);
		log.info("Importing " + StringUtils.titleCaseCompress(infoEnum.name(), '_') + "Infos - Reading workbook: " + filepath);
		Workbook wb = null;
		try {
			wb = new XSSFWorkbook(filepath);
		} catch (IOException e) {
			log.error("Error opening workboook from file: " + filepath, e);
		}
		return wb;
	}

	/**
	 * Reads the content of a workbook {@link Cell} containing a list of values delimited by {@link IInfoWorkbook.CELL_NEWLINE} and
	 * calls the {@link Consumer} function provided with the value of the cell parsed as the class as defined in the {@code listClass} param
	 * <p>
	 * An example for a cell containing a list of Integers that are added to the info using its {@code setValMethodName} method would be: <p>
	 * {@code parseCell(cell, Integer.class, info::setValMethodName)}
	 * 
	 * @param cell the workbook cell containing the data to parse
	 * @param valClass {@link Class} to convert the cell value into
	 * @param func the {@link Consumer} function called with the cell value as a parameter
	 */
	protected <U> void parseCell(Cell cell, Class<U> valClass, Consumer<U> func) {
		XSSFCell xsfCell = (XSSFCell) cell;
		// This is a horrible kludge, but formatting a cell as text in Excel doesn't always seem to work properly
		if (xsfCell.getCellTypeEnum() == CellType.NUMERIC) {
			if (valClass == Integer.class || valClass == Boolean.class) {
				func.accept(getVal(String.valueOf((int)cell.getNumericCellValue()), valClass));
			} else if (valClass == Float.class) {
				func.accept(getVal(String.valueOf(cell.getNumericCellValue()), valClass));
			}
		} else if (xsfCell.getCellTypeEnum() == CellType.BOOLEAN) {
			if (valClass == Boolean.class) {
				func.accept(getVal(cell.getBooleanCellValue() ? "true" : "false", valClass));
			}
		} else {
			func.accept(getVal(cell.getStringCellValue(), valClass));
		}
	}
	
	/**
	 * Reads the content of a workbook {@link Cell} containing a list of value pairs delimited by {@link IInfoWorkbook.CELL_NEWLINE} and
	 * calls the {@link Consumer} function provided with the value of the cell parsed into an {@link IPair} keyed by the {@code keyClass} parameter
	 * and with a value of {@code valClass} 
	 * <p>
	 * An example for a cell containing a list of String/Integer pairs that are added to the info using its {@code addPairValMethodName} method would be: <br>
	 * {@code parseCellPairs(cell, String.class, Integer.class, info::addPairValMethodName)}

	 * @param cell the workbook cell containing the data to parse
	 * @param keyClass {@link Class} of the pair key
	 * @param valClass {@link Class} of the pair value
	 * @param func the {@link Consumer} function called with a single {@code IPair} as a parameter
	 */
	protected <U, V> void parsePairsCell(Cell cell, Class<U> keyClass, Class<V> valClass, Consumer<IPair<U, V>> func) {
		String[] arr = cell.getStringCellValue().split(IInfoWorkbook.CELL_NEWLINE);
		if (arr.length > 1) {
			boolean first = true;
			U key = null;
			V val = null;
			for (String str: arr) {
				if (StringUtils.hasCharacters(str)) {
					if (first) {
						key = getVal(str, keyClass);
					} else {
						val = getVal(str, valClass);
						func.accept(new Pair<U, V>(key, val));
					}
					first = !first;
				}
			}
		}
	}
	
	/**
	 * Reads the content of a workbook {@link Cell} containing a list of value triples delimited by {@link IInfoWorkbook.CELL_NEWLINE} and
	 * calls the {@link Consumer} function provided with the value of the cell parsed into an {@link ITriple} keyed by the {@code keyClass} parameter
	 * and with a value of {@code valClass} and data of {@code dataClass} 
	 * <p>
	 * An example for a cell containing a list of String/String/Integer triples that are added to the info using its {@code addTripleValMethodName} method would be: <br>
	 * {@code parseCellTriples(cell, String.class, String.class, Integer.class, info::addTripleValMethodName)}

	 * @param cell the workbook cell containing the data to parse
	 * @param keyClass {@link Class} of the triple key
	 * @param valClass {@link Class} of the triple value
	 * @param dataClass {@link Class} of the triple data
	 * @param func the {@link Consumer} function called with a single {@code IPair} as a parameter
	 */
	protected <U, V, W> void parseTriplesCell(Cell cell, Class<U> keyClass, Class<V> valClass, Class<W> dataClass, Consumer<ITriple<U, V, W>> func) {
		String[] arr = cell.getStringCellValue().split(IInfoWorkbook.CELL_NEWLINE);
		if (arr.length > 2) {
			int count = 0;
			U key = null;
			V val = null;
			W data = null;
			for (String str: arr) {
				if (StringUtils.hasCharacters(str)) {
					if (count == 0) {
						key = getVal(str, keyClass);
					} else if (count == 1){
						val = getVal(str, valClass);
					} else if (count == 2){
						data = getVal(str, dataClass);
						func.accept(new Triple<U, V, W>(key, val, data));
						count = -1;
					}
					count++;
				}
			}
		}
	}
	
	/**
	 * Reads the content of a workbook {@link Cell} containing a list of values delimited by {@link IInfoWorkbook.CELL_NEWLINE} and
	 * calls the {@link Consumer} function provided with the value of the cell parsed as the class as defined in the {@code listClass} param
	 * <p>
	 * An example for a cell containing a list of Integers that are added to the info using its {@code addArrayValMethodName} method would be: <p>
	 * {@code parseCellList(cell, Integer.class, info::addArrayValMethodName)}
	 * 
	 * @param cell the workbook cell containing the data to parse
	 * @param listClass {@link Class} to convert the cell values into 
	 * @param func the {@link Consumer} function called with a single list value as a parameter
	 */
	protected <U> void parseListCell(Cell cell, Class<U> listClass, Consumer<U> func) {
		for (String str: cell.getStringCellValue().split(IInfoWorkbook.CELL_NEWLINE)) {
			if (StringUtils.hasCharacters(str))
				func.accept(getVal(str, listClass));
		}
	}
	
	protected <U,V> void parseMapListCell(Cell cell, Class<U> keyClass, Class<V> listClass, BiConsumer<U, List<V>> func) {
		String[] arr = cell.getStringCellValue().split(IInfoWorkbook.CELL_NEWLINE);
		if (arr.length > 1) {
			boolean newEntry = true;
			U key = null;
			List<V> val = null;
			for (String str: arr) {
				if (StringUtils.hasCharacters(str)) {
					if (str.equals(IInfoWorkbook.CELL_GROUP_DELIM)) {
						func.accept(key, val);
						newEntry = true;
					} else if (newEntry) {
						key = getVal(str, keyClass);
						val = new ArrayList<V>();
						newEntry = false;
					} else {
						val.add(getVal(str.trim(), listClass));
					}
				}
			}
			func.accept(key, val);
		}
		
	}

	/**
	 * Takes a string and parses it into another class type. If the string cannot be parsed then {@code null} will be returned.
	 * 
	 * @param str {@link String} to parse
	 * @param valClass {@link Class} to convert the String into
	 *   
	 * @return {@code str} cast to the class defined by {@code valClass}
	 */
	@SuppressWarnings({ "unchecked" })
	protected <U> U getVal(String str, Class<U> valClass) {
		U val = null;
		if (valClass == String.class)
			val = (U) str;
		else if (valClass == Integer.class)
			val = (U) Integer.valueOf(str);
		else if (valClass == Boolean.class)
			val = (U) Boolean.valueOf(str);
		else if (valClass == Float.class)
			val = (U) Float.valueOf(str);
		
		return val;
		
	}
	
	/**
	 * Main function to read the {@link Workbook} and extract the info data from the tab containing the list of infos.
	 * 
	 * @param wb {@link Workbook} containing the info data
	 * @return {@code true} if the workbook could be parsed and the sheet exists; otherwise {@code false}
	 */
	protected boolean parseXlsx(Workbook wb) {

		// Open the spreadsheet and get the list of infos
		Sheet sheet = wb.getSheet(getListSheetName());
		if (sheet == null) {
			log.warn("Sheet " + getListSheetName() + " does not exist in the workbook");
			return false;
		}
		
		Iterator<Row> itRow = sheet.rowIterator();
		while (itRow.hasNext()) {
			Row row = itRow.next();
			
			// Ignore the header row
			if (row.getRowNum() == 0)
				continue;
			
			S info = parseRow(row);
			if (info != null) {
				infos.addInfo(info);
			}
		}
		return true;
	}
	
	/**
	 * Takes a row from the spreadsheet and parses it into an info of the appropriate type. This method
	 * needs to be implemented by the concrete classes for each specific info type
	 *  
	 * @param row {@link Row} containing the data on a single info object
	 * @return instance of a subclass of {@link IInfo}
	 */
	protected abstract S parseRow(Row row);

	/**
	 * @return the formatter
	 */
	public IXmlFormatter getFormatter() {
		return formatter;
	}

	/**
	 * @param formatter the formatter to set
	 */
	public void setFormatter(IXmlFormatter formatter) {
		this.formatter = formatter;
	}
}
