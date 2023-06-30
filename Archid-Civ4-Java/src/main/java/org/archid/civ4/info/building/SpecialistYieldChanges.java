package org.archid.civ4.info.building;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlJavaTypeAdapter(SpecialistYieldChangesAdapter.class)
class SpecialistYieldChanges{
	private List<SpecialistYieldChange> wrapper = new ArrayList<SpecialistYieldChange>();

	public List<SpecialistYieldChange> getSpecialistYieldChangeList() {
		return wrapper;
	}

	public void setSpecialistYieldChangeList(List<SpecialistYieldChange> wrapper){
		this.wrapper = wrapper;
	}

	static class SpecialistYieldChange {
		private String resource;
		private List<Integer> list = new ArrayList<Integer>();

		public void setResource (String resource) {
			this.resource = resource;
		}

		public String getResource() {
			return resource;
		}

		public void addElement(Integer element) {
			list.add(element);
		}

		public List<Integer> getElements() {
			return list;
		}
	}
}