package org.archid.utils;

/**
 * Interface describing a class that contains a key-value pair.
 * <p>
 * The keys and values can be of any object type
 * 
 * @author jim
 *
 * @param <K> Object type of the key
 * @param <V> Object type of the value
 */
public interface IPair <K, V> {
	
	/**
	 * Sets the key of the pair
	 * 
	 * @param key value to set as the key
	 */
	void setKey(K key);
	
	/**
	 * Gets the key of the pair
	 * 
	 * @return key
	 */
	public K getKey();
	
	/**
	 * Sets the value of the pair
	 * 
	 * @param value value to set
	 */
	void setValue(V value);
	
	/**
	 * Gets the value of the pair
	 * 
	 * @return value object
	 */
	public V getValue();

}
