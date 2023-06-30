package org.archid.civ4.info.building;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.archid.civ4.info.building.VicinityBonusYieldChanges.VicinityBonusYieldChange;
import org.archid.utils.CollectionUtils;
import org.archid.utils.JaxbUtils;

public class VicinityBonusYieldChangesAdapter extends XmlAdapter<VicinityBonusYieldChangesAdapter.AdaptedVicinityBonusYieldChanges, VicinityBonusYieldChanges> {

	public static class AdaptedVicinityBonusYieldChanges {
		@XmlElement(name = "VicinityBonusYieldChange")
		private List<AdaptedVicinityBonusYieldChange> entries = new ArrayList<AdaptedVicinityBonusYieldChange>();
	}

	static class AdaptedVicinityBonusYieldChange {
		@XmlElement(name="BonusType")
		String resource;
		@XmlElementWrapper(name="YieldChanges")
		@XmlElement(name="iYield")
		List<Integer> list;
	}

	@Override
	public VicinityBonusYieldChanges unmarshal(AdaptedVicinityBonusYieldChanges v) throws Exception {
		VicinityBonusYieldChanges changes = new VicinityBonusYieldChanges();
		for (AdaptedVicinityBonusYieldChange adapter: v.entries) {
			VicinityBonusYieldChange wrapper = new VicinityBonusYieldChange();
			wrapper.setResource(JaxbUtils.unmarshallString(adapter.resource));
			if (CollectionUtils.hasElements(adapter.list)) {
				for (Integer val: adapter.list) {
					wrapper.addElement(val);
				}
			}
			changes.getVicinityBonusYieldChangeList().add(wrapper);
		}
		return changes;
	}

	@Override
	public AdaptedVicinityBonusYieldChanges marshal(VicinityBonusYieldChanges v) throws Exception {
		if (!CollectionUtils.hasElements(v.getVicinityBonusYieldChangeList())) return null;

		AdaptedVicinityBonusYieldChanges changes = new AdaptedVicinityBonusYieldChanges();
		for(VicinityBonusYieldChange wrapper: v.getVicinityBonusYieldChangeList()) {
			AdaptedVicinityBonusYieldChange adapter = new AdaptedVicinityBonusYieldChange();
			adapter.resource = wrapper.getResource();
			adapter.list = new ArrayList<Integer>();
			if (CollectionUtils.hasElements(wrapper.getElements())) {
				adapter.list = wrapper.getElements();
			}
			changes.entries.add(adapter);
		}
		return changes;
	}
}