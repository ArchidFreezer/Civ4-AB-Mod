package org.archid.civ4.info.bonus;

import org.archid.civ4.info.AbstractConverter;
import org.archid.civ4.info.IXmlTaggedInfo;
import org.archid.utils.StringUtils;

public class BonusConverter extends AbstractConverter {

	@Override
	public IXmlTaggedInfo updateCustomTags(IXmlTaggedInfo info) {
		
		String tmp = info.getTagXml("FeatureBooleans");
		if (StringUtils.hasCharacters(tmp)) {
			tmp = removeTag(tmp, "FeatureBoolean>");
			tmp = removeTag(tmp, "bFeature>");
			tmp = tmp.replaceAll("\\t<FeatureType", "<FeatureType");
			info.setTagXml("FeatureBooleans", tmp);
		}
		
		tmp = info.getTagXml("TerrainBooleans");
		if (StringUtils.hasCharacters(tmp)) {
			tmp = removeTag(tmp, "TerrainBoolean>");
			tmp = removeTag(tmp, "bTerrain>");
			tmp = tmp.replaceAll("\\t<TerrainType", "<TerrainType");
			info.setTagXml("TerrainBooleans", tmp);
		}
		
		tmp = info.getTagXml("FeatureTerrainBooleans");
		if (StringUtils.hasCharacters(tmp)) {
			tmp = removeTag(tmp, "FeatureTerrainBoolean>");
			tmp = removeTag(tmp, "bFeatureTerrain>");
			tmp = tmp.replaceAll("\\t<TerrainType", "<TerrainType");
			info.setTagXml("FeatureTerrainBooleans", tmp);
		}
		
		return info;
	}
	
}
