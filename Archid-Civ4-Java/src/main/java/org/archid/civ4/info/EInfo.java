package org.archid.civ4.info;

public enum EInfo {
	BONUS("Terrain", "CIV4BonusInfos.xml"),
	BONUS_CLASS("Terrain", "CIV4BonusClassInfos.xml"),
	BUILDING("Buildings", "CIV4BuildingInfos.xml"), 
	BUILDING_CLASS("Buildings", "CIV4BuildingClassInfos.xml"),
	CIVIC("GameInfo", "CIV4CivicInfos.xml"),
	ERA("GameInfo", "CIV4EraInfos.xml"),
	EVENT("Events", "CIV4EventInfos.xml"),
	EVENT_TRIGGER("Events", "CIV4EventTriggerInfos.xml"),
	TECH("Technologies", "CIV4TechInfos.xml"),
	UNIT("Units", "CIV4UnitInfos.xml"),
	UNKNOWN("unknown", "UnknownInfo");
	
	private final String file;
	private final String folder;
	
	EInfo(String folder, String file) {
		this.folder = folder;
		this.file = file;
	}
	
	@Override
	public String toString() {
		return file;
	}
	
	public String getFolder() {
		return folder;
	}
	
	public static boolean containsFile(String v) {
		for (EInfo c: EInfo.values()) {
			if (c.file.equals(v)) {
				return true;
			}
		}
		return false;
	}
	
	public static EInfo fromFile(String v) {
		for (EInfo c: EInfo.values()) {
			if (c.file.equals(v)) {
				return c;
			}
		}
		return UNKNOWN;
	}
}