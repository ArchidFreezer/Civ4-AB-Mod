package org.archid.civ4.info.building;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.archid.civ4.info.building.SpecialistYieldChanges.SpecialistYieldChange;
import org.archid.utils.CollectionUtils;
import org.archid.utils.JaxbUtils;

public class SpecialistYieldChangesAdapter extends XmlAdapter<SpecialistYieldChangesAdapter.AdaptedSpecialistYieldChanges, SpecialistYieldChanges> {

	public static class AdaptedSpecialistYieldChanges {
		@XmlElement(name = "SpecialistYieldChange")
		private List<AdaptedSpecialistYieldChange> entries = new ArrayList<AdaptedSpecialistYieldChange>();
	}

	static class AdaptedSpecialistYieldChange {
		@XmlElement(name="SpecialistType")
		String resource;
		@XmlElementWrapper(name="YieldChanges")
		@XmlElement(name="iYield")
		List<Integer> list;
	}

	@Override
	public SpecialistYieldChanges unmarshal(AdaptedSpecialistYieldChanges v) throws Exception {
		SpecialistYieldChanges changes = new SpecialistYieldChanges();
		for (AdaptedSpecialistYieldChange adapter: v.entries) {
			SpecialistYieldChange wrapper = new SpecialistYieldChange();
			wrapper.setResource(JaxbUtils.unmarshallString(adapter.resource));
			if (CollectionUtils.hasElements(adapter.list)) {
				for (Integer val: adapter.list) {
					wrapper.addElement(val);
				}
			}
			changes.getSpecialistYieldChangeList().add(wrapper);
		}
		return changes;
	}

	@Override
	public AdaptedSpecialistYieldChanges marshal(SpecialistYieldChanges v) throws Exception {
		if (!CollectionUtils.hasElements(v.getSpecialistYieldChangeList())) return null;

		AdaptedSpecialistYieldChanges changes = new AdaptedSpecialistYieldChanges();
		for(SpecialistYieldChange wrapper: v.getSpecialistYieldChangeList()) {
			AdaptedSpecialistYieldChange adapter = new AdaptedSpecialistYieldChange();
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