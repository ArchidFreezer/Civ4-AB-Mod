package org.archid.civ4.java;

import org.archid.civ4.java.infoprocessor.BonusInfoProcessor;
import org.archid.civ4.java.infoprocessor.BuildingClassInfoProcessor;
import org.archid.civ4.java.infoprocessor.BuildingInfoProcessor;
import org.archid.civ4.java.infoprocessor.CivicInfoProcessor;
import org.archid.civ4.java.infoprocessor.DefaultInfoProcessor;
import org.archid.civ4.java.infoprocessor.EraInfoProcessor;
import org.archid.civ4.java.infoprocessor.IInfoProcessor;
import org.archid.civ4.java.infoprocessor.TechInfoProcessor;
import org.archid.civ4.java.infoprocessor.UnitInfoProcessor;

public class InfoProcessorFactory {
	
	public static IInfoProcessor getProcessor(String infoName) {
		if (infoName.equalsIgnoreCase("BonusInfo")) {
			return new BonusInfoProcessor();
		} else if (infoName.equalsIgnoreCase("BuildingClassInfo")) {
			return new BuildingClassInfoProcessor();
		} else if (infoName.equalsIgnoreCase("BuildingInfo")) {
			return new BuildingInfoProcessor();
		} else if (infoName.equalsIgnoreCase("CivicInfo")) {
			return new CivicInfoProcessor();
		} else if (infoName.equalsIgnoreCase("EraInfo")) {
			return new EraInfoProcessor();
		} else if (infoName.equalsIgnoreCase("TechInfo")) {
			return new TechInfoProcessor();
		} else if (infoName.equalsIgnoreCase("UnitInfo")) {
			return new UnitInfoProcessor();
		} else {
			return new DefaultInfoProcessor();
		}
	}

}
