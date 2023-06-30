/**
 * 
 */
package org.archid.civ4.info;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.annotation.XmlTransient;

/**
 * @author Jim
 * @param <T>
 *
 */
@XmlTransient
public abstract class AbstractInfos<T extends IInfo> implements IInfos<T> {

	protected Map<String, T> infos;
	
	public AbstractInfos(Map<String, T> infos) {
		this.infos = infos;
	}

	@Override
	public boolean addInfo(T info) {
		boolean isNew = false;
		if (!infos.containsKey(info.getType())) {
			infos.put(info.getType(), info);
			isNew = true;
		}
		return isNew;
	}
	
	@Override
	public T getInfo(String type) {
		return infos.get(type);
	}
	
	
	@Override
	public Set<String> getInfoTypes() {
		return infos.keySet();
	}
	
	@Override 
	public Set<T> getInfos() {
		return new HashSet<T>(infos.values());
	}
	
	@Override
	public boolean containsInfo(String type) {
		return infos.containsKey(type);
	}
	
}
