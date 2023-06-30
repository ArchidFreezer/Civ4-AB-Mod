package org.archid.civ4.info;

import org.apache.log4j.Logger;
import org.archid.civ4.info.bonus.BonusImporter;
import org.archid.civ4.info.bonusclass.BonusClassImporter;
import org.archid.civ4.info.building.BuildingImporter;
import org.archid.civ4.info.buildingclass.BuildingClassImporter;
import org.archid.civ4.info.civic.CivicImporter;
import org.archid.civ4.info.era.EraImporter;
import org.archid.civ4.info.event.EventImporter;
import org.archid.civ4.info.eventtrigger.EventTriggerImporter;
import org.archid.civ4.info.tech.TechImporter;
import org.archid.civ4.info.unit.UnitImporter;

public class ImporterFactory {

	/** Logging facility */
	static Logger log = Logger.getLogger(ImporterFactory.class.getName());
	
	public static IImporter getImporter(EInfo infoType) {
		IImporter importer = null;
		switch (infoType) {
		case BONUS:
			importer = new BonusImporter(infoType);
			break;
		case BONUS_CLASS:
			importer = new BonusClassImporter(infoType);
			break;
		case BUILDING:
			importer = new BuildingImporter(infoType);
			break;
		case BUILDING_CLASS:
			importer = new BuildingClassImporter(infoType);
			break;
		case CIVIC:
			importer = new CivicImporter(infoType);
			break;
		case ERA:
			importer = new EraImporter(infoType);
			break;
		case EVENT:
			importer = new EventImporter(infoType);
			break;
		case EVENT_TRIGGER:
			importer = new EventTriggerImporter(infoType);
			break;
		case TECH:
			importer = new TechImporter(infoType);
			break;
		case UNIT:
			importer = new UnitImporter(infoType);
			break;
		default:
			log.error("Unknown importer requested");
			break;
		}
		return importer;
	}

}
