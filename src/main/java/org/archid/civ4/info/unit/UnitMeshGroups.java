package org.archid.civ4.info.unit;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlJavaTypeAdapter(UnitMeshGroupsAdapter.class) 
class UnitMeshGroups {
	private int groupSize;
	private int meleeWaveSize;
	private int rangedWaveSize;
	private float maxSpeed;
	private float padTime;
	private List<UnitMeshGroup> unitMeshGroupList = new ArrayList<UnitMeshGroup>();
	
	public int getGroupSize() {
		return groupSize;
	}
	
	public void setGroupSize(int groupSize) {
		this.groupSize = groupSize;
	}
	
	public int getMeleeWaveSize() {
		return meleeWaveSize;
	}
	
	public void setMeleeWaveSize(int meleeWaveSize) {
		this.meleeWaveSize = meleeWaveSize;
	}
	
	public int getRangedWaveSize() {
		return rangedWaveSize;
	}
	
	public void setRangedWaveSize(int rangedWaveSize) {
		this.rangedWaveSize = rangedWaveSize;
	}
	
	public float getMaxSpeed() {
		return maxSpeed;
	}
	
	public void setMaxSpeed(float maxSpeed) {
		this.maxSpeed = maxSpeed;
	}
	
	public float getPadTime() {
		return padTime;
	}
	
	public void setPadTime(float padTime) {
		this.padTime = padTime;
	}

	public List<UnitMeshGroup> getUnitMeshGroupList() {
		return unitMeshGroupList;
	}

	public void setUnitMeshGroupList(List<UnitMeshGroup> unitMeshGroupList) {
		this.unitMeshGroupList = unitMeshGroupList;
	}

	static class UnitMeshGroup {
		private int required;
		private String earlyArtDefineTag;
		private String middleArtDefineTag;
		private String lateArtDefineTag;
		
		public int getRequired() {
			return required;
		}
		
		public void setRequired(int required) {
			this.required = required;
		}

		public String getEarlyArtDefineTag() {
			return earlyArtDefineTag;
		}

		public void setEarlyArtDefineTag(String earlyArtDefineTag) {
			this.earlyArtDefineTag = earlyArtDefineTag;
		}

		public String getMiddleArtDefineTag() {
			return middleArtDefineTag;
		}

		public void setMiddleArtDefineTag(String middleArtDefineTag) {
			this.middleArtDefineTag = middleArtDefineTag;
		}

		public String getLateArtDefineTag() {
			return lateArtDefineTag;
		}

		public void setLateArtDefineTag(String lateArtDefineTag) {
			this.lateArtDefineTag = lateArtDefineTag;
		}

	}
}