package org.archid.civ4.info;

import java.util.Map;
import java.util.Set;

public interface IInfos<T extends IInfo> {

	/**
	 * Sets the collection of infos, replacing any existing collection.
	 * 
	 * @param techInfos {@code Map} of infos keyed on the type
	 */
	public void setInfoMap(Map<String, T> infos);

	/**
	 * Gets the collection of infos
	 * <p>
	 * Implementing classes should annotate this to allow for jaxb marshalling/unmarshalling with the following:
	 * <pre>
	 * {@code @XmlJavaTypeAdapter(Adapter.class)}
	 * {@code @XmlElement(name="xxxInfos")}
	 * </pre>
	 * 
	 * @return {@code Map} of infos keyed on the type
	 */
	public Map<String, T> getInfoMap();

	/**
	 * Add a info to the collection of infos. If a info with the same type already exists it will be replaced
	 * 
	 * @param info {@code T} to add
	 * @return {@code true} if the info was added; otherwise {@code false}
	 */
	public boolean addInfo(T info);

	/**
	 * Gets a info
	 * 
	 * @param type Type of the info
	 * @return info with the given type if one exists; otherwise {@code null}
	 */
	public T getInfo(String type);

	/**
	 * Gets the collection of info types
	 * 
	 * @return {@code Set} of info types 
	 */
	public Set<String> getInfoTypes();
	
	/**
	 * Gets the collection of infos
	 * 
	 * @return {@code Set} of {@code T} infos
	 */
	public Set<T> getInfos();
	
	/**
	 * Gets whether the info type exists in the map
	 * 
	 * @param type {@code String} type of info
	 * @return {@code true} if the info type exists; otherwise false
	 */
	public boolean containsInfo(String type);
	
}