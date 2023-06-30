package org.archid.civ4.info.building;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlJavaTypeAdapter(TechCommerceChangesAdapter.class)
class TechCommerceChanges{
	private List<TechCommerceChange> wrapper = new ArrayList<TechCommerceChange>();

	public List<TechCommerceChange> getTechCommerceChangeList() {
		return wrapper;
	}

	public void setTechCommerceChangeList(List<TechCommerceChange> wrapper){
		this.wrapper = wrapper;
	}

	static class TechCommerceChange {
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