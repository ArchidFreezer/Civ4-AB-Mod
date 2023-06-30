package org.archid.civ4.info.unit;

import org.archid.civ4.info.IInfoWorkbook;
import org.archid.utils.StringUtils;

public interface IUnitWorkbook extends IInfoWorkbook {

	public static final String SHEETNAME_LIST = "UnitList";

	public enum SheetHeaders {
		CLAZZ, TYPE, UNIQUE_NAMES, SPECIAL, CAPTURE, COMBAT, SUB_COMBAT_TYPES, DOMAIN, DEFAULT_UNIT_A_I, FIXED_A_I, MAX_WEAPON_TYPE_TIER, MAX_AMMO_TYPE_TIER, INVISIBLE, SEE_INVISIBLES, 
		DESCRIPTION, CIVILOPEDIA, STRATEGY, HELP, ADVISOR, ANIMAL, FOOD, NO_BAD_GOODIES, ONLY_DEFENSIVE, NO_CAPTURE, QUICK_COMBAT, RIVAL_TERRITORY, MILITARY_HAPPINESS, MILITARY_SUPPORT, 
		MILITARY_PRODUCTION, PILLAGE, SPY, SABOTAGE, DESTROY, STEAL_PLANS, INVESTIGATE, COUNTER_SPY, FOUND, GOLDEN_AGE, INVISIBLE_BOOL, FIRST_STRIKE_IMMUNE, NO_DEFENSIVE_BONUS, 
		IGNORE_BUILDING_DEFENSE, CAN_MOVE_IMPASSABLE, CAN_MOVE_ALL_TERRAIN, FLAT_MOVEMENT_COST, IGNORE_TERRAIN_COST, NUKE_IMMUNE, PREREQ_BONUSES, PREREQ_RELIGION_BOOL, MECHANIZED, 
		RENDER_BELOW_WATER, RENDER_ALWAYS, SUICIDE, SINGLE_BUILD, SLAVE, LINE_OF_SIGHT, HIDDEN_NATIONALITY, ALWAYS_HOSTILE, WORKER_TRADE, MILITARY_TRADE, BARBARIAN_LEADER, CITY_PACIFIER, 
		UNIT_CLASS_UPGRADES, UNIT_CLASS_TARGETS, UNIT_COMBAT_TARGETS, UNIT_CLASS_DEFENDERS, UNIT_COMBAT_DEFENDERS, FLANKING_STRIKES, UNIT_A_IS, NOT_UNIT_A_IS, BUILDS, RELIGION_SPREADS, 
		CORPORATION_SPREADS, GREAT_PEOPLES, SLAVE_SPECIALIST_TYPE, BUILDINGS, FORCE_BUILDINGS, HOLY_CITY, RELIGION_TYPE, STATE_RELIGION, PREREQ_RELIGION, PREREQ_CORPORATION, 
		PREREQ_BUILDING, PREREQ_TECHS, OBSOLETE_TECH, BONUS_TYPE, PREREQ_OR_BONUSES, PREREQ_AND_CIVICS, PREREQ_OR_CIVICS, PREREQ_AND_TERRAINS, PREREQ_OR_TERRAINS, PREREQ_OR_BUILDING_CLASSES, 
		PREREQ_NOT_BUILDING_CLASSES, PREREQ_VICINITY_AND_BONUS, PREREQ_VICINITY_OR_BONUS, PREREQ_VICINITY_IMPROVEMENTS, PREREQ_VICINITY_FEATURES, PREREQ_WORLD_VIEWS, MIN_POPULATION, 
		MIN_CULTURE_LEVEL, PREREQ_POWER, PRODUCTION_TRAITS, FLAVORS, A_I_WEIGHT, COST, HURRY_COST_MODIFIER, ADVANCED_START_COST, ADVANCED_START_COST_INCREASE, MIN_AREA_SIZE, 
		MORALE, MOVES, UNIT_RANGE_TYPE, NO_REVEAL_MAP, AIR_RANGE, AIR_UNIT_CAP, DROP_RANGE, NUKE_RANGE, WORK_RATE, BASE_DISCOVER, DISCOVER_MULTIPLIER, BASE_HURRY, HURRY_MULTIPLIER, 
		BASE_TRADE, TRADE_MULTIPLIER, GREAT_WORK_CULTURE, ESPIONAGE_POINTS, GREAT_JEST_HAPPINESS, GREAT_JEST_DURATION, TERRAIN_IMPASSABLES, FEATURE_IMPASSABLES, TERRAIN_PASSABLE_TECHS, 
		FEATURE_PASSABLE_TECHS, COMBAT_INT, COMBAT_LIMIT, AIR_COMBAT, AIR_COMBAT_LIMIT, X_P_VALUE_ATTACK, X_P_VALUE_DEFENSE, FIRST_STRIKES, CHANCE_FIRST_STRIKES, INTERCEPTION_PROBABILITY, 
		EVASION_PROBABILITY, WITHDRAWAL_PROB, COLLATERAL_DAMAGE, COLLATERAL_DAMAGE_LIMIT, COLLATERAL_DAMAGE_MAX_UNITS, CITY_ATTACK, CITY_DEFENSE, ANIMAL_COMBAT, HILLS_ATTACK, 
		HILLS_DEFENSE, TERRAIN_NATIVES, FEATURE_NATIVES, TERRAIN_ATTACKS, TERRAIN_DEFENSES, FEATURE_ATTACKS, FEATURE_DEFENSES, UNIT_CLASS_ATTACK_MODS, UNIT_CLASS_DEFENSE_MODS, 
		UNIT_COMBAT_MODS, UNIT_COMBAT_COLLATERAL_IMMUNES, DOMAIN_MODS, BONUS_PRODUCTION_MODIFIERS, YIELDS_FROM_KILL, COMMERCES_FROM_KILL, BOMB_RATE, BOMBARD_RATE, SPECIAL_CARGO, 
		DOMAIN_CARGO, CARGO, CONSCRIPTION, CULTURE_GARRISON, EXTRA_COST, ASSET, POWER, UNIT_MESH_GROUPS, FORMATION_TYPE, HOT_KEY, ALT_DOWN, SHIFT_DOWN, CTRL_DOWN, HOT_KEY_PRIORITY, 
		FREE_PROMOTIONS, LEADER_PROMOTION, LEADER_EXPERIENCE, ORDER_PRIORITY;

		@Override
		public String toString() {
			return StringUtils.titleCaseSpace(this.name(), '_');
		}
	}
}