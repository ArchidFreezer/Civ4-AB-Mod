/**
 * 
 */
package org.archid.utils;

/**
 * Interface describing a class that contains a key-value-data set.
 * <p>
 * The values can be of any object type
 * 
 * @author jim
 *
 * @param <K> Object type of the key
 * @param <V> Object type of the value
 * @param <D> Object type of the value data
 */
public interface ITriple <K, V, D> {

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

	/**
	 * Sets the data of the pair
	 * 
	 * @param data value to set
	 */
	void setData(D data);
	
	/**
	 * Gets the data of the pair
	 * 
	 * @return data object
	 */
	public D getData();

}
