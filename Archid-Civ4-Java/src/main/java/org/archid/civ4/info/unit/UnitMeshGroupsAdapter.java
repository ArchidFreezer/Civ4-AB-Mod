package org.archid.civ4.info.unit;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.archid.civ4.info.unit.UnitMeshGroups.UnitMeshGroup;
import org.archid.utils.CollectionUtils;
import org.archid.utils.JaxbUtils;

public class UnitMeshGroupsAdapter extends XmlAdapter<UnitMeshGroupsAdapter.AdaptedUnitMeshGroups, UnitMeshGroups>{
	
	public static class AdaptedUnitMeshGroups {
		@XmlElement(name="iGroupSize")
		private Integer groupSize;
		@XmlElement(name="fMaxSpeed")
		private Float maxSpeed;
		@XmlElement(name="fPadTime")
		private Float padTime;
		@XmlElement(name="iMeleeWaveSize")
		private Integer meleeWaveSize;
		@XmlElement(name="iRangedWaveSize")
		private Integer rangedWaveSize;
		@XmlElement(name="UnitMeshGroup")
		private List<AdaptedUnitMeshGroup> meshGroups;
	}
	
	public static class AdaptedUnitMeshGroup {
		@XmlElement(name="iRequired")
		private Integer required;
		@XmlElement(name="EarlyArtDefineTag")
		private String earlyArtDefineTag;
		@XmlElement(name="LateArtDefineTag")
		private String lateArtDefineTag;
		@XmlElement(name="MiddleArtDefineTag")
		private String middleArtDefineTag;
	}

	@Override
	public UnitMeshGroups unmarshal(AdaptedUnitMeshGroups v) throws Exception {
		UnitMeshGroups group = new UnitMeshGroups();
		group.setGroupSize(v.groupSize);
		group.setMaxSpeed(v.maxSpeed);
		group.setPadTime(v.padTime);
		group.setMeleeWaveSize(v.meleeWaveSize);
		group.setRangedWaveSize(v.rangedWaveSize);
		if (CollectionUtils.hasElements(v.meshGroups)) {
			for (AdaptedUnitMeshGroup meshGroup: v.meshGroups) {
				UnitMeshGroup mesh = new UnitMeshGroup();
				mesh.setRequired(meshGroup.required);
				mesh.setEarlyArtDefineTag(JaxbUtils.unmarshallString(meshGroup.earlyArtDefineTag));
				mesh.setMiddleArtDefineTag(JaxbUtils.unmarshallString(meshGroup.middleArtDefineTag));
				mesh.setLateArtDefineTag(JaxbUtils.unmarshallString(meshGroup.lateArtDefineTag));
				group.getUnitMeshGroupList().add(mesh);
			}
		}
		return group;
	}

	@Override
	public AdaptedUnitMeshGroups marshal(UnitMeshGroups v) throws Exception {
		AdaptedUnitMeshGroups group = new AdaptedUnitMeshGroups();
		group.groupSize = v.getGroupSize();
		group.maxSpeed = v.getMaxSpeed();
		group.padTime = v.getPadTime();
		group.meleeWaveSize = v.getMeleeWaveSize();
		group.rangedWaveSize = v.getRangedWaveSize();
		if (CollectionUtils.hasElements(v.getUnitMeshGroupList())) {
			group.meshGroups = new ArrayList<AdaptedUnitMeshGroup>();
			for (UnitMeshGroup meshGroup: v.getUnitMeshGroupList()) {
				AdaptedUnitMeshGroup mesh = new AdaptedUnitMeshGroup();
				mesh.required = meshGroup.getRequired();
				mesh.earlyArtDefineTag = JaxbUtils.marshallString(meshGroup.getEarlyArtDefineTag());
				mesh.middleArtDefineTag = JaxbUtils.marshallString(meshGroup.getMiddleArtDefineTag());
				mesh.lateArtDefineTag = JaxbUtils.marshallString(meshGroup.getLateArtDefineTag());
				group.meshGroups.add(mesh);
			}
		}
		return group;
	}

}
