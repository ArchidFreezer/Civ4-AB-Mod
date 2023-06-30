package org.archid.civ4.info.unit;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.archid.civ4.info.AbstractImporter;
import org.archid.civ4.info.EInfo;
import org.archid.civ4.info.IInfoWorkbook;
import org.archid.civ4.info.IInfos;
import org.archid.civ4.info.unit.UnitMeshGroups.UnitMeshGroup;
import org.archid.utils.StringUtils;

public class UnitImporter extends AbstractImporter<IInfos<IUnitInfo>, IUnitInfo> {

	/** Logging facility */
	static Logger log = Logger.getLogger(UnitImporter.class.getName());

	public UnitImporter(EInfo infoEnum) {
		super(infoEnum, new UnitInfoXmlFormatter());
	}

	@Override
	public String getListSheetName() {
		return IUnitWorkbook.SHEETNAME_LIST;
	}

	@Override
	protected IUnitInfo parseRow(Row row) {
		int colNum = 0;
		String type = row.getCell(1).getStringCellValue();
		// Handle cells that have been moved
		if (type.isEmpty())
			return null;

		IUnitInfo info = UnitInfos.createInfo(type);
		parseCell(row.getCell(colNum++), String.class, info::setClazz);
		parseCell(row.getCell(colNum++), String.class, info::setType);
		parseListCell(row.getCell(colNum++), String.class, info::addUniqueName);
		parseCell(row.getCell(colNum++), String.class, info::setSpecial);
		parseCell(row.getCell(colNum++), String.class, info::setCapture);
		parseCell(row.getCell(colNum++), String.class, info::setCombat);
		parseListCell(row.getCell(colNum++), String.class, info::addSubCombatType);
		parseCell(row.getCell(colNum++), String.class, info::setDomain);
		parseCell(row.getCell(colNum++), String.class, info::setDefaultUnitAI);
		parseCell(row.getCell(colNum++), Boolean.class, info::setFixedAI);
		parseCell(row.getCell(colNum++), Integer.class, info::setMaxWeaponTypeTier);
		parseCell(row.getCell(colNum++), Integer.class, info::setMaxAmmoTypeTier);
		parseCell(row.getCell(colNum++), String.class, info::setInvisible);
		parseListCell(row.getCell(colNum++), String.class, info::addSeeInvisible);
		parseCell(row.getCell(colNum++), String.class, info::setDescription);
		parseCell(row.getCell(colNum++), String.class, info::setCivilopedia);
		parseCell(row.getCell(colNum++), String.class, info::setStrategy);
		parseCell(row.getCell(colNum++), String.class, info::setHelp);
		parseCell(row.getCell(colNum++), String.class, info::setAdvisor);
		parseCell(row.getCell(colNum++), Boolean.class, info::setAnimal);
		parseCell(row.getCell(colNum++), Boolean.class, info::setFood);
		parseCell(row.getCell(colNum++), Boolean.class, info::setNoBadGoodies);
		parseCell(row.getCell(colNum++), Boolean.class, info::setOnlyDefensive);
		parseCell(row.getCell(colNum++), Boolean.class, info::setNoCapture);
		parseCell(row.getCell(colNum++), Boolean.class, info::setQuickCombat);
		parseCell(row.getCell(colNum++), Boolean.class, info::setRivalTerritory);
		parseCell(row.getCell(colNum++), Boolean.class, info::setMilitaryHappiness);
		parseCell(row.getCell(colNum++), Boolean.class, info::setMilitarySupport);
		parseCell(row.getCell(colNum++), Boolean.class, info::setMilitaryProduction);
		parseCell(row.getCell(colNum++), Boolean.class, info::setPillage);
		parseCell(row.getCell(colNum++), Boolean.class, info::setSpy);
		parseCell(row.getCell(colNum++), Boolean.class, info::setSabotage);
		parseCell(row.getCell(colNum++), Boolean.class, info::setDestroy);
		parseCell(row.getCell(colNum++), Boolean.class, info::setStealPlans);
		parseCell(row.getCell(colNum++), Boolean.class, info::setInvestigate);
		parseCell(row.getCell(colNum++), Boolean.class, info::setCounterSpy);
		parseCell(row.getCell(colNum++), Boolean.class, info::setFound);
		parseCell(row.getCell(colNum++), Boolean.class, info::setGoldenAge);
		parseCell(row.getCell(colNum++), Boolean.class, info::setInvisibleBool);
		parseCell(row.getCell(colNum++), Boolean.class, info::setFirstStrikeImmune);
		parseCell(row.getCell(colNum++), Boolean.class, info::setNoDefensiveBonus);
		parseCell(row.getCell(colNum++), Boolean.class, info::setIgnoreBuildingDefense);
		parseCell(row.getCell(colNum++), Boolean.class, info::setCanMoveImpassable);
		parseCell(row.getCell(colNum++), Boolean.class, info::setCanMoveAllTerrain);
		parseCell(row.getCell(colNum++), Boolean.class, info::setFlatMovementCost);
		parseCell(row.getCell(colNum++), Boolean.class, info::setIgnoreTerrainCost);
		parseCell(row.getCell(colNum++), Boolean.class, info::setNukeImmune);
		parseCell(row.getCell(colNum++), Boolean.class, info::setPrereqBonuses);
		parseCell(row.getCell(colNum++), Boolean.class, info::setPrereqReligionBool);
		parseCell(row.getCell(colNum++), Boolean.class, info::setMechanized);
		parseCell(row.getCell(colNum++), Boolean.class, info::setRenderBelowWater);
		parseCell(row.getCell(colNum++), Boolean.class, info::setRenderAlways);
		parseCell(row.getCell(colNum++), Boolean.class, info::setSuicide);
		parseCell(row.getCell(colNum++), Boolean.class, info::setSingleBuild);
		parseCell(row.getCell(colNum++), Boolean.class, info::setSlave);
		parseCell(row.getCell(colNum++), Boolean.class, info::setLineOfSight);
		parseCell(row.getCell(colNum++), Boolean.class, info::setHiddenNationality);
		parseCell(row.getCell(colNum++), Boolean.class, info::setAlwaysHostile);
		parseCell(row.getCell(colNum++), Boolean.class, info::setWorkerTrade);
		parseCell(row.getCell(colNum++), Boolean.class, info::setMilitaryTrade);
		parseCell(row.getCell(colNum++), Boolean.class, info::setBarbarianLeader);
		parseCell(row.getCell(colNum++), Boolean.class, info::setCityPacifier);
		parseListCell(row.getCell(colNum++), String.class, info::addUnitClassUpgrade);
		parseListCell(row.getCell(colNum++), String.class, info::addUnitClassTarget);
		parseListCell(row.getCell(colNum++), String.class, info::addUnitCombatTarget);
		parseListCell(row.getCell(colNum++), String.class, info::addUnitClassDefender);
		parseListCell(row.getCell(colNum++), String.class, info::addUnitCombatDefender);
		parsePairsCell(row.getCell(colNum++), String.class, Integer.class, info::addFlankingStrike);
		parseListCell(row.getCell(colNum++), String.class, info::addUnitAI);
		parseListCell(row.getCell(colNum++), String.class, info::addNotUnitAI);
		parseListCell(row.getCell(colNum++), String.class, info::addBuild);
		parsePairsCell(row.getCell(colNum++), String.class, Integer.class, info::addReligionSpread);
		parsePairsCell(row.getCell(colNum++), String.class, Integer.class, info::addCorporationSpread);
		parseListCell(row.getCell(colNum++), String.class, info::addGreatPeople);
		parseCell(row.getCell(colNum++), String.class, info::setSlaveSpecialistType);
		parseListCell(row.getCell(colNum++), String.class, info::addBuilding);
		parseListCell(row.getCell(colNum++), String.class, info::addForceBuilding);
		parseCell(row.getCell(colNum++), String.class, info::setHolyCity);
		parseCell(row.getCell(colNum++), String.class, info::setReligionType);
		parseCell(row.getCell(colNum++), String.class, info::setStateReligion);
		parseCell(row.getCell(colNum++), String.class, info::setPrereqReligion);
		parseCell(row.getCell(colNum++), String.class, info::setPrereqCorporation);
		parseCell(row.getCell(colNum++), String.class, info::setPrereqBuilding);
		parseListCell(row.getCell(colNum++), String.class, info::addPrereqTech);
		parseCell(row.getCell(colNum++), String.class, info::setObsoleteTech);
		parseCell(row.getCell(colNum++), String.class, info::setBonusType);
		parseListCell(row.getCell(colNum++), String.class, info::addPrereqOrBonus);
		parseListCell(row.getCell(colNum++), String.class, info::addPrereqAndCivic);
		parseListCell(row.getCell(colNum++), String.class, info::addPrereqOrCivic);
		parseListCell(row.getCell(colNum++), String.class, info::addPrereqAndTerrain);
		parseListCell(row.getCell(colNum++), String.class, info::addPrereqOrTerrain);
		parseListCell(row.getCell(colNum++), String.class, info::addPrereqOrBuildingClass);
		parseListCell(row.getCell(colNum++), String.class, info::addPrereqNotBuildingClass);
		parseListCell(row.getCell(colNum++), String.class, info::addPrereqVicinityAndBonu);
		parseListCell(row.getCell(colNum++), String.class, info::addPrereqVicinityOrBonu);
		parseListCell(row.getCell(colNum++), String.class, info::addPrereqVicinityImprovement);
		parseListCell(row.getCell(colNum++), String.class, info::addPrereqVicinityFeature);
		parseListCell(row.getCell(colNum++), String.class, info::addPrereqWorldView);
		parseCell(row.getCell(colNum++), Integer.class, info::setMinPopulation);
		parseCell(row.getCell(colNum++), String.class, info::setMinCultureLevel);
		parseCell(row.getCell(colNum++), Boolean.class, info::setPrereqPower);
		parsePairsCell(row.getCell(colNum++), String.class, Integer.class, info::addProductionTrait);
		parsePairsCell(row.getCell(colNum++), String.class, Integer.class, info::addFlavor);
		parseCell(row.getCell(colNum++), Integer.class, info::setAIWeight);
		parseCell(row.getCell(colNum++), Integer.class, info::setCost);
		parseCell(row.getCell(colNum++), Integer.class, info::setHurryCostModifier);
		parseCell(row.getCell(colNum++), Integer.class, info::setAdvancedStartCost);
		parseCell(row.getCell(colNum++), Integer.class, info::setAdvancedStartCostIncrease);
		parseCell(row.getCell(colNum++), Integer.class, info::setMinAreaSize);
		parseCell(row.getCell(colNum++), Integer.class, info::setMorale);
		parseCell(row.getCell(colNum++), Integer.class, info::setMoves);
		parseCell(row.getCell(colNum++), String.class, info::setUnitRangeType);
		parseCell(row.getCell(colNum++), Boolean.class, info::setNoRevealMap);
		parseCell(row.getCell(colNum++), Integer.class, info::setAirRange);
		parseCell(row.getCell(colNum++), Integer.class, info::setAirUnitCap);
		parseCell(row.getCell(colNum++), Integer.class, info::setDropRange);
		parseCell(row.getCell(colNum++), Integer.class, info::setNukeRange);
		parseCell(row.getCell(colNum++), Integer.class, info::setWorkRate);
		parseCell(row.getCell(colNum++), Integer.class, info::setBaseDiscover);
		parseCell(row.getCell(colNum++), Integer.class, info::setDiscoverMultiplier);
		parseCell(row.getCell(colNum++), Integer.class, info::setBaseHurry);
		parseCell(row.getCell(colNum++), Integer.class, info::setHurryMultiplier);
		parseCell(row.getCell(colNum++), Integer.class, info::setBaseTrade);
		parseCell(row.getCell(colNum++), Integer.class, info::setTradeMultiplier);
		parseCell(row.getCell(colNum++), Integer.class, info::setGreatWorkCulture);
		parseCell(row.getCell(colNum++), Integer.class, info::setEspionagePoints);
		parseCell(row.getCell(colNum++), Integer.class, info::setGreatJestHappiness);
		parseCell(row.getCell(colNum++), Integer.class, info::setGreatJestDuration);
		parseListCell(row.getCell(colNum++), String.class, info::addTerrainImpassable);
		parseListCell(row.getCell(colNum++), String.class, info::addFeatureImpassable);
		parsePairsCell(row.getCell(colNum++), String.class, String.class, info::addTerrainPassableTech);
		parsePairsCell(row.getCell(colNum++), String.class, String.class, info::addFeaturePassableTech);
		parseCell(row.getCell(colNum++), Integer.class, info::setCombatInt);
		parseCell(row.getCell(colNum++), Integer.class, info::setCombatLimit);
		parseCell(row.getCell(colNum++), Integer.class, info::setAirCombat);
		parseCell(row.getCell(colNum++), Integer.class, info::setAirCombatLimit);
		parseCell(row.getCell(colNum++), Integer.class, info::setXPValueAttack);
		parseCell(row.getCell(colNum++), Integer.class, info::setXPValueDefense);
		parseCell(row.getCell(colNum++), Integer.class, info::setFirstStrikes);
		parseCell(row.getCell(colNum++), Integer.class, info::setChanceFirstStrikes);
		parseCell(row.getCell(colNum++), Integer.class, info::setInterceptionProbability);
		parseCell(row.getCell(colNum++), Integer.class, info::setEvasionProbability);
		parseCell(row.getCell(colNum++), Integer.class, info::setWithdrawalProb);
		parseCell(row.getCell(colNum++), Integer.class, info::setCollateralDamage);
		parseCell(row.getCell(colNum++), Integer.class, info::setCollateralDamageLimit);
		parseCell(row.getCell(colNum++), Integer.class, info::setCollateralDamageMaxUnits);
		parseCell(row.getCell(colNum++), Integer.class, info::setCityAttack);
		parseCell(row.getCell(colNum++), Integer.class, info::setCityDefense);
		parseCell(row.getCell(colNum++), Integer.class, info::setAnimalCombat);
		parseCell(row.getCell(colNum++), Integer.class, info::setHillsAttack);
		parseCell(row.getCell(colNum++), Integer.class, info::setHillsDefense);
		parseListCell(row.getCell(colNum++), String.class, info::addTerrainNative);
		parseListCell(row.getCell(colNum++), String.class, info::addFeatureNative);
		parsePairsCell(row.getCell(colNum++), String.class, Integer.class, info::addTerrainAttack);
		parsePairsCell(row.getCell(colNum++), String.class, Integer.class, info::addTerrainDefense);
		parsePairsCell(row.getCell(colNum++), String.class, Integer.class, info::addFeatureAttack);
		parsePairsCell(row.getCell(colNum++), String.class, Integer.class, info::addFeatureDefense);
		parsePairsCell(row.getCell(colNum++), String.class, Integer.class, info::addUnitClassAttackMod);
		parsePairsCell(row.getCell(colNum++), String.class, Integer.class, info::addUnitClassDefenseMod);
		parsePairsCell(row.getCell(colNum++), String.class, Integer.class, info::addUnitCombatMod);
		parsePairsCell(row.getCell(colNum++), String.class, Integer.class, info::addUnitCombatCollateralImmune);
		parsePairsCell(row.getCell(colNum++), String.class, Integer.class, info::addDomainMod);
		parsePairsCell(row.getCell(colNum++), String.class, Integer.class, info::addBonusProductionModifier);
		parseListCell(row.getCell(colNum++), Integer.class, info::addYieldsFromKill);
		parseListCell(row.getCell(colNum++), Integer.class, info::addCommercesFromKill);
		parseCell(row.getCell(colNum++), Integer.class, info::setBombRate);
		parseCell(row.getCell(colNum++), Integer.class, info::setBombardRate);
		parseCell(row.getCell(colNum++), String.class, info::setSpecialCargo);
		parseCell(row.getCell(colNum++), String.class, info::setDomainCargo);
		parseCell(row.getCell(colNum++), Integer.class, info::setCargo);
		parseCell(row.getCell(colNum++), Integer.class, info::setConscription);
		parseCell(row.getCell(colNum++), Integer.class, info::setCultureGarrison);
		parseCell(row.getCell(colNum++), Integer.class, info::setExtraCost);
		parseCell(row.getCell(colNum++), Integer.class, info::setAsset);
		parseCell(row.getCell(colNum++), Integer.class, info::setPower);
		info.setUnitMeshGroups(getUnitMeshGroups(row.getCell(colNum++).getStringCellValue()));
		parseCell(row.getCell(colNum++), String.class, info::setFormationType);
		parseCell(row.getCell(colNum++), String.class, info::setHotKey);
		parseCell(row.getCell(colNum++), Boolean.class, info::setAltDown);
		parseCell(row.getCell(colNum++), Boolean.class, info::setShiftDown);
		parseCell(row.getCell(colNum++), Boolean.class, info::setCtrlDown);
		parseCell(row.getCell(colNum++), Integer.class, info::setHotKeyPriority);
		parseListCell(row.getCell(colNum++), String.class, info::addFreePromotion);
		parseCell(row.getCell(colNum++), String.class, info::setLeaderPromotion);
		parseCell(row.getCell(colNum++), Integer.class, info::setLeaderExperience);
		parseCell(row.getCell(colNum++), Integer.class, info::setOrderPriority);

		return info;
	}
	private UnitMeshGroups getUnitMeshGroups(String stringCellValue) {
		String[] rows = stringCellValue.split(IInfoWorkbook.CELL_NEWLINE);
		int index = 0;
		UnitMeshGroups mesh = new UnitMeshGroups();
		mesh.setGroupSize(Integer.parseInt(rows[index++]));
		mesh.setMaxSpeed(Float.parseFloat(rows[index++]));
		mesh.setPadTime(Float.parseFloat(rows[index++]));
		mesh.setMeleeWaveSize(Integer.parseInt(rows[index++]));
		mesh.setRangedWaveSize(Integer.parseInt(rows[index++]));
		List<UnitMeshGroup> groups = new ArrayList<UnitMeshGroup>();
		while (index < rows.length) {
			String row = rows[index++];
			if (row.equals(IUnitWorkbook.CELL_GROUP_DELIM)) {
				UnitMeshGroup group = new UnitMeshGroup();
				group.setRequired(Integer.parseInt(rows[index++].trim()));
				group.setEarlyArtDefineTag(rows[index++].trim());
				group.setLateArtDefineTag(StringUtils.getNullIfEmpty(rows[index++].trim()));
				group.setMiddleArtDefineTag(StringUtils.getNullIfEmpty(rows[index++].trim()));
				groups.add(group);
			}
			mesh.setUnitMeshGroupList(groups);
		}
		return mesh;
	}
}