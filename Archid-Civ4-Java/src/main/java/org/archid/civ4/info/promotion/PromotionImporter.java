package org.archid.civ4.info.promotion;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.archid.civ4.info.AbstractImporter;
import org.archid.civ4.info.DefaultXmlFormatter;
import org.archid.civ4.info.EInfo;
import org.archid.civ4.info.IInfos;

public class PromotionImporter extends AbstractImporter<IInfos<IPromotionInfo>, IPromotionInfo> {

	/** Logging facility */
	static Logger log = Logger.getLogger(PromotionImporter.class.getName());

	public PromotionImporter(EInfo infoEnum) {
		super(infoEnum, new DefaultXmlFormatter("promotion"));
	}

	@Override
	public String getListSheetName() {
		return IPromotionWorkbook.SHEETNAME_LIST;
	}

	@Override
	protected IPromotionInfo parseRow(Row row) {
		int colNum = 0;
		String type = row.getCell(0).getStringCellValue();
		// Handle cells that have been moved
		if (type.isEmpty())
			return null;

		IPromotionInfo info = PromotionInfos.createInfo(type);
		parseCell(row.getCell(colNum++), String.class, info::setType);
		parseCell(row.getCell(colNum++), String.class, info::setDescription);
		parseCell(row.getCell(colNum++), String.class, info::setHelp);
		parseCell(row.getCell(colNum++), String.class, info::setSound);
		parseCell(row.getCell(colNum++), String.class, info::setLayerAnimationPath);
		parseCell(row.getCell(colNum++), String.class, info::setPromotionPrereq);
		parseListCell(row.getCell(colNum++), String.class, info::addPrereqOrPromotion);
		parseCell(row.getCell(colNum++), String.class, info::setTechPrereq);
		parseCell(row.getCell(colNum++), String.class, info::setObsoleteTech);
		parseCell(row.getCell(colNum++), String.class, info::setStateReligionPrereq);
		parseListCell(row.getCell(colNum++), String.class, info::addSeeInvisible);
		parseCell(row.getCell(colNum++), Boolean.class, info::setCityPrereq);
		parseCell(row.getCell(colNum++), Integer.class, info::setPromotionGroup);
		parseCell(row.getCell(colNum++), Boolean.class, info::setLeader);
		parseCell(row.getCell(colNum++), Boolean.class, info::setBlitz);
		parseCell(row.getCell(colNum++), Boolean.class, info::setAmphib);
		parseCell(row.getCell(colNum++), Boolean.class, info::setRiver);
		parseCell(row.getCell(colNum++), Boolean.class, info::setEnemyRoute);
		parseCell(row.getCell(colNum++), Boolean.class, info::setAlwaysHeal);
		parseCell(row.getCell(colNum++), Boolean.class, info::setHillsDoubleMove);
		parseCell(row.getCell(colNum++), Boolean.class, info::setCanMovePeaks);
		parseCell(row.getCell(colNum++), Boolean.class, info::setImmuneToFirstStrikes);
		parseCell(row.getCell(colNum++), Boolean.class, info::setLoyal);
		parseCell(row.getCell(colNum++), Boolean.class, info::setSpyRadiation);
		parseCell(row.getCell(colNum++), Boolean.class, info::setCarryReligion);
		parseCell(row.getCell(colNum++), Integer.class, info::setEnslaveCountChange);
		parseCell(row.getCell(colNum++), Integer.class, info::setVisibilityChange);
		parseCell(row.getCell(colNum++), Integer.class, info::setMovesChange);
		parseCell(row.getCell(colNum++), Integer.class, info::setMoveDiscountChange);
		parseCell(row.getCell(colNum++), Integer.class, info::setAirRangeChange);
		parseCell(row.getCell(colNum++), Integer.class, info::setInterceptChange);
		parseCell(row.getCell(colNum++), Integer.class, info::setEvasionChange);
		parseCell(row.getCell(colNum++), Integer.class, info::setWithdrawalChange);
		parseCell(row.getCell(colNum++), Integer.class, info::setCargoChange);
		parseCell(row.getCell(colNum++), Integer.class, info::setCollateralDamageChange);
		parseCell(row.getCell(colNum++), Integer.class, info::setBombardRateChange);
		parseCell(row.getCell(colNum++), Integer.class, info::setFirstStrikesChange);
		parseCell(row.getCell(colNum++), Integer.class, info::setChanceFirstStrikesChange);
		parseCell(row.getCell(colNum++), Integer.class, info::setEnemyHealChange);
		parseCell(row.getCell(colNum++), Integer.class, info::setNeutralHealChange);
		parseCell(row.getCell(colNum++), Integer.class, info::setFriendlyHealChange);
		parseCell(row.getCell(colNum++), Integer.class, info::setSameTileHealChange);
		parseCell(row.getCell(colNum++), Integer.class, info::setAdjacentTileHealChange);
		parseCell(row.getCell(colNum++), Integer.class, info::setCombatPercent);
		parseCell(row.getCell(colNum++), Integer.class, info::setCityAttack);
		parseCell(row.getCell(colNum++), Integer.class, info::setCityDefense);
		parseCell(row.getCell(colNum++), Integer.class, info::setHillsAttack);
		parseCell(row.getCell(colNum++), Integer.class, info::setHillsDefense);
		parseCell(row.getCell(colNum++), Integer.class, info::setKamikazePercent);
		parseCell(row.getCell(colNum++), Integer.class, info::setSpyPreparationModifier);
		parseCell(row.getCell(colNum++), Integer.class, info::setSpyPoisonModifier);
		parseCell(row.getCell(colNum++), Integer.class, info::setSpyRevoltChange);
		parseCell(row.getCell(colNum++), Integer.class, info::setSpyUnhappyChange);
		parseCell(row.getCell(colNum++), Integer.class, info::setSpyInterceptChange);
		parseCell(row.getCell(colNum++), Integer.class, info::setSpyEvasionChange);
		parseCell(row.getCell(colNum++), Integer.class, info::setSpyDiploPenaltyChange);
		parseCell(row.getCell(colNum++), Integer.class, info::setSpyEscapeChange);
		parseCell(row.getCell(colNum++), Integer.class, info::setSpyNukeCityChange);
		parseCell(row.getCell(colNum++), Integer.class, info::setSpyDisablePowerChange);
		parseCell(row.getCell(colNum++), Integer.class, info::setSpyWarWearinessChange);
		parseCell(row.getCell(colNum++), Integer.class, info::setSpyResearchSabotageChange);
		parseCell(row.getCell(colNum++), Integer.class, info::setSpyReligionRemovalChange);
		parseCell(row.getCell(colNum++), Integer.class, info::setSpyCorporationRemovalChange);
		parseCell(row.getCell(colNum++), Integer.class, info::setSpyStealTreasuryChange);
		parseCell(row.getCell(colNum++), Integer.class, info::setSpyBuyTechChange);
		parseCell(row.getCell(colNum++), Integer.class, info::setSpySwitchCivicChange);
		parseCell(row.getCell(colNum++), Integer.class, info::setSpySwitchReligionChange);
		parseCell(row.getCell(colNum++), Integer.class, info::setSpyDestroyProjectChange);
		parseCell(row.getCell(colNum++), Integer.class, info::setSpyDestroyBuildingChange);
		parseCell(row.getCell(colNum++), Integer.class, info::setSpyDestroyImprovementChange);
		parseCell(row.getCell(colNum++), Integer.class, info::setSpyDestroyProductionChange);
		parseCell(row.getCell(colNum++), Integer.class, info::setSpyCultureChange);
		parseCell(row.getCell(colNum++), Integer.class, info::setRevoltProtection);
		parseCell(row.getCell(colNum++), Integer.class, info::setCollateralDamageProtection);
		parseCell(row.getCell(colNum++), Integer.class, info::setPillageChange);
		parseCell(row.getCell(colNum++), Integer.class, info::setPlunderChange);
		parseCell(row.getCell(colNum++), Integer.class, info::setExtraMorale);
		parseCell(row.getCell(colNum++), Integer.class, info::setEnemyMoraleModifier);
		parseCell(row.getCell(colNum++), Integer.class, info::setUpgradeDiscount);
		parseCell(row.getCell(colNum++), Integer.class, info::setExperiencePercent);
		parseCell(row.getCell(colNum++), Integer.class, info::setWorkRateModifier);
		parseCell(row.getCell(colNum++), Boolean.class, info::setUnitRangeUnbound);
		parseCell(row.getCell(colNum++), Boolean.class, info::setUnitTerritoryUnbound);
		parseCell(row.getCell(colNum++), Integer.class, info::setUnitRangeChange);
		parseCell(row.getCell(colNum++), Integer.class, info::setUnitRangeModifier);
		parsePairsCell(row.getCell(colNum++), String.class, Integer.class, info::addTerrainAttack);
		parsePairsCell(row.getCell(colNum++), String.class, Integer.class, info::addTerrainDefense);
		parsePairsCell(row.getCell(colNum++), String.class, Integer.class, info::addFeatureAttack);
		parsePairsCell(row.getCell(colNum++), String.class, Integer.class, info::addFeatureDefense);
		parsePairsCell(row.getCell(colNum++), String.class, Integer.class, info::addUnitCombatMod);
		parsePairsCell(row.getCell(colNum++), String.class, Integer.class, info::addDomainMod);
		parseListCell(row.getCell(colNum++), String.class, info::addTerrainDoubleMove);
		parseListCell(row.getCell(colNum++), String.class, info::addFeatureDoubleMove);
		parsePairsCell(row.getCell(colNum++), String.class, String.class, info::addBuildLeaveFeature);
		parseListCell(row.getCell(colNum++), String.class, info::addNotUnitCombatType);
		parseListCell(row.getCell(colNum++), String.class, info::addOrUnitCombatType);
		parseCell(row.getCell(colNum++), String.class, info::setHotKey);
		parseCell(row.getCell(colNum++), Boolean.class, info::setAltDown);
		parseCell(row.getCell(colNum++), Boolean.class, info::setShiftDown);
		parseCell(row.getCell(colNum++), Boolean.class, info::setCtrlDown);
		parseCell(row.getCell(colNum++), Integer.class, info::setHotKeyPriority);
		parseCell(row.getCell(colNum++), String.class, info::setButton);
		parseCell(row.getCell(colNum++), Integer.class, info::setOrderPriority);

		return info;
	}
}