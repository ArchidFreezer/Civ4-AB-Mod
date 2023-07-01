package org.archid.civ4.info.tech;

import org.archid.civ4.info.IInfoWorkbook;
import org.archid.utils.StringUtils;

public interface ITechWorkbook extends IInfoWorkbook {

	public static final String SHEETNAME_TREE = "TechTree";
	public static final String SHEETNAME_LIST = "TechList";

	public enum SheetHeaders {
		TYPE, DESCRIPTION, CIVILOPEDIA, HELP, STRATEGY, ADVISOR, A_I_WEIGHT, A_I_TRADE_MODIFIER, COST, ADVANCED_START_COST, ADVANCED_START_COST_INCREASE, ERA, CIV_SETTLED, FREE_CAPITAL_BUILDING_CLASS, 
		FIRST_FREE_UNIT_CLASS, FREE_UNIT_CLASS, FEATURE_PRODUCTION_MODIFIER, WORKER_SPEED_MODIFIER, TRADE_ROUTES, HEALTH, HAPPINESS, FIRST_FREE_TECHS, ASSET, POWER, REPEAT, TRADE, 
		EMBASSY_TRADING, FREE_TRADE_AGREEMENT_TRADING, NON_AGGRESSION_TRADING, DISABLE, GOODY_TECH, EXTRA_WATER_SEE_FROM, MAP_CENTERING, MAP_VISIBLE, MAP_TRADING, TECH_TRADING, 
		GOLD_TRADING, OPEN_BORDERS_TRADING, LIMITED_BORDERS_TRADING, DEFENSIVE_PACT_TRADING, PERMANENT_ALLIANCE_TRADING, VASSAL_TRADING, BRIDGE_BUILDING, IRRIGATION, IGNORE_IRRIGATION, 
		WATER_WORK, CAN_PASS_PEAKS, MOVE_FAST_PEAKS, CAN_FOUND_ON_PEAKS, GRID_X, GRID_Y, DOMAIN_EXTRA_MOVES, COMMERCE_MODIFIERS, SPECIALIST_EXTRA_COMMERCES, COMMERCE_FLEXIBLE, 
		TERRAIN_TRADES, RIVER_TRADE, CAPTURE_CITIES, UNIT_RANGE_UNBOUND, UNIT_TERRITORY_UNBOUND, UNIT_RANGE_CHANGE, UNIT_RANGE_MODIFIER, CULTURE_DEFENCE_MODIFIER, FOREST_PLOT_YIELD_CHANGES, 
		RIVER_PLOT_YIELD_CHANGES, SEA_PLOT_YIELD_CHANGES, WORLD_VIEW_REVOLT_TURN_CHANGES, FLAVORS, OR_PRE_REQS, AND_PRE_REQS, ENABLED_WORLD_VIEWS, QUOTE, SOUND, SOUND_M_P, BUTTON;

		@Override
		public String toString() {
			return StringUtils.titleCaseSpace(this.name(), '_');
		}
	}
}