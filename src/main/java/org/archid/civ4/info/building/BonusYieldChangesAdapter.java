package org.archid.civ4.info.building;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.archid.civ4.info.building.BonusYieldChanges.BonusYieldChange;
import org.archid.utils.CollectionUtils;
import org.archid.utils.JaxbUtils;

public class BonusYieldChangesAdapter extends XmlAdapter<BonusYieldChangesAdapter.AdaptedBonusYieldChanges, BonusYieldChanges> {

	public static class AdaptedBonusYieldChanges {
		@XmlElement(name = "BonusYieldChange")
		private List<AdaptedBonusYieldChange> entries = new ArrayList<AdaptedBonusYieldChange>();
	}

	static class AdaptedBonusYieldChange {
		@XmlElement(name="BonusType")
		String resource;
		@XmlElementWrapper(name="YieldChanges")
		@XmlElement(name="iYield")
		List<Integer> list;
	}

	@Override
	public BonusYieldChanges unmarshal(AdaptedBonusYieldChanges v) throws Exception {
		BonusYieldChanges changes = new BonusYieldChanges();
		for (AdaptedBonusYieldChange adapter: v.entries) {
			BonusYieldChange wrapper = new BonusYieldChange();
			wrapper.setResource(JaxbUtils.unmarshallString(adapter.resource));
			if (CollectionUtils.hasElements(adapter.list)) {
				for (Integer val: adapter.list) {
					wrapper.addElement(val);
				}
			}
			changes.getBonusYieldChangeList().add(wrapper);
		}
		return changes;
	}

	@Override
	public AdaptedBonusYieldChanges marshal(BonusYieldChanges v) throws Exception {
		if (!CollectionUtils.hasElements(v.getBonusYieldChangeList())) return null;

		AdaptedBonusYieldChanges changes = new AdaptedBonusYieldChanges();
		for(BonusYieldChange wrapper: v.getBonusYieldChangeList()) {
			AdaptedBonusYieldChange adapter = new AdaptedBonusYieldChange();
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