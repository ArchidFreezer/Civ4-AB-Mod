package org.archid.civ4.info.tech;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import org.archid.utils.CollectionUtils;
import org.archid.utils.IPair;
import org.archid.utils.JaxbUtils;
import org.archid.utils.Pair;
import org.archid.utils.StringUtils;

public class TechMapAdapter extends XmlAdapter<TechMapAdapter.TechMap, Map<String, ITechInfo>> {

	public static class TechMap {
		@XmlElement(name = "TechInfo")
		List<AdaptedTech> entries = new ArrayList<AdaptedTech>();
	}

	private static class AdaptedTech {
		@XmlElement(name="Type")
		private String type;
		@XmlElement(name="Description")
		private String description;
		@XmlElement(name="Civilopedia")
		private String civilopedia;
		@XmlElement(name="Help")
		private String help;
		@XmlElement(name="Strategy")
		private String strategy;
		@XmlElement(name="Advisor")
		private String advisor;
		@XmlElement(name="iAIWeight")
		private Integer aIWeight;
		@XmlElement(name="iAITradeModifier")
		private Integer aITradeModifier;
		@XmlElement(name="iCost")
		private Integer cost;
		@XmlElement(name="iAdvancedStartCost")
		private Integer advancedStartCost;
		@XmlElement(name="iAdvancedStartCostIncrease")
		private Integer advancedStartCostIncrease;
		@XmlElement(name="Era")
		private String era;
		@XmlElement(name="bCivSettled")
		private Integer civSettled;
		@XmlElement(name="FreeCapitalBuildingClass")
		private String freeCapitalBuildingClass;
		@XmlElement(name="FirstFreeUnitClass")
		private String firstFreeUnitClass;
		@XmlElement(name="FreeUnitClass")
		private String freeUnitClass;
		@XmlElement(name="iFeatureProductionModifier")
		private Integer featureProductionModifier;
		@XmlElement(name="iWorkerSpeedModifier")
		private Integer workerSpeedModifier;
		@XmlElement(name="iTradeRoutes")
		private Integer tradeRoutes;
		@XmlElement(name="iHealth")
		private Integer health;
		@XmlElement(name="iHappiness")
		private Integer happiness;
		@XmlElement(name="iFirstFreeTechs")
		private Integer firstFreeTechs;
		@XmlElement(name="iAsset")
		private Integer asset;
		@XmlElement(name="iPower")
		private Integer power;
		@XmlElement(name="bRepeat")
		private Integer repeat;
		@XmlElement(name="bTrade")
		private Integer trade;
		@XmlElement(name="bEmbassyTrading")
		private Integer embassyTrading;
		@XmlElement(name="bFreeTradeAgreementTrading")
		private Integer freeTradeAgreementTrading;
		@XmlElement(name="bNonAggressionTrading")
		private Integer nonAggressionTrading;
		@XmlElement(name="bDisable")
		private Integer disable;
		@XmlElement(name="bGoodyTech")
		private Integer goodyTech;
		@XmlElement(name="bExtraWaterSeeFrom")
		private Integer extraWaterSeeFrom;
		@XmlElement(name="bMapCentering")
		private Integer mapCentering;
		@XmlElement(name="bMapVisible")
		private Integer mapVisible;
		@XmlElement(name="bMapTrading")
		private Integer mapTrading;
		@XmlElement(name="bTechTrading")
		private Integer techTrading;
		@XmlElement(name="bGoldTrading")
		private Integer goldTrading;
		@XmlElement(name="bOpenBordersTrading")
		private Integer openBordersTrading;
		@XmlElement(name="bLimitedBordersTrading")
		private Integer limitedBordersTrading;
		@XmlElement(name="bDefensivePactTrading")
		private Integer defensivePactTrading;
		@XmlElement(name="bPermanentAllianceTrading")
		private Integer permanentAllianceTrading;
		@XmlElement(name="bVassalTrading")
		private Integer vassalTrading;
		@XmlElement(name="bBridgeBuilding")
		private Integer bridgeBuilding;
		@XmlElement(name="bIrrigation")
		private Integer irrigation;
		@XmlElement(name="bIgnoreIrrigation")
		private Integer ignoreIrrigation;
		@XmlElement(name="bWaterWork")
		private Integer waterWork;
		@XmlElement(name="bCanPassPeaks")
		private Integer canPassPeaks;
		@XmlElement(name="bMoveFastPeaks")
		private Integer moveFastPeaks;
		@XmlElement(name="bCanFoundOnPeaks")
		private Integer canFoundOnPeaks;
		@XmlElement(name="iGridX")
		private Integer gridX;
		@XmlElement(name="iGridY")
		private Integer gridY;
		@XmlElementWrapper(name="DomainExtraMoves")
		@XmlElement(name="DomainExtraMove")
		private List<AdaptedDomainExtraMoves> domainExtraMoves;
		@XmlElementWrapper(name="CommerceModifiers")
		@XmlElement(name="iCommerce")
		private List<Integer> commerceModifiers;
		@XmlElementWrapper(name="SpecialistExtraCommerces")
		@XmlElement(name="iCommerce")
		private List<Integer> specialistExtraCommerces;
		@XmlElementWrapper(name="CommerceFlexible")
		@XmlElement(name="bFlexible")
		private List<Integer> commerceFlexible;
		@XmlElementWrapper(name="TerrainTrades")
		@XmlElement(name="TerrainType")
		private List<String> terrainTrades;
		@XmlElement(name="bRiverTrade")
		private Integer riverTrade;
		@XmlElement(name="bCaptureCities")
		private Integer captureCities;
		@XmlElement(name="bUnitRangeUnbound")
		private Integer unitRangeUnbound;
		@XmlElement(name="bUnitTerritoryUnbound")
		private Integer unitTerritoryUnbound;
		@XmlElement(name="iUnitRangeChange")
		private Integer unitRangeChange;
		@XmlElement(name="iUnitRangeModifier")
		private Integer unitRangeModifier;
		@XmlElement(name="iCultureDefenceModifier")
		private Integer cultureDefenceModifier;
		@XmlElementWrapper(name="ForestPlotYieldChanges")
		@XmlElement(name="iYield")
		private List<Integer> forestPlotYieldChanges;
		@XmlElementWrapper(name="RiverPlotYieldChanges")
		@XmlElement(name="iYield")
		private List<Integer> riverPlotYieldChanges;
		@XmlElementWrapper(name="SeaPlotYieldChanges")
		@XmlElement(name="iYield")
		private List<Integer> seaPlotYieldChanges;
		@XmlElementWrapper(name="WorldViewRevoltTurnChanges")
		@XmlElement(name="WorldViewRevoltTurnChange")
		private List<AdaptedWorldViewRevoltTurnChanges> worldViewRevoltTurnChanges;
		@XmlElementWrapper(name="Flavors")
		@XmlElement(name="Flavor")
		private List<AdaptedFlavors> flavors;
		@XmlElementWrapper(name="OrPreReqs")
		@XmlElement(name="PrereqTech")
		private List<String> orPreReqs;
		@XmlElementWrapper(name="AndPreReqs")
		@XmlElement(name="PrereqTech")
		private List<String> andPreReqs;
		@XmlElementWrapper(name="EnabledWorldViews")
		@XmlElement(name="WorldViewType")
		private List<String> enabledWorldViews;
		@XmlElement(name="Quote")
		private String quote;
		@XmlElement(name="Sound")
		private String sound;
		@XmlElement(name="SoundMP")
		private String soundMP;
		@XmlElement(name="Button")
		private String button;
	}

	private static class AdaptedDomainExtraMoves {
		@XmlElement(name="DomainType")
		private String domainType;
		@XmlElement(name="iExtraMoves")
		private Integer iExtraMoves;
	}

	private static class AdaptedWorldViewRevoltTurnChanges {
		@XmlElement(name="WorldViewType")
		private String worldViewType;
		@XmlElement(name="iChange")
		private Integer iChange;
	}

	private static class AdaptedFlavors {
		@XmlElement(name="FlavorType")
		private String flavorType;
		@XmlElement(name="iFlavor")
		private Integer iFlavor;
	}

	@Override
	public Map<String, ITechInfo> unmarshal(TechMap v) throws Exception {
		Map<String, ITechInfo> map = new TreeMap<String, ITechInfo>();
		for (AdaptedTech aInfo: v.entries) {
			ITechInfo info = TechInfos.createInfo(aInfo.type);
			info.setDescription(JaxbUtils.unmarshallString(aInfo.description));
			info.setCivilopedia(JaxbUtils.unmarshallString(aInfo.civilopedia));
			info.setHelp(JaxbUtils.unmarshallString(aInfo.help));
			info.setStrategy(JaxbUtils.unmarshallString(aInfo.strategy));
			info.setAdvisor(JaxbUtils.unmarshallString(aInfo.advisor));
			info.setAIWeight(JaxbUtils.unmarshallInteger(aInfo.aIWeight));
			info.setAITradeModifier(JaxbUtils.unmarshallInteger(aInfo.aITradeModifier));
			info.setCost(JaxbUtils.unmarshallInteger(aInfo.cost));
			info.setAdvancedStartCost(JaxbUtils.unmarshallInteger(aInfo.advancedStartCost));
			info.setAdvancedStartCostIncrease(JaxbUtils.unmarshallInteger(aInfo.advancedStartCostIncrease));
			info.setEra(JaxbUtils.unmarshallString(aInfo.era));
			info.setCivSettled(JaxbUtils.unmarshallBoolean(aInfo.civSettled));
			info.setFreeCapitalBuildingClass(JaxbUtils.unmarshallString(aInfo.freeCapitalBuildingClass));
			info.setFirstFreeUnitClass(JaxbUtils.unmarshallString(aInfo.firstFreeUnitClass));
			info.setFreeUnitClass(JaxbUtils.unmarshallString(aInfo.freeUnitClass));
			info.setFeatureProductionModifier(JaxbUtils.unmarshallInteger(aInfo.featureProductionModifier));
			info.setWorkerSpeedModifier(JaxbUtils.unmarshallInteger(aInfo.workerSpeedModifier));
			info.setTradeRoutes(JaxbUtils.unmarshallInteger(aInfo.tradeRoutes));
			info.setHealth(JaxbUtils.unmarshallInteger(aInfo.health));
			info.setHappiness(JaxbUtils.unmarshallInteger(aInfo.happiness));
			info.setFirstFreeTechs(JaxbUtils.unmarshallInteger(aInfo.firstFreeTechs));
			info.setAsset(JaxbUtils.unmarshallInteger(aInfo.asset));
			info.setPower(JaxbUtils.unmarshallInteger(aInfo.power));
			info.setRepeat(JaxbUtils.unmarshallBoolean(aInfo.repeat));
			info.setTrade(JaxbUtils.unmarshallBoolean(aInfo.trade));
			info.setEmbassyTrading(JaxbUtils.unmarshallBoolean(aInfo.embassyTrading));
			info.setFreeTradeAgreementTrading(JaxbUtils.unmarshallBoolean(aInfo.freeTradeAgreementTrading));
			info.setNonAggressionTrading(JaxbUtils.unmarshallBoolean(aInfo.nonAggressionTrading));
			info.setDisable(JaxbUtils.unmarshallBoolean(aInfo.disable));
			info.setGoodyTech(JaxbUtils.unmarshallBoolean(aInfo.goodyTech));
			info.setExtraWaterSeeFrom(JaxbUtils.unmarshallBoolean(aInfo.extraWaterSeeFrom));
			info.setMapCentering(JaxbUtils.unmarshallBoolean(aInfo.mapCentering));
			info.setMapVisible(JaxbUtils.unmarshallBoolean(aInfo.mapVisible));
			info.setMapTrading(JaxbUtils.unmarshallBoolean(aInfo.mapTrading));
			info.setTechTrading(JaxbUtils.unmarshallBoolean(aInfo.techTrading));
			info.setGoldTrading(JaxbUtils.unmarshallBoolean(aInfo.goldTrading));
			info.setOpenBordersTrading(JaxbUtils.unmarshallBoolean(aInfo.openBordersTrading));
			info.setLimitedBordersTrading(JaxbUtils.unmarshallBoolean(aInfo.limitedBordersTrading));
			info.setDefensivePactTrading(JaxbUtils.unmarshallBoolean(aInfo.defensivePactTrading));
			info.setPermanentAllianceTrading(JaxbUtils.unmarshallBoolean(aInfo.permanentAllianceTrading));
			info.setVassalTrading(JaxbUtils.unmarshallBoolean(aInfo.vassalTrading));
			info.setBridgeBuilding(JaxbUtils.unmarshallBoolean(aInfo.bridgeBuilding));
			info.setIrrigation(JaxbUtils.unmarshallBoolean(aInfo.irrigation));
			info.setIgnoreIrrigation(JaxbUtils.unmarshallBoolean(aInfo.ignoreIrrigation));
			info.setWaterWork(JaxbUtils.unmarshallBoolean(aInfo.waterWork));
			info.setCanPassPeaks(JaxbUtils.unmarshallBoolean(aInfo.canPassPeaks));
			info.setMoveFastPeaks(JaxbUtils.unmarshallBoolean(aInfo.moveFastPeaks));
			info.setCanFoundOnPeaks(JaxbUtils.unmarshallBoolean(aInfo.canFoundOnPeaks));
			info.setGridX(JaxbUtils.unmarshallInteger(aInfo.gridX));
			info.setGridY(JaxbUtils.unmarshallInteger(aInfo.gridY));

			if (CollectionUtils.hasElements(aInfo.domainExtraMoves)) {
				for (AdaptedDomainExtraMoves adaptor: aInfo.domainExtraMoves) {
					if (StringUtils.hasCharacters(adaptor.domainType)) {
						info.addDomainExtraMove(new Pair<String, Integer>(adaptor.domainType, adaptor.iExtraMoves));
					}
				}
			}

			if (CollectionUtils.hasElements(aInfo.commerceModifiers)) {
				for (Integer val: aInfo.commerceModifiers) {
					info.addCommerceModifier(JaxbUtils.unmarshallInteger(val));
				}
			}

			if (CollectionUtils.hasElements(aInfo.specialistExtraCommerces)) {
				for (Integer val: aInfo.specialistExtraCommerces) {
					info.addSpecialistExtraCommerce(JaxbUtils.unmarshallInteger(val));
				}
			}

			if (CollectionUtils.hasElements(aInfo.commerceFlexible)) {
				for (Integer val: aInfo.commerceFlexible) {
					info.addCommerceFlexible(JaxbUtils.unmarshallBoolean(val));
				}
			}

			if (CollectionUtils.hasElements(aInfo.terrainTrades)) {
				for (String val: aInfo.terrainTrades) {
					if (StringUtils.hasCharacters(val)) {
						info.addTerrainTrade(JaxbUtils.unmarshallString(val));
					}
				}
			}
			info.setRiverTrade(JaxbUtils.unmarshallBoolean(aInfo.riverTrade));
			info.setCaptureCities(JaxbUtils.unmarshallBoolean(aInfo.captureCities));
			info.setUnitRangeUnbound(JaxbUtils.unmarshallBoolean(aInfo.unitRangeUnbound));
			info.setUnitTerritoryUnbound(JaxbUtils.unmarshallBoolean(aInfo.unitTerritoryUnbound));
			info.setUnitRangeChange(JaxbUtils.unmarshallInteger(aInfo.unitRangeChange));
			info.setUnitRangeModifier(JaxbUtils.unmarshallInteger(aInfo.unitRangeModifier));
			info.setCultureDefenceModifier(JaxbUtils.unmarshallInteger(aInfo.cultureDefenceModifier));

			if (CollectionUtils.hasElements(aInfo.forestPlotYieldChanges)) {
				for (Integer val: aInfo.forestPlotYieldChanges) {
					info.addForestPlotYieldChange(JaxbUtils.unmarshallInteger(val));
				}
			}

			if (CollectionUtils.hasElements(aInfo.riverPlotYieldChanges)) {
				for (Integer val: aInfo.riverPlotYieldChanges) {
					info.addRiverPlotYieldChange(JaxbUtils.unmarshallInteger(val));
				}
			}

			if (CollectionUtils.hasElements(aInfo.seaPlotYieldChanges)) {
				for (Integer val: aInfo.seaPlotYieldChanges) {
					info.addSeaPlotYieldChange(JaxbUtils.unmarshallInteger(val));
				}
			}

			if (CollectionUtils.hasElements(aInfo.worldViewRevoltTurnChanges)) {
				for (AdaptedWorldViewRevoltTurnChanges adaptor: aInfo.worldViewRevoltTurnChanges) {
					if (StringUtils.hasCharacters(adaptor.worldViewType)) {
						info.addWorldViewRevoltTurnChange(new Pair<String, Integer>(adaptor.worldViewType, adaptor.iChange));
					}
				}
			}

			if (CollectionUtils.hasElements(aInfo.flavors)) {
				for (AdaptedFlavors adaptor: aInfo.flavors) {
					if (StringUtils.hasCharacters(adaptor.flavorType)) {
						info.addFlavor(new Pair<String, Integer>(adaptor.flavorType, adaptor.iFlavor));
					}
				}
			}

			if (CollectionUtils.hasElements(aInfo.orPreReqs)) {
				for (String val: aInfo.orPreReqs) {
					if (StringUtils.hasCharacters(val)) {
						info.addOrPreReq(JaxbUtils.unmarshallString(val));
					}
				}
			}

			if (CollectionUtils.hasElements(aInfo.andPreReqs)) {
				for (String val: aInfo.andPreReqs) {
					if (StringUtils.hasCharacters(val)) {
						info.addAndPreReq(JaxbUtils.unmarshallString(val));
					}
				}
			}

			if (CollectionUtils.hasElements(aInfo.enabledWorldViews)) {
				for (String val: aInfo.enabledWorldViews) {
					if (StringUtils.hasCharacters(val)) {
						info.addEnabledWorldView(JaxbUtils.unmarshallString(val));
					}
				}
			}
			info.setQuote(JaxbUtils.unmarshallString(aInfo.quote));
			info.setSound(JaxbUtils.unmarshallString(aInfo.sound));
			info.setSoundMP(JaxbUtils.unmarshallString(aInfo.soundMP));
			info.setButton(JaxbUtils.unmarshallString(aInfo.button));

			map.put(aInfo.type, info);
		}
		return map;
	}

	@Override
	public TechMap marshal(Map<String, ITechInfo> v) throws Exception {
		TechMap map = new TechMap();
		for (ITechInfo info: v.values()) {
			AdaptedTech aInfo = new AdaptedTech();
			aInfo.type = JaxbUtils.marshallString(info.getType());
			aInfo.description = JaxbUtils.marshallMandatoryString(info.getDescription());
			aInfo.civilopedia = JaxbUtils.marshallString(info.getCivilopedia());
			aInfo.help = JaxbUtils.marshallString(info.getHelp());
			aInfo.strategy = JaxbUtils.marshallString(info.getStrategy());
			aInfo.advisor = JaxbUtils.marshallMandatoryString(info.getAdvisor());
			aInfo.aIWeight = JaxbUtils.marshallInteger(info.getAIWeight());
			aInfo.aITradeModifier = JaxbUtils.marshallInteger(info.getAITradeModifier());
			aInfo.cost = JaxbUtils.marshallMandatoryInteger(info.getCost());
			aInfo.advancedStartCost = JaxbUtils.marshallMandatoryInteger(info.getAdvancedStartCost());
			aInfo.advancedStartCostIncrease = JaxbUtils.marshallInteger(info.getAdvancedStartCostIncrease());
			aInfo.era = JaxbUtils.marshallMandatoryString(info.getEra());
			aInfo.civSettled = JaxbUtils.marshallBoolean(info.isCivSettled());
			aInfo.freeCapitalBuildingClass = JaxbUtils.marshallString(info.getFreeCapitalBuildingClass());
			aInfo.firstFreeUnitClass = JaxbUtils.marshallString(info.getFirstFreeUnitClass());
			aInfo.freeUnitClass = JaxbUtils.marshallString(info.getFreeUnitClass());
			aInfo.featureProductionModifier = JaxbUtils.marshallInteger(info.getFeatureProductionModifier());
			aInfo.workerSpeedModifier = JaxbUtils.marshallInteger(info.getWorkerSpeedModifier());
			aInfo.tradeRoutes = JaxbUtils.marshallInteger(info.getTradeRoutes());
			aInfo.health = JaxbUtils.marshallInteger(info.getHealth());
			aInfo.happiness = JaxbUtils.marshallInteger(info.getHappiness());
			aInfo.firstFreeTechs = JaxbUtils.marshallInteger(info.getFirstFreeTechs());
			aInfo.asset = JaxbUtils.marshallMandatoryInteger(info.getAsset());
			aInfo.power = JaxbUtils.marshallInteger(info.getPower());
			aInfo.repeat = JaxbUtils.marshallBoolean(info.isRepeat());
			aInfo.trade = JaxbUtils.marshallBoolean(info.isTrade());
			aInfo.embassyTrading = JaxbUtils.marshallBoolean(info.isEmbassyTrading());
			aInfo.freeTradeAgreementTrading = JaxbUtils.marshallBoolean(info.isFreeTradeAgreementTrading());
			aInfo.nonAggressionTrading = JaxbUtils.marshallBoolean(info.isNonAggressionTrading());
			aInfo.disable = JaxbUtils.marshallBoolean(info.isDisable());
			aInfo.goodyTech = JaxbUtils.marshallBoolean(info.isGoodyTech());
			aInfo.extraWaterSeeFrom = JaxbUtils.marshallBoolean(info.isExtraWaterSeeFrom());
			aInfo.mapCentering = JaxbUtils.marshallBoolean(info.isMapCentering());
			aInfo.mapVisible = JaxbUtils.marshallBoolean(info.isMapVisible());
			aInfo.mapTrading = JaxbUtils.marshallBoolean(info.isMapTrading());
			aInfo.techTrading = JaxbUtils.marshallBoolean(info.isTechTrading());
			aInfo.goldTrading = JaxbUtils.marshallBoolean(info.isGoldTrading());
			aInfo.openBordersTrading = JaxbUtils.marshallBoolean(info.isOpenBordersTrading());
			aInfo.limitedBordersTrading = JaxbUtils.marshallBoolean(info.isLimitedBordersTrading());
			aInfo.defensivePactTrading = JaxbUtils.marshallBoolean(info.isDefensivePactTrading());
			aInfo.permanentAllianceTrading = JaxbUtils.marshallBoolean(info.isPermanentAllianceTrading());
			aInfo.vassalTrading = JaxbUtils.marshallBoolean(info.isVassalTrading());
			aInfo.bridgeBuilding = JaxbUtils.marshallBoolean(info.isBridgeBuilding());
			aInfo.irrigation = JaxbUtils.marshallBoolean(info.isIrrigation());
			aInfo.ignoreIrrigation = JaxbUtils.marshallBoolean(info.isIgnoreIrrigation());
			aInfo.waterWork = JaxbUtils.marshallBoolean(info.isWaterWork());
			aInfo.canPassPeaks = JaxbUtils.marshallBoolean(info.isCanPassPeaks());
			aInfo.moveFastPeaks = JaxbUtils.marshallBoolean(info.isMoveFastPeaks());
			aInfo.canFoundOnPeaks = JaxbUtils.marshallBoolean(info.isCanFoundOnPeaks());
			aInfo.gridX = JaxbUtils.marshallMandatoryInteger(info.getGridX());
			aInfo.gridY = JaxbUtils.marshallMandatoryInteger(info.getGridY());

			if (CollectionUtils.hasElements(info.getDomainExtraMoves())) {
				aInfo.domainExtraMoves = new ArrayList<AdaptedDomainExtraMoves>();
				for (IPair<String, Integer> pair: info.getDomainExtraMoves()) {
					AdaptedDomainExtraMoves adaptor = new AdaptedDomainExtraMoves();
					adaptor.domainType = pair.getKey();
					adaptor.iExtraMoves = pair.getValue();
					aInfo.domainExtraMoves.add(adaptor);
				}
			}

			if (CollectionUtils.hasElements(info.getCommerceModifiers())) {
				aInfo.commerceModifiers = new ArrayList<Integer>();
				for(Integer val: info.getCommerceModifiers()) {
					aInfo.commerceModifiers.add(JaxbUtils.marshallMandatoryInteger(val));
				}
			}

			if (CollectionUtils.hasElements(info.getSpecialistExtraCommerces())) {
				aInfo.specialistExtraCommerces = new ArrayList<Integer>();
				for(Integer val: info.getSpecialistExtraCommerces()) {
					aInfo.specialistExtraCommerces.add(JaxbUtils.marshallMandatoryInteger(val));
				}
			}

			if (CollectionUtils.hasElements(info.getCommerceFlexible())) {
				aInfo.commerceFlexible = new ArrayList<Integer>();
				for(Boolean val: info.getCommerceFlexible()) {
					aInfo.commerceFlexible.add(JaxbUtils.marshallMandatoryBoolean(val));
				}
			}

			if (CollectionUtils.hasElements(info.getTerrainTrades())) {
				aInfo.terrainTrades = new ArrayList<String>();
				for(String val: info.getTerrainTrades()) {
					aInfo.terrainTrades.add(JaxbUtils.marshallMandatoryString(val));
				}
			}
			aInfo.riverTrade = JaxbUtils.marshallBoolean(info.isRiverTrade());
			aInfo.captureCities = JaxbUtils.marshallBoolean(info.isCaptureCities());
			aInfo.unitRangeUnbound = JaxbUtils.marshallBoolean(info.isUnitRangeUnbound());
			aInfo.unitTerritoryUnbound = JaxbUtils.marshallBoolean(info.isUnitTerritoryUnbound());
			aInfo.unitRangeChange = JaxbUtils.marshallInteger(info.getUnitRangeChange());
			aInfo.unitRangeModifier = JaxbUtils.marshallInteger(info.getUnitRangeModifier());
			aInfo.cultureDefenceModifier = JaxbUtils.marshallInteger(info.getCultureDefenceModifier());

			if (CollectionUtils.hasElements(info.getForestPlotYieldChanges())) {
				aInfo.forestPlotYieldChanges = new ArrayList<Integer>();
				for(Integer val: info.getForestPlotYieldChanges()) {
					aInfo.forestPlotYieldChanges.add(JaxbUtils.marshallMandatoryInteger(val));
				}
			}

			if (CollectionUtils.hasElements(info.getRiverPlotYieldChanges())) {
				aInfo.riverPlotYieldChanges = new ArrayList<Integer>();
				for(Integer val: info.getRiverPlotYieldChanges()) {
					aInfo.riverPlotYieldChanges.add(JaxbUtils.marshallMandatoryInteger(val));
				}
			}

			if (CollectionUtils.hasElements(info.getSeaPlotYieldChanges())) {
				aInfo.seaPlotYieldChanges = new ArrayList<Integer>();
				for(Integer val: info.getSeaPlotYieldChanges()) {
					aInfo.seaPlotYieldChanges.add(JaxbUtils.marshallMandatoryInteger(val));
				}
			}

			if (CollectionUtils.hasElements(info.getWorldViewRevoltTurnChanges())) {
				aInfo.worldViewRevoltTurnChanges = new ArrayList<AdaptedWorldViewRevoltTurnChanges>();
				for (IPair<String, Integer> pair: info.getWorldViewRevoltTurnChanges()) {
					AdaptedWorldViewRevoltTurnChanges adaptor = new AdaptedWorldViewRevoltTurnChanges();
					adaptor.worldViewType = pair.getKey();
					adaptor.iChange = pair.getValue();
					aInfo.worldViewRevoltTurnChanges.add(adaptor);
				}
			}

			if (CollectionUtils.hasElements(info.getFlavors())) {
				aInfo.flavors = new ArrayList<AdaptedFlavors>();
				for (IPair<String, Integer> pair: info.getFlavors()) {
					AdaptedFlavors adaptor = new AdaptedFlavors();
					adaptor.flavorType = pair.getKey();
					adaptor.iFlavor = pair.getValue();
					aInfo.flavors.add(adaptor);
				}
			}

			if (CollectionUtils.hasElements(info.getOrPreReqs())) {
				aInfo.orPreReqs = new ArrayList<String>();
				for(String val: info.getOrPreReqs()) {
					aInfo.orPreReqs.add(JaxbUtils.marshallMandatoryString(val));
				}
			}

			if (CollectionUtils.hasElements(info.getAndPreReqs())) {
				aInfo.andPreReqs = new ArrayList<String>();
				for(String val: info.getAndPreReqs()) {
					aInfo.andPreReqs.add(JaxbUtils.marshallMandatoryString(val));
				}
			}

			if (CollectionUtils.hasElements(info.getEnabledWorldViews())) {
				aInfo.enabledWorldViews = new ArrayList<String>();
				for(String val: info.getEnabledWorldViews()) {
					aInfo.enabledWorldViews.add(JaxbUtils.marshallMandatoryString(val));
				}
			}
			aInfo.quote = JaxbUtils.marshallString(info.getQuote());
			aInfo.sound = JaxbUtils.marshallString(info.getSound());
			aInfo.soundMP = JaxbUtils.marshallString(info.getSoundMP());
			aInfo.button = JaxbUtils.marshallMandatoryString(info.getButton());

			map.entries.add(aInfo);
		}
		return map;
	}
}