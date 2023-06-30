package org.archid.civ4.info;

import org.apache.log4j.Logger;
import org.archid.civ4.info.bonus.BonusConverter;
import org.archid.civ4.info.building.BuildingConverter;
import org.archid.civ4.info.building.KBuildingConverter;
import org.archid.civ4.info.unit.UnitConverter;

public class ConverterFactory {

	/** Logging facility */
	static Logger log = Logger.getLogger(ConverterFactory.class.getName());
	
	public static IConverter getConverter(EInfo infoType) {
		IConverter converter = null;
		switch(infoType) {
		case BONUS:
			converter = new BonusConverter();
			break;
		case BUILDING:
			converter = new KBuildingConverter();
			break;
		case UNIT:
			converter = new UnitConverter();
			break;
		default:
			log.error("Unknown converter requested");
			break;
		}
		
		return converter;
	}

}
