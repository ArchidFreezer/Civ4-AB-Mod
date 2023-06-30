package org.archid.civ4.info;

import org.apache.log4j.Logger;
import org.archid.civ4.info.bonus.BonusExporter;
import org.archid.civ4.info.bonusclass.BonusClassExporter;
import org.archid.civ4.info.building.BuildingExporter;
import org.archid.civ4.info.buildingclass.BuildingClassExporter;
import org.archid.civ4.info.civic.CivicExporter;
import org.archid.civ4.info.era.EraExporter;
import org.archid.civ4.info.event.EventExporter;
import org.archid.civ4.info.eventtrigger.EventTriggerExporter;
import org.archid.civ4.info.tech.TechExporter;
import org.archid.civ4.info.unit.UnitExporter;

public class ExporterFactory {

	/** Logging facility */
	static Logger log = Logger.getLogger(ExporterFactory.class.getName());
	
	public static IExporter getExporter(EInfo infoType) {
		IExporter exporter = null;
		switch (infoType) {
		case BONUS:
			exporter = new BonusExporter(infoType);
			break;
		case BONUS_CLASS:
			exporter = new BonusClassExporter(infoType);
			break;
		case BUILDING:
			exporter = new BuildingExporter(infoType);
			break;
		case BUILDING_CLASS:
			exporter = new BuildingClassExporter(infoType);
			break;
		case CIVIC:
			exporter = new CivicExporter(infoType);
			break;
		case ERA:
			exporter = new EraExporter(infoType);
			break;
		case EVENT:
			exporter = new EventExporter(infoType);
			break;
		case EVENT_TRIGGER:
			exporter = new EventTriggerExporter(infoType);
			break;
		case TECH:
			exporter = new TechExporter(infoType);
			break;
		case UNIT:
			exporter = new UnitExporter(infoType);
			break;
		default:
			log.error("Unknown exporter requested");
			break;
		}
		return exporter;
	}

}
