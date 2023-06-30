package org.archid.civ4.info.building;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlJavaTypeAdapter(TechYieldChangesAdapter.class)
class TechYieldChanges{
	private List<TechYieldChange> wrapper = new ArrayList<TechYieldChange>();

	public List<TechYieldChange> getTechYieldChangeList() {
		return wrapper;
	}

	public void setTechYieldChangeList(List<TechYieldChange> wrapper){
		this.wrapper = wrapper;
	}

	static class TechYieldChange {
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