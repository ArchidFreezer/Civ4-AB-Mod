package org.archid.civ4.info.eventtrigger;

import org.archid.civ4.info.IInfoWorkbook;
import org.archid.utils.StringUtils;

public interface IEventTriggerWorkbook extends IInfoWorkbook {

	public static final String SHEETNAME_LIST = "EventTriggerList";

	public enum SheetHeaders {
		TYPE, WORLD_NEWS_TEXTS, TRIGGER_TEXTS, SINGLE_PLAYER, PERCENT_GAMES_ACTIVE, WEIGHT, PROBABILITY_UNIT_MULTIPLY, PROBABILITY_BUILDING_MULTIPLY, CIVIC, MIN_TREASURY, MIN_POPULATION, 
		MAX_POPULATION, MIN_MAP_LANDMASS, MIN_OUR_LANDMASS, MAX_OUR_LANDMASS, MIN_DIFFICULTY, ANGRY, UNHEALTHY, UNITS_REQUIRED, NUM_UNITS, NUM_UNITS_GLOBAL, UNIT_DAMAGED_WEIGHT, 
		UNIT_DISTANCE_WEIGHT, UNIT_EXPERIENCE_WEIGHT, UNITS_ON_PLOT, BUILDINGS_REQUIRED, NUM_BUILDINGS, NUM_BUILDINGS_GLOBAL, NUM_PLOTS_REQUIRED, OWN_PLOT, PLOT_TYPE, FEATURES_REQUIRED, 
		TERRAINS_REQUIRED, IMPROVEMENTS_REQUIRED, BONUSES_REQUIRED, ROUTES_REQUIRED, RELIGIONS_REQUIRED, NUM_RELIGIONS, CORPORATIONS_REQUIRED, NUM_CORPORATIONS, PICK_RELIGION, 
		STATE_RELIGION, HOLY_CITY, PICK_CORPORATION, HEADQUARTERS, EVENTS, PREREQ_EVENTS, PREREQ_EVENT_PLOT, OR_PRE_REQS, AND_PRE_REQS, OBSOLETE_TECHS, RECURRING, TEAM, GLOBAL, 
		PICK_PLAYER, OTHER_PLAYER_WAR, OTHER_PLAYER_HAS_RELIGION, OTHER_PLAYER_HAS_OTHER_RELIGION, OTHER_PLAYER_A_I, OTHER_PLAYER_SHARE_BORDERS, OTHER_PLAYER_HAS_TECH, PICK_CITY, 
		PICK_OTHER_PLAYER_CITY, SHOW_PLOT, CITY_FOOD_WEIGHT, PYTHON_CAN_DO, PYTHON_CAN_DO_CITY, PYTHON_CAN_DO_UNIT, PYTHON_CALLBACK;

		@Override
		public String toString() {
			return StringUtils.titleCaseSpace(this.name(), '_');
		}
	}
}