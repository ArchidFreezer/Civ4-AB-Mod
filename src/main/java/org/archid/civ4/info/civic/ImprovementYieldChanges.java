package org.archid.civ4.info.civic;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlJavaTypeAdapter(ImprovementYieldChangesAdapter.class)
class ImprovementYieldChanges{
	private List<ImprovementYieldChange> wrapper = new ArrayList<ImprovementYieldChange>();

	public List<ImprovementYieldChange> getImprovementYieldChangeList() {
		return wrapper;
	}

	public void setImprovementYieldChangeList(List<ImprovementYieldChange> wrapper){
		this.wrapper = wrapper;
	}

	static class ImprovementYieldChange {
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