package org.archid.civ4.info.unit;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.archid.civ4.info.AbstractExporter;
import org.archid.civ4.info.EInfo;
import org.archid.civ4.info.IInfoWorkbook;
import org.archid.civ4.info.IInfos;
import org.archid.civ4.info.unit.IUnitWorkbook.SheetHeaders;
import org.archid.civ4.info.unit.UnitMeshGroups.UnitMeshGroup;

public class UnitExporter extends AbstractExporter<IInfos<IUnitInfo>, IUnitInfo> {

	/** Logging facility */
	static Logger log = Logger.getLogger(UnitExporter.class.getName());

	public UnitExporter(EInfo infoEnum) {
		super(infoEnum);
	}

	@Override
	public List<String> getHeaders() {
		List<String> headers = new ArrayList<String>();
		for (SheetHeaders header: SheetHeaders.values()) {
			headers.add(header.toString());
		}
		return headers;
	}

	@Override
	protected int getNumCols() {
		return IUnitWorkbook.SheetHeaders.values().length;
	}

	@Override
	protected String getInfoListSheetName() {
		return IUnitWorkbook.SHEETNAME_LIST;
	}

	@Override
	protected void populateRow(Row row, IUnitInfo info) {
		int maxHeight = 1;
		int colNum = 0;
		addSingleCell(row.createCell(colNum++), info.getClazz());
		addSingleCell(row.createCell(colNum++), info.getType());
		maxHeight = addRepeatingCell(row.createCell(colNum++), info.getUniqueNames(), maxHeight);
		addSingleCell(row.createCell(colNum++), info.getSpecial());
		addSingleCell(row.createCell(colNum++), info.getCapture());
		addSingleCell(row.createCell(colNum++), info.getCombat());
		maxHeight = addRepeatingCell(row.createCell(colNum++), info.getSubCombatTypes(), maxHeight);
		addSingleCell(row.createCell(colNum++), info.getDomain());
		addSingleCell(row.createCell(colNum++), info.getDefaultUnitAI());
		addSingleCell(row.createCell(colNum++), info.isFixedAI());
		addSingleCell(row.createCell(colNum++), info.getMaxWeaponTypeTier());
		addSingleCell(row.createCell(colNum++), info.getMaxAmmoTypeTier());
		addSingleCell(row.createCell(colNum++), info.getInvisible());
		maxHeight = addRepeatingCell(row.createCell(colNum++), info.getSeeInvisibles(), maxHeight);
		addSingleCell(row.createCell(colNum++), info.getDescription());
		addSingleCell(row.createCell(colNum++), info.getCivilopedia());
		addSingleCell(row.createCell(colNum++), info.getStrategy());
		addSingleCell(row.createCell(colNum++), info.getHelp());
		addSingleCell(row.createCell(colNum++), info.getAdvisor());
		addSingleCell(row.createCell(colNum++), info.isAnimal());
		addSingleCell(row.createCell(colNum++), info.isFood());
		addSingleCell(row.createCell(colNum++), info.isNoBadGoodies());
		addSingleCell(row.createCell(colNum++), info.isOnlyDefensive());
		addSingleCell(row.createCell(colNum++), info.isNoCapture());
		addSingleCell(row.createCell(colNum++), info.isQuickCombat());
		addSingleCell(row.createCell(colNum++), info.isRivalTerritory());
		addSingleCell(row.createCell(colNum++), info.isMilitaryHappiness());
		addSingleCell(row.createCell(colNum++), info.isMilitarySupport());
		addSingleCell(row.createCell(colNum++), info.isMilitaryProduction());
		addSingleCell(row.createCell(colNum++), info.isPillage());
		addSingleCell(row.createCell(colNum++), info.isSpy());
		addSingleCell(row.createCell(colNum++), info.isSabotage());
		addSingleCell(row.createCell(colNum++), info.isDestroy());
		addSingleCell(row.createCell(colNum++), info.isStealPlans());
		addSingleCell(row.createCell(colNum++), info.isInvestigate());
		addSingleCell(row.createCell(colNum++), info.isCounterSpy());
		addSingleCell(row.createCell(colNum++), info.isFound());
		addSingleCell(row.createCell(colNum++), info.isGoldenAge());
		addSingleCell(row.createCell(colNum++), info.isInvisibleBool());
		addSingleCell(row.createCell(colNum++), info.isFirstStrikeImmune());
		addSingleCell(row.createCell(colNum++), info.isNoDefensiveBonus());
		addSingleCell(row.createCell(colNum++), info.isIgnoreBuildingDefense());
		addSingleCell(row.createCell(colNum++), info.isCanMoveImpassable());
		addSingleCell(row.createCell(colNum++), info.isCanMoveAllTerrain());
		addSingleCell(row.createCell(colNum++), info.isFlatMovementCost());
		addSingleCell(row.createCell(colNum++), info.isIgnoreTerrainCost());
		addSingleCell(row.createCell(colNum++), info.isNukeImmune());
		addSingleCell(row.createCell(colNum++), info.isPrereqBonuses());
		addSingleCell(row.createCell(colNum++), info.isPrereqReligionBool());
		addSingleCell(row.createCell(colNum++), info.isMechanized());
		addSingleCell(row.createCell(colNum++), info.isRenderBelowWater());
		addSingleCell(row.createCell(colNum++), info.isRenderAlways());
		addSingleCell(row.createCell(colNum++), info.isSuicide());
		addSingleCell(row.createCell(colNum++), info.isSingleBuild());
		addSingleCell(row.createCell(colNum++), info.isSlave());
		addSingleCell(row.createCell(colNum++), info.isLineOfSight());
		addSingleCell(row.createCell(colNum++), info.isHiddenNationality());
		addSingleCell(row.createCell(colNum++), info.isAlwaysHostile());
		addSingleCell(row.createCell(colNum++), info.isWorkerTrade());
		addSingleCell(row.createCell(colNum++), info.isMilitaryTrade());
		addSingleCell(row.createCell(colNum++), info.isBarbarianLeader());
		addSingleCell(row.createCell(colNum++), info.isCityPacifier());
		maxHeight = addRepeatingCell(row.createCell(colNum++), info.getUnitClassUpgrades(), maxHeight);
		maxHeight = addRepeatingCell(row.createCell(colNum++), info.getUnitClassTargets(), maxHeight);
		maxHeight = addRepeatingCell(row.createCell(colNum++), info.getUnitCombatTargets(), maxHeight);
		maxHeight = addRepeatingCell(row.createCell(colNum++), info.getUnitClassDefenders(), maxHeight);
		maxHeight = addRepeatingCell(row.createCell(colNum++), info.getUnitCombatDefenders(), maxHeight);
		maxHeight = addRepeatingPairCell(row.createCell(colNum++), info.getFlankingStrikes(), maxHeight);
		maxHeight = addRepeatingCell(row.createCell(colNum++), info.getUnitAIs(), maxHeight);
		maxHeight = addRepeatingCell(row.createCell(colNum++), info.getNotUnitAIs(), maxHeight);
		maxHeight = addRepeatingCell(row.createCell(colNum++), info.getBuilds(), maxHeight);
		maxHeight = addRepeatingPairCell(row.createCell(colNum++), info.getReligionSpreads(), maxHeight);
		maxHeight = addRepeatingPairCell(row.createCell(colNum++), info.getCorporationSpreads(), maxHeight);
		maxHeight = addRepeatingCell(row.createCell(colNum++), info.getGreatPeoples(), maxHeight);
		addSingleCell(row.createCell(colNum++), info.getSlaveSpecialistType());
		maxHeight = addRepeatingCell(row.createCell(colNum++), info.getBuildings(), maxHeight);
		maxHeight = addRepeatingCell(row.createCell(colNum++), info.getForceBuildings(), maxHeight);
		addSingleCell(row.createCell(colNum++), info.getHolyCity());
		addSingleCell(row.createCell(colNum++), info.getReligionType());
		addSingleCell(row.createCell(colNum++), info.getStateReligion());
		addSingleCell(row.createCell(colNum++), info.getPrereqReligion());
		addSingleCell(row.createCell(colNum++), info.getPrereqCorporation());
		addSingleCell(row.createCell(colNum++), info.getPrereqBuilding());
		maxHeight = addRepeatingCell(row.createCell(colNum++), info.getPrereqTechs(), maxHeight);
		addSingleCell(row.createCell(colNum++), info.getObsoleteTech());
		addSingleCell(row.createCell(colNum++), info.getBonusType());
		maxHeight = addRepeatingCell(row.createCell(colNum++), info.getPrereqOrBonuses(), maxHeight);
		maxHeight = addRepeatingCell(row.createCell(colNum++), info.getPrereqAndCivics(), maxHeight);
		maxHeight = addRepeatingCell(row.createCell(colNum++), info.getPrereqOrCivics(), maxHeight);
		maxHeight = addRepeatingCell(row.createCell(colNum++), info.getPrereqAndTerrains(), maxHeight);
		maxHeight = addRepeatingCell(row.createCell(colNum++), info.getPrereqOrTerrains(), maxHeight);
		maxHeight = addRepeatingCell(row.createCell(colNum++), info.getPrereqOrBuildingClasses(), maxHeight);
		maxHeight = addRepeatingCell(row.createCell(colNum++), info.getPrereqNotBuildingClasses(), maxHeight);
		maxHeight = addRepeatingCell(row.createCell(colNum++), info.getPrereqVicinityAndBonus(), maxHeight);
		maxHeight = addRepeatingCell(row.createCell(colNum++), info.getPrereqVicinityOrBonus(), maxHeight);
		maxHeight = addRepeatingCell(row.createCell(colNum++), info.getPrereqVicinityImprovements(), maxHeight);
		maxHeight = addRepeatingCell(row.createCell(colNum++), info.getPrereqVicinityFeatures(), maxHeight);
		maxHeight = addRepeatingCell(row.createCell(colNum++), info.getPrereqWorldViews(), maxHeight);
		addSingleCell(row.createCell(colNum++), info.getMinPopulation());
		addSingleCell(row.createCell(colNum++), info.getMinCultureLevel());
		addSingleCell(row.createCell(colNum++), info.isPrereqPower());
		maxHeight = addRepeatingPairCell(row.createCell(colNum++), info.getProductionTraits(), maxHeight);
		maxHeight = addRepeatingPairCell(row.createCell(colNum++), info.getFlavors(), maxHeight);
		addSingleCell(row.createCell(colNum++), info.getAIWeight());
		addSingleCell(row.createCell(colNum++), info.getCost());
		addSingleCell(row.createCell(colNum++), info.getHurryCostModifier());
		addSingleCell(row.createCell(colNum++), info.getAdvancedStartCost());
		addSingleCell(row.createCell(colNum++), info.getAdvancedStartCostIncrease());
		addSingleCell(row.createCell(colNum++), info.getMinAreaSize());
		addSingleCell(row.createCell(colNum++), info.getMorale());
		addSingleCell(row.createCell(colNum++), info.getMoves());
		addSingleCell(row.createCell(colNum++), info.getUnitRangeType());
		addSingleCell(row.createCell(colNum++), info.isNoRevealMap());
		addSingleCell(row.createCell(colNum++), info.getAirRange());
		addSingleCell(row.createCell(colNum++), info.getAirUnitCap());
		addSingleCell(row.createCell(colNum++), info.getDropRange());
		addSingleCell(row.createCell(colNum++), info.getNukeRange());
		addSingleCell(row.createCell(colNum++), info.getWorkRate());
		addSingleCell(row.createCell(colNum++), info.getBaseDiscover());
		addSingleCell(row.createCell(colNum++), info.getDiscoverMultiplier());
		addSingleCell(row.createCell(colNum++), info.getBaseHurry());
		addSingleCell(row.createCell(colNum++), info.getHurryMultiplier());
		addSingleCell(row.createCell(colNum++), info.getBaseTrade());
		addSingleCell(row.createCell(colNum++), info.getTradeMultiplier());
		addSingleCell(row.createCell(colNum++), info.getGreatWorkCulture());
		addSingleCell(row.createCell(colNum++), info.getEspionagePoints());
		addSingleCell(row.createCell(colNum++), info.getGreatJestHappiness());
		addSingleCell(row.createCell(colNum++), info.getGreatJestDuration());
		maxHeight = addRepeatingCell(row.createCell(colNum++), info.getTerrainImpassables(), maxHeight);
		maxHeight = addRepeatingCell(row.createCell(colNum++), info.getFeatureImpassables(), maxHeight);
		maxHeight = addRepeatingPairCell(row.createCell(colNum++), info.getTerrainPassableTechs(), maxHeight);
		maxHeight = addRepeatingPairCell(row.createCell(colNum++), info.getFeaturePassableTechs(), maxHeight);
		addSingleCell(row.createCell(colNum++), info.getCombatInt());
		addSingleCell(row.createCell(colNum++), info.getCombatLimit());
		addSingleCell(row.createCell(colNum++), info.getAirCombat());
		addSingleCell(row.createCell(colNum++), info.getAirCombatLimit());
		addSingleCell(row.createCell(colNum++), info.getXPValueAttack());
		addSingleCell(row.createCell(colNum++), info.getXPValueDefense());
		addSingleCell(row.createCell(colNum++), info.getFirstStrikes());
		addSingleCell(row.createCell(colNum++), info.getChanceFirstStrikes());
		addSingleCell(row.createCell(colNum++), info.getInterceptionProbability());
		addSingleCell(row.createCell(colNum++), info.getEvasionProbability());
		addSingleCell(row.createCell(colNum++), info.getWithdrawalProb());
		addSingleCell(row.createCell(colNum++), info.getCollateralDamage());
		addSingleCell(row.createCell(colNum++), info.getCollateralDamageLimit());
		addSingleCell(row.createCell(colNum++), info.getCollateralDamageMaxUnits());
		addSingleCell(row.createCell(colNum++), info.getCityAttack());
		addSingleCell(row.createCell(colNum++), info.getCityDefense());
		addSingleCell(row.createCell(colNum++), info.getAnimalCombat());
		addSingleCell(row.createCell(colNum++), info.getHillsAttack());
		addSingleCell(row.createCell(colNum++), info.getHillsDefense());
		maxHeight = addRepeatingCell(row.createCell(colNum++), info.getTerrainNatives(), maxHeight);
		maxHeight = addRepeatingCell(row.createCell(colNum++), info.getFeatureNatives(), maxHeight);
		maxHeight = addRepeatingPairCell(row.createCell(colNum++), info.getTerrainAttacks(), maxHeight);
		maxHeight = addRepeatingPairCell(row.createCell(colNum++), info.getTerrainDefenses(), maxHeight);
		maxHeight = addRepeatingPairCell(row.createCell(colNum++), info.getFeatureAttacks(), maxHeight);
		maxHeight = addRepeatingPairCell(row.createCell(colNum++), info.getFeatureDefenses(), maxHeight);
		maxHeight = addRepeatingPairCell(row.createCell(colNum++), info.getUnitClassAttackMods(), maxHeight);
		maxHeight = addRepeatingPairCell(row.createCell(colNum++), info.getUnitClassDefenseMods(), maxHeight);
		maxHeight = addRepeatingPairCell(row.createCell(colNum++), info.getUnitCombatMods(), maxHeight);
		maxHeight = addRepeatingPairCell(row.createCell(colNum++), info.getUnitCombatCollateralImmunes(), maxHeight);
		maxHeight = addRepeatingPairCell(row.createCell(colNum++), info.getDomainMods(), maxHeight);
		maxHeight = addRepeatingPairCell(row.createCell(colNum++), info.getBonusProductionModifiers(), maxHeight);
		maxHeight = addRepeatingCell(row.createCell(colNum++), info.getYieldsFromKill(), maxHeight);
		maxHeight = addRepeatingCell(row.createCell(colNum++), info.getCommercesFromKill(), maxHeight);
		addSingleCell(row.createCell(colNum++), info.getBombRate());
		addSingleCell(row.createCell(colNum++), info.getBombardRate());
		addSingleCell(row.createCell(colNum++), info.getSpecialCargo());
		addSingleCell(row.createCell(colNum++), info.getDomainCargo());
		addSingleCell(row.createCell(colNum++), info.getCargo());
		addSingleCell(row.createCell(colNum++), info.getConscription());
		addSingleCell(row.createCell(colNum++), info.getCultureGarrison());
		addSingleCell(row.createCell(colNum++), info.getExtraCost());
		addSingleCell(row.createCell(colNum++), info.getAsset());
		addSingleCell(row.createCell(colNum++), info.getPower());
		addSingleCell(row.createCell(colNum++), getUnitMeshText(info));
		addSingleCell(row.createCell(colNum++), info.getFormationType());
		addSingleCell(row.createCell(colNum++), info.getHotKey());
		addSingleCell(row.createCell(colNum++), info.isAltDown());
		addSingleCell(row.createCell(colNum++), info.isShiftDown());
		addSingleCell(row.createCell(colNum++), info.isCtrlDown());
		addSingleCell(row.createCell(colNum++), info.getHotKeyPriority());
		maxHeight = addRepeatingCell(row.createCell(colNum++), info.getFreePromotions(), maxHeight);
		addSingleCell(row.createCell(colNum++), info.getLeaderPromotion());
		addSingleCell(row.createCell(colNum++), info.getLeaderExperience());
		addSingleCell(row.createCell(colNum++), info.getOrderPriority());

		row.setHeightInPoints(maxHeight * row.getSheet().getDefaultRowHeightInPoints());
	}

	private String getUnitMeshText(IUnitInfo info) {
		StringBuilder sb = new StringBuilder();
		UnitMeshGroups mesh = info.getUnitMeshGroups();
		sb.append(mesh.getGroupSize() + IInfoWorkbook.CELL_NEWLINE);
		sb.append(mesh.getMaxSpeed() + IInfoWorkbook.CELL_NEWLINE);
		sb.append(mesh.getPadTime() + IInfoWorkbook.CELL_NEWLINE);
		sb.append(mesh.getMeleeWaveSize() + IInfoWorkbook.CELL_NEWLINE);
		sb.append(mesh.getRangedWaveSize());
		for (UnitMeshGroup group: mesh.getUnitMeshGroupList()) {
			sb.append(IInfoWorkbook.CELL_NEWLINE + IUnitWorkbook.CELL_GROUP_DELIM + IInfoWorkbook.CELL_NEWLINE);
			sb.append(IUnitWorkbook.CELL_GROUP_PAD + group.getRequired() + IInfoWorkbook.CELL_NEWLINE);
			sb.append(IUnitWorkbook.CELL_GROUP_PAD + group.getEarlyArtDefineTag() + IInfoWorkbook.CELL_NEWLINE);
			sb.append(IUnitWorkbook.CELL_GROUP_PAD + group.getLateArtDefineTag() + IInfoWorkbook.CELL_NEWLINE);
			sb.append(IUnitWorkbook.CELL_GROUP_PAD + group.getMiddleArtDefineTag());
		}

		return sb.toString();
	}
}