package org.archid.civ4.info.bonus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import org.archid.utils.CollectionUtils;
import org.archid.utils.JaxbUtils;
import org.archid.utils.StringUtils;

public class BonusMapAdapter extends XmlAdapter<BonusMapAdapter.BonusMap, Map<String, IBonusInfo>> {

	public static class BonusMap {
		@XmlElement(name = "BonusInfo")
		List<AdaptedBonus> entries = new ArrayList<AdaptedBonus>();
	}

	private static class AdaptedBonus {
		@XmlElement(name="Type")
		private String type;
		@XmlElement(name="Description")
		private String description;
		@XmlElement(name="Civilopedia")
		private String civilopedia;
		@XmlElement(name="Help")
		private String help;
		@XmlElement(name="BonusClassType")
		private String bonusClassType;
		@XmlElement(name="ArtDefineTag")
		private String artDefineTag;
		@XmlElement(name="TechReveal")
		private String techReveal;
		@XmlElement(name="TechCityTrade")
		private String techCityTrade;
		@XmlElement(name="TechObsolete")
		private String techObsolete;
		@XmlElementWrapper(name="YieldChanges")
		@XmlElement(name="iYieldChange")
		private List<Integer> yieldChanges;
		@XmlElement(name="iAITradeModifier")
		private Integer aITradeModifier;
		@XmlElement(name="iAIObjective")
		private Integer aIObjective;
		@XmlElement(name="iHealth")
		private Integer health;
		@XmlElement(name="iHappiness")
		private Integer happiness;
		@XmlElement(name="iPlacementOrder")
		private Integer placementOrder;
		@XmlElement(name="iConstAppearance")
		private Integer constAppearance;
		@XmlElement(name="iMinAreaSize")
		private Integer minAreaSize;
		@XmlElement(name="iMinLatitude")
		private Integer minLatitude;
		@XmlElement(name="iMaxLatitude")
		private Integer maxLatitude;
		@XmlElement(name="Rands")
		private Rands rands;
		@XmlElement(name="iPlayer")
		private Integer player;
		@XmlElement(name="iTilesPer")
		private Integer tilesPer;
		@XmlElement(name="iMinLandPercent")
		private Integer minLandPercent;
		@XmlElement(name="iUnique")
		private Integer unique;
		@XmlElement(name="iGroupRange")
		private Integer groupRange;
		@XmlElement(name="iGroupRand")
		private Integer groupRand;
		@XmlElement(name="bArea")
		private Integer area;
		@XmlElement(name="bHills")
		private Integer hills;
		@XmlElement(name="bPeaks")
		private Integer peaks;
		@XmlElement(name="bFlatlands")
		private Integer flatlands;
		@XmlElement(name="bNoRiverSide")
		private Integer noRiverSide;
		@XmlElement(name="bNormalize")
		private Integer normalize;
		@XmlElementWrapper(name="TerrainBooleans")
		@XmlElement(name="TerrainType")
		private List<String> terrainBooleans;
		@XmlElementWrapper(name="FeatureBooleans")
		@XmlElement(name="FeatureType")
		private List<String> featureBooleans;
		@XmlElementWrapper(name="FeatureTerrainBooleans")
		@XmlElement(name="TerrainType")
		private List<String> featureTerrainBooleans;
		@XmlElement(name="bUseLSystem")
		private Integer useLSystem;
	}

	@Override
	public Map<String, IBonusInfo> unmarshal(BonusMap v) throws Exception {
		Map<String, IBonusInfo> map = new TreeMap<String, IBonusInfo>();
		for (AdaptedBonus aInfo: v.entries) {
			IBonusInfo info = BonusInfos.createInfo(aInfo.type);
			info.setDescription(JaxbUtils.unmarshallString(aInfo.description));
			info.setCivilopedia(JaxbUtils.unmarshallString(aInfo.civilopedia));
			info.setHelp(JaxbUtils.unmarshallString(aInfo.help));
			info.setBonusClassType(JaxbUtils.unmarshallString(aInfo.bonusClassType));
			info.setArtDefineTag(JaxbUtils.unmarshallString(aInfo.artDefineTag));
			info.setTechReveal(JaxbUtils.unmarshallString(aInfo.techReveal));
			info.setTechCityTrade(JaxbUtils.unmarshallString(aInfo.techCityTrade));
			info.setTechObsolete(JaxbUtils.unmarshallString(aInfo.techObsolete));

			if (CollectionUtils.hasElements(aInfo.yieldChanges)) {
				for (Integer val: aInfo.yieldChanges) {
					info.addYieldChange(val);
				}
			}
			info.setAITradeModifier(JaxbUtils.unmarshallInteger(aInfo.aITradeModifier));
			info.setAIObjective(JaxbUtils.unmarshallInteger(aInfo.aIObjective));
			info.setHealth(JaxbUtils.unmarshallInteger(aInfo.health));
			info.setHappiness(JaxbUtils.unmarshallInteger(aInfo.happiness));
			info.setPlacementOrder(JaxbUtils.unmarshallInteger(aInfo.placementOrder));
			info.setConstAppearance(JaxbUtils.unmarshallInteger(aInfo.constAppearance));
			info.setMinAreaSize(JaxbUtils.unmarshallInteger(aInfo.minAreaSize));
			info.setMinLatitude(JaxbUtils.unmarshallInteger(aInfo.minLatitude));
			info.setMaxLatitude(JaxbUtils.unmarshallInteger(aInfo.maxLatitude));
			info.setRands(aInfo.rands);
			info.setPlayer(JaxbUtils.unmarshallInteger(aInfo.player));
			info.setTilesPer(JaxbUtils.unmarshallInteger(aInfo.tilesPer));
			info.setMinLandPercent(JaxbUtils.unmarshallInteger(aInfo.minLandPercent));
			info.setUnique(JaxbUtils.unmarshallInteger(aInfo.unique));
			info.setGroupRange(JaxbUtils.unmarshallInteger(aInfo.groupRange));
			info.setGroupRand(JaxbUtils.unmarshallInteger(aInfo.groupRand));
			info.setArea(JaxbUtils.unmarshallBoolean(aInfo.area));
			info.setHills(JaxbUtils.unmarshallBoolean(aInfo.hills));
			info.setPeaks(JaxbUtils.unmarshallBoolean(aInfo.peaks));
			info.setFlatlands(JaxbUtils.unmarshallBoolean(aInfo.flatlands));
			info.setNoRiverSide(JaxbUtils.unmarshallBoolean(aInfo.noRiverSide));
			info.setNormalize(JaxbUtils.unmarshallBoolean(aInfo.normalize));

			if (CollectionUtils.hasElements(aInfo.terrainBooleans)) {
				for (String val: aInfo.terrainBooleans) {
					if (StringUtils.hasCharacters(val)) {
						info.addTerrainBoolean(val);
					}
				}
			}

			if (CollectionUtils.hasElements(aInfo.featureBooleans)) {
				for (String val: aInfo.featureBooleans) {
					if (StringUtils.hasCharacters(val)) {
						info.addFeatureBoolean(val);
					}
				}
			}

			if (CollectionUtils.hasElements(aInfo.featureTerrainBooleans)) {
				for (String val: aInfo.featureTerrainBooleans) {
					if (StringUtils.hasCharacters(val)) {
						info.addFeatureTerrainBoolean(val);
					}
				}
			}
			info.setUseLSystem(JaxbUtils.unmarshallBoolean(aInfo.useLSystem));

			map.put(aInfo.type, info);
		}
		return map;
	}

	@Override
	public BonusMap marshal(Map<String, IBonusInfo> v) throws Exception {
		BonusMap map = new BonusMap();
		for (IBonusInfo info: v.values()) {
			AdaptedBonus aInfo = new AdaptedBonus();
			aInfo.type = info.getType();
			aInfo.description = JaxbUtils.marshallMandatoryString(info.getDescription());
			aInfo.civilopedia = JaxbUtils.marshallString(info.getCivilopedia());
			aInfo.help = JaxbUtils.marshallString(info.getHelp());
			aInfo.bonusClassType = JaxbUtils.marshallString(info.getBonusClassType());
			aInfo.artDefineTag = JaxbUtils.marshallString(info.getArtDefineTag());
			aInfo.techReveal = JaxbUtils.marshallString(info.getTechReveal());
			aInfo.techCityTrade = JaxbUtils.marshallString(info.getTechCityTrade());
			aInfo.techObsolete = JaxbUtils.marshallString(info.getTechObsolete());

			if (CollectionUtils.hasElements(info.getYieldChanges())) {
				aInfo.yieldChanges = new ArrayList<Integer>();
				for(Integer val: info.getYieldChanges()) {
					aInfo.yieldChanges.add(val);
				}
			}
			aInfo.aITradeModifier = JaxbUtils.marshallInteger(info.getAITradeModifier());
			aInfo.aIObjective = JaxbUtils.marshallInteger(info.getAIObjective());
			aInfo.health = JaxbUtils.marshallInteger(info.getHealth());
			aInfo.happiness = JaxbUtils.marshallInteger(info.getHappiness());
			aInfo.placementOrder = JaxbUtils.marshallInteger(info.getPlacementOrder());
			aInfo.constAppearance = JaxbUtils.marshallInteger(info.getConstAppearance());
			aInfo.minAreaSize = JaxbUtils.marshallInteger(info.getMinAreaSize());
			aInfo.minLatitude = JaxbUtils.marshallInteger(info.getMinLatitude());
			aInfo.maxLatitude = JaxbUtils.marshallInteger(info.getMaxLatitude());
			aInfo.rands = info.getRands();
			aInfo.player = JaxbUtils.marshallInteger(info.getPlayer());
			aInfo.tilesPer = JaxbUtils.marshallInteger(info.getTilesPer());
			aInfo.minLandPercent = JaxbUtils.marshallInteger(info.getMinLandPercent());
			aInfo.unique = JaxbUtils.marshallInteger(info.getUnique());
			aInfo.groupRange = JaxbUtils.marshallInteger(info.getGroupRange());
			aInfo.groupRand = JaxbUtils.marshallInteger(info.getGroupRand());
			aInfo.area = JaxbUtils.marshallBoolean(info.isArea());
			aInfo.hills = JaxbUtils.marshallBoolean(info.isHills());
			aInfo.peaks = JaxbUtils.marshallBoolean(info.isPeaks());
			aInfo.flatlands = JaxbUtils.marshallBoolean(info.isFlatlands());
			aInfo.noRiverSide = JaxbUtils.marshallBoolean(info.isNoRiverSide());
			aInfo.normalize = JaxbUtils.marshallBoolean(info.isNormalize());

			if (CollectionUtils.hasElements(info.getTerrainBooleans())) {
				aInfo.terrainBooleans = new ArrayList<String>();
				for(String val: info.getTerrainBooleans()) {
					aInfo.terrainBooleans.add(val);
				}
			}

			if (CollectionUtils.hasElements(info.getFeatureBooleans())) {
				aInfo.featureBooleans = new ArrayList<String>();
				for(String val: info.getFeatureBooleans()) {
					aInfo.featureBooleans.add(val);
				}
			}

			if (CollectionUtils.hasElements(info.getFeatureTerrainBooleans())) {
				aInfo.featureTerrainBooleans = new ArrayList<String>();
				for(String val: info.getFeatureTerrainBooleans()) {
					aInfo.featureTerrainBooleans.add(val);
				}
			}
			aInfo.useLSystem = JaxbUtils.marshallBoolean(info.isUseLSystem());

			map.entries.add(aInfo);
		}
		return map;
	}
}