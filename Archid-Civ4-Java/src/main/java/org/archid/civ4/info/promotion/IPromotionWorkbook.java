package org.archid.civ4.info.promotion;

import org.archid.civ4.info.IInfoWorkbook;
import org.archid.utils.StringUtils;

public interface IPromotionWorkbook extends IInfoWorkbook {

	public static final String SHEETNAME_LIST = "PromotionList";

	public enum SheetHeaders {
		TYPE, DESCRIPTION, HELP, SOUND, LAYER_ANIMATION_PATH, PROMOTION_PREREQ, PREREQ_OR_PROMOTIONS, TECH_PREREQ, OBSOLETE_TECH, STATE_RELIGION_PREREQ, SEE_INVISIBLES, CITY_PREREQ, 
		PROMOTION_GROUP, LEADER, BLITZ, AMPHIB, RIVER, ENEMY_ROUTE, ALWAYS_HEAL, HILLS_DOUBLE_MOVE, CAN_MOVE_PEAKS, IMMUNE_TO_FIRST_STRIKES, LOYAL, SPY_RADIATION, CARRY_RELIGION, 
		ENSLAVE_COUNT_CHANGE, VISIBILITY_CHANGE, MOVES_CHANGE, MOVE_DISCOUNT_CHANGE, AIR_RANGE_CHANGE, INTERCEPT_CHANGE, EVASION_CHANGE, WITHDRAWAL_CHANGE, CARGO_CHANGE, COLLATERAL_DAMAGE_CHANGE, 
		BOMBARD_RATE_CHANGE, FIRST_STRIKES_CHANGE, CHANCE_FIRST_STRIKES_CHANGE, ENEMY_HEAL_CHANGE, NEUTRAL_HEAL_CHANGE, FRIENDLY_HEAL_CHANGE, SAME_TILE_HEAL_CHANGE, ADJACENT_TILE_HEAL_CHANGE, 
		COMBAT_PERCENT, CITY_ATTACK, CITY_DEFENSE, HILLS_ATTACK, HILLS_DEFENSE, KAMIKAZE_PERCENT, SPY_PREPARATION_MODIFIER, SPY_POISON_MODIFIER, SPY_REVOLT_CHANGE, SPY_UNHAPPY_CHANGE, 
		SPY_INTERCEPT_CHANGE, SPY_EVASION_CHANGE, SPY_DIPLO_PENALTY_CHANGE, SPY_ESCAPE_CHANGE, SPY_NUKE_CITY_CHANGE, SPY_DISABLE_POWER_CHANGE, SPY_WAR_WEARINESS_CHANGE, SPY_RESEARCH_SABOTAGE_CHANGE, 
		SPY_RELIGION_REMOVAL_CHANGE, SPY_CORPORATION_REMOVAL_CHANGE, SPY_STEAL_TREASURY_CHANGE, SPY_BUY_TECH_CHANGE, SPY_SWITCH_CIVIC_CHANGE, SPY_SWITCH_RELIGION_CHANGE, SPY_DESTROY_PROJECT_CHANGE, 
		SPY_DESTROY_BUILDING_CHANGE, SPY_DESTROY_IMPROVEMENT_CHANGE, SPY_DESTROY_PRODUCTION_CHANGE, SPY_CULTURE_CHANGE, REVOLT_PROTECTION, COLLATERAL_DAMAGE_PROTECTION, PILLAGE_CHANGE, 
		PLUNDER_CHANGE, EXTRA_MORALE, ENEMY_MORALE_MODIFIER, UPGRADE_DISCOUNT, EXPERIENCE_PERCENT, WORK_RATE_MODIFIER, UNIT_RANGE_UNBOUND, UNIT_TERRITORY_UNBOUND, UNIT_RANGE_CHANGE, 
		UNIT_RANGE_MODIFIER, TERRAIN_ATTACKS, TERRAIN_DEFENSES, FEATURE_ATTACKS, FEATURE_DEFENSES, UNIT_COMBAT_MODS, DOMAIN_MODS, TERRAIN_DOUBLE_MOVES, FEATURE_DOUBLE_MOVES, 
		BUILD_LEAVE_FEATURES, NOT_UNIT_COMBAT_TYPES, OR_UNIT_COMBAT_TYPES, HOT_KEY, ALT_DOWN, SHIFT_DOWN, CTRL_DOWN, HOT_KEY_PRIORITY, BUTTON, ORDER_PRIORITY;

		@Override
		public String toString() {
			return StringUtils.titleCaseSpace(this.name(), '_');
		}
	}
}