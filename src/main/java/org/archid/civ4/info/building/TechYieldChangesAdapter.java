package org.archid.civ4.info.building;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.archid.civ4.info.building.TechYieldChanges.TechYieldChange;
import org.archid.utils.CollectionUtils;
import org.archid.utils.JaxbUtils;

public class TechYieldChangesAdapter extends XmlAdapter<TechYieldChangesAdapter.AdaptedTechYieldChanges, TechYieldChanges> {

	public static class AdaptedTechYieldChanges {
		@XmlElement(name = "TechYieldChange")
		private List<AdaptedTechYieldChange> entries = new ArrayList<AdaptedTechYieldChange>();
	}

	static class AdaptedTechYieldChange {
		@XmlElement(name="TechType")
		String resource;
		@XmlElementWrapper(name="YieldChanges")
		@XmlElement(name="iYield")
		List<Integer> list;
	}

	@Override
	public TechYieldChanges unmarshal(AdaptedTechYieldChanges v) throws Exception {
		TechYieldChanges changes = new TechYieldChanges();
		for (AdaptedTechYieldChange adapter: v.entries) {
			TechYieldChange wrapper = new TechYieldChange();
			wrapper.setResource(JaxbUtils.unmarshallString(adapter.resource));
			if (CollectionUtils.hasElements(adapter.list)) {
				for (Integer val: adapter.list) {
					wrapper.addElement(val);
				}
			}
			changes.getTechYieldChangeList().add(wrapper);
		}
		return changes;
	}

	@Override
	public AdaptedTechYieldChanges marshal(TechYieldChanges v) throws Exception {
		if (!CollectionUtils.hasElements(v.getTechYieldChangeList())) return null;

		AdaptedTechYieldChanges changes = new AdaptedTechYieldChanges();
		for(TechYieldChange wrapper: v.getTechYieldChangeList()) {
			AdaptedTechYieldChange adapter = new AdaptedTechYieldChange();
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