package org.archid.civ4.info.promotion;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.archid.civ4.info.AbstractExporter;
import org.archid.civ4.info.EInfo;
import org.archid.civ4.info.IInfos;
import org.archid.civ4.info.promotion.IPromotionWorkbook.SheetHeaders;

public class PromotionExporter extends AbstractExporter<IInfos<IPromotionInfo>, IPromotionInfo> {

	/** Logging facility */
	static Logger log = Logger.getLogger(PromotionExporter.class.getName());

	public PromotionExporter(EInfo infoEnum) {
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
		return IPromotionWorkbook.SheetHeaders.values().length;
	}

	@Override
	protected String getInfoListSheetName() {
		return IPromotionWorkbook.SHEETNAME_LIST;
	}

	@Override
	protected void populateRow(Row row, IPromotionInfo info) {
		int maxHeight = 1;
		int colNum = 0;
		addSingleCell(row.createCell(colNum++), info.getType());
		addSingleCell(row.createCell(colNum++), info.getDescription());
		addSingleCell(row.createCell(colNum++), info.getHelp());
		addSingleCell(row.createCell(colNum++), info.getSound());
		addSingleCell(row.createCell(colNum++), info.getLayerAnimationPath());
		addSingleCell(row.createCell(colNum++), info.getPromotionPrereq());
		maxHeight = addRepeatingCell(row.createCell(colNum++), info.getPrereqOrPromotions(), maxHeight);
		addSingleCell(row.createCell(colNum++), info.getTechPrereq());
		addSingleCell(row.createCell(colNum++), info.getObsoleteTech());
		addSingleCell(row.createCell(colNum++), info.getStateReligionPrereq());
		maxHeight = addRepeatingCell(row.createCell(colNum++), info.getSeeInvisibles(), maxHeight);
		addSingleCell(row.createCell(colNum++), info.isCityPrereq());
		addSingleCell(row.createCell(colNum++), info.getPromotionGroup());
		addSingleCell(row.createCell(colNum++), info.isLeader());
		addSingleCell(row.createCell(colNum++), info.isBlitz());
		addSingleCell(row.createCell(colNum++), info.isAmphib());
		addSingleCell(row.createCell(colNum++), info.isRiver());
		addSingleCell(row.createCell(colNum++), info.isEnemyRoute());
		addSingleCell(row.createCell(colNum++), info.isAlwaysHeal());
		addSingleCell(row.createCell(colNum++), info.isHillsDoubleMove());
		addSingleCell(row.createCell(colNum++), info.isCanMovePeaks());
		addSingleCell(row.createCell(colNum++), info.isImmuneToFirstStrikes());
		addSingleCell(row.createCell(colNum++), info.isLoyal());
		addSingleCell(row.createCell(colNum++), info.isSpyRadiation());
		addSingleCell(row.createCell(colNum++), info.isCarryReligion());
		addSingleCell(row.createCell(colNum++), info.getEnslaveCountChange());
		addSingleCell(row.createCell(colNum++), info.getVisibilityChange());
		addSingleCell(row.createCell(colNum++), info.getMovesChange());
		addSingleCell(row.createCell(colNum++), info.getMoveDiscountChange());
		addSingleCell(row.createCell(colNum++), info.getAirRangeChange());
		addSingleCell(row.createCell(colNum++), info.getInterceptChange());
		addSingleCell(row.createCell(colNum++), info.getEvasionChange());
		addSingleCell(row.createCell(colNum++), info.getWithdrawalChange());
		addSingleCell(row.createCell(colNum++), info.getCargoChange());
		addSingleCell(row.createCell(colNum++), info.getCollateralDamageChange());
		addSingleCell(row.createCell(colNum++), info.getBombardRateChange());
		addSingleCell(row.createCell(colNum++), info.getFirstStrikesChange());
		addSingleCell(row.createCell(colNum++), info.getChanceFirstStrikesChange());
		addSingleCell(row.createCell(colNum++), info.getEnemyHealChange());
		addSingleCell(row.createCell(colNum++), info.getNeutralHealChange());
		addSingleCell(row.createCell(colNum++), info.getFriendlyHealChange());
		addSingleCell(row.createCell(colNum++), info.getSameTileHealChange());
		addSingleCell(row.createCell(colNum++), info.getAdjacentTileHealChange());
		addSingleCell(row.createCell(colNum++), info.getCombatPercent());
		addSingleCell(row.createCell(colNum++), info.getCityAttack());
		addSingleCell(row.createCell(colNum++), info.getCityDefense());
		addSingleCell(row.createCell(colNum++), info.getHillsAttack());
		addSingleCell(row.createCell(colNum++), info.getHillsDefense());
		addSingleCell(row.createCell(colNum++), info.getKamikazePercent());
		addSingleCell(row.createCell(colNum++), info.getSpyPreparationModifier());
		addSingleCell(row.createCell(colNum++), info.getSpyPoisonModifier());
		addSingleCell(row.createCell(colNum++), info.getSpyRevoltChange());
		addSingleCell(row.createCell(colNum++), info.getSpyUnhappyChange());
		addSingleCell(row.createCell(colNum++), info.getSpyInterceptChange());
		addSingleCell(row.createCell(colNum++), info.getSpyEvasionChange());
		addSingleCell(row.createCell(colNum++), info.getSpyDiploPenaltyChange());
		addSingleCell(row.createCell(colNum++), info.getSpyEscapeChange());
		addSingleCell(row.createCell(colNum++), info.getSpyNukeCityChange());
		addSingleCell(row.createCell(colNum++), info.getSpyDisablePowerChange());
		addSingleCell(row.createCell(colNum++), info.getSpyWarWearinessChange());
		addSingleCell(row.createCell(colNum++), info.getSpyResearchSabotageChange());
		addSingleCell(row.createCell(colNum++), info.getSpyReligionRemovalChange());
		addSingleCell(row.createCell(colNum++), info.getSpyCorporationRemovalChange());
		addSingleCell(row.createCell(colNum++), info.getSpyStealTreasuryChange());
		addSingleCell(row.createCell(colNum++), info.getSpyBuyTechChange());
		addSingleCell(row.createCell(colNum++), info.getSpySwitchCivicChange());
		addSingleCell(row.createCell(colNum++), info.getSpySwitchReligionChange());
		addSingleCell(row.createCell(colNum++), info.getSpyDestroyProjectChange());
		addSingleCell(row.createCell(colNum++), info.getSpyDestroyBuildingChange());
		addSingleCell(row.createCell(colNum++), info.getSpyDestroyImprovementChange());
		addSingleCell(row.createCell(colNum++), info.getSpyDestroyProductionChange());
		addSingleCell(row.createCell(colNum++), info.getSpyCultureChange());
		addSingleCell(row.createCell(colNum++), info.getRevoltProtection());
		addSingleCell(row.createCell(colNum++), info.getCollateralDamageProtection());
		addSingleCell(row.createCell(colNum++), info.getPillageChange());
		addSingleCell(row.createCell(colNum++), info.getPlunderChange());
		addSingleCell(row.createCell(colNum++), info.getExtraMorale());
		addSingleCell(row.createCell(colNum++), info.getEnemyMoraleModifier());
		addSingleCell(row.createCell(colNum++), info.getUpgradeDiscount());
		addSingleCell(row.createCell(colNum++), info.getExperiencePercent());
		addSingleCell(row.createCell(colNum++), info.getWorkRateModifier());
		addSingleCell(row.createCell(colNum++), info.isUnitRangeUnbound());
		addSingleCell(row.createCell(colNum++), info.isUnitTerritoryUnbound());
		addSingleCell(row.createCell(colNum++), info.getUnitRangeChange());
		addSingleCell(row.createCell(colNum++), info.getUnitRangeModifier());
		maxHeight = addRepeatingPairCell(row.createCell(colNum++), info.getTerrainAttacks(), maxHeight);
		maxHeight = addRepeatingPairCell(row.createCell(colNum++), info.getTerrainDefenses(), maxHeight);
		maxHeight = addRepeatingPairCell(row.createCell(colNum++), info.getFeatureAttacks(), maxHeight);
		maxHeight = addRepeatingPairCell(row.createCell(colNum++), info.getFeatureDefenses(), maxHeight);
		maxHeight = addRepeatingPairCell(row.createCell(colNum++), info.getUnitCombatMods(), maxHeight);
		maxHeight = addRepeatingPairCell(row.createCell(colNum++), info.getDomainMods(), maxHeight);
		maxHeight = addRepeatingCell(row.createCell(colNum++), info.getTerrainDoubleMoves(), maxHeight);
		maxHeight = addRepeatingCell(row.createCell(colNum++), info.getFeatureDoubleMoves(), maxHeight);
		maxHeight = addRepeatingPairCell(row.createCell(colNum++), info.getBuildLeaveFeatures(), maxHeight);
		maxHeight = addRepeatingCell(row.createCell(colNum++), info.getNotUnitCombatTypes(), maxHeight);
		maxHeight = addRepeatingCell(row.createCell(colNum++), info.getOrUnitCombatTypes(), maxHeight);
		addSingleCell(row.createCell(colNum++), info.getHotKey());
		addSingleCell(row.createCell(colNum++), info.isAltDown());
		addSingleCell(row.createCell(colNum++), info.isShiftDown());
		addSingleCell(row.createCell(colNum++), info.isCtrlDown());
		addSingleCell(row.createCell(colNum++), info.getHotKeyPriority());
		addSingleCell(row.createCell(colNum++), info.getButton());
		addSingleCell(row.createCell(colNum++), info.getOrderPriority());

		row.setHeightInPoints(maxHeight * row.getSheet().getDefaultRowHeightInPoints());
	}
}