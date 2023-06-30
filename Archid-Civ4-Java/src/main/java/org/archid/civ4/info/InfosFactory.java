package org.archid.civ4.info;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;
import org.archid.civ4.info.bonus.BonusInfos;
import org.archid.civ4.info.bonusclass.BonusClassInfos;
import org.archid.civ4.info.building.BuildingInfos;
import org.archid.civ4.info.buildingclass.BuildingClassInfos;
import org.archid.civ4.info.civic.CivicInfos;
import org.archid.civ4.info.era.EraInfos;
import org.archid.civ4.info.event.EventInfos;
import org.archid.civ4.info.eventtrigger.EventTriggerInfos;
import org.archid.civ4.info.tech.TechInfos;
import org.archid.civ4.info.unit.UnitInfos;

public class InfosFactory {
	
	/** Logging facility */
	static Logger log = Logger.getLogger(InfosFactory.class.getName());
	
	@SuppressWarnings("unchecked")
	public static <T extends IInfos<S>, S extends IInfo> T getInfos(EInfo infoType) {
		T infos = null;
		switch(infoType) {
		case BONUS:
			infos = (T) new BonusInfos();
			break;
		case BONUS_CLASS:
			infos = (T) new BonusClassInfos();
			break;
		case BUILDING:
			infos = (T) new BuildingInfos();
			break;
		case BUILDING_CLASS:
			infos = (T) new BuildingClassInfos();
			break;
		case CIVIC:
			infos = (T) new CivicInfos();
			break;
		case ERA:
			infos = (T) new EraInfos();
			break;
		case EVENT:
			infos = (T) new EventInfos();
			break;
		case EVENT_TRIGGER:
			infos = (T) new EventTriggerInfos();
			break;
		case TECH:
			infos = (T) new TechInfos();
			break;
		case UNIT:
			infos = (T) new UnitInfos();
			break;
		default:
			log.error("Error getting infos file: unknown info type");
			break;
		}
		return infos;
	}

	private static JAXBContext getContext(EInfo infoType) throws JAXBException {
		JAXBContext jaxbContext = null;
		switch (infoType) {
		case BONUS:
			jaxbContext = JAXBContext.newInstance(BonusInfos.class);
			break;
		case BONUS_CLASS:
			jaxbContext = JAXBContext.newInstance(BonusClassInfos.class);
			break;
		case BUILDING:
			jaxbContext = JAXBContext.newInstance(BuildingInfos.class);
			break;
		case BUILDING_CLASS:
			jaxbContext = JAXBContext.newInstance(BuildingClassInfos.class);
			break;
		case CIVIC:
			jaxbContext = JAXBContext.newInstance(CivicInfos.class);
			break;
		case ERA:
			jaxbContext = JAXBContext.newInstance(EraInfos.class);
			break;
		case EVENT:
			jaxbContext = JAXBContext.newInstance(EventInfos.class);
			break;
		case EVENT_TRIGGER:
			jaxbContext = JAXBContext.newInstance(EventTriggerInfos.class);
			break;
		case TECH:
			jaxbContext = JAXBContext.newInstance(TechInfos.class);
			break;
		case UNIT:
			jaxbContext = JAXBContext.newInstance(UnitInfos.class);
			break;
		default:
			log.error("Error reading infos file: unknown info type");
			break;
		}
		return jaxbContext;
	}
	
	@SuppressWarnings("unchecked")
	public static <T extends IInfos<S>, S extends IInfo> T readInfos(EInfo infoType, String xmlPath) {

		T infos = null;

		try {
			// Initialise the context
			JAXBContext jaxbContext = getContext(infoType);
			
			// Read the infos
			if (jaxbContext != null) {
				File input = new File(xmlPath);
				Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
				infos = (T) jaxbUnmarshaller.unmarshal(input);
			}
		} catch (JAXBException e) {
			log.error("Error unmarshalling the xml file", e);
		}
		return infos;
	}
	
	public static <T extends IInfos<S>, S extends IInfo> void writeInfos(EInfo infoType, String xmlPath, T infos) {
		try {
			// Initialise the context
			JAXBContext jaxbContext = getContext(infoType);
			
			// write the infos
			if (jaxbContext != null) {
				File output = new File(xmlPath);
				Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
				jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
				jaxbMarshaller.setProperty("com.sun.xml.internal.bind.xmlHeaders", "<?xml version=\"1.0\"?>");
				jaxbMarshaller.marshal(infos, output);
			}
					
		} catch (JAXBException e) {
			log.error("Error marshalling the xml file", e);
		}
	}
}
