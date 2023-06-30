package org.archid.civ4.info.bonus;

import org.archid.civ4.info.IInfoWorkbook;
import org.archid.utils.StringUtils;

public interface IBonusWorkbook extends IInfoWorkbook {

	public static final String SHEETNAME_LIST = "BonusList";

	public enum SheetHeaders {
		TYPE, DESCRIPTION, CIVILOPEDIA, HELP, BONUS_CLASS_TYPE, ART_DEFINE_TAG, TECH_REVEAL, TECH_CITY_TRADE, TECH_OBSOLETE, YIELD_CHANGES, A_I_TRADE_MODIFIER, A_I_OBJECTIVE, HEALTH, 
		HAPPINESS, PLACEMENT_ORDER, CONST_APPEARANCE, MIN_AREA_SIZE, MIN_LATITUDE, MAX_LATITUDE, RANDS, PLAYER, TILES_PER, MIN_LAND_PERCENT, UNIQUE, GROUP_RANGE, GROUP_RAND, 
		AREA, HILLS, PEAKS, FLATLANDS, NO_RIVER_SIDE, NORMALIZE, TERRAIN_BOOLEANS, FEATURE_BOOLEANS, FEATURE_TERRAIN_BOOLEANS, USE_L_SYSTEM;

		@Override
		public String toString() {
			return StringUtils.titleCaseSpace(this.name(), '_');
		}
	}
}