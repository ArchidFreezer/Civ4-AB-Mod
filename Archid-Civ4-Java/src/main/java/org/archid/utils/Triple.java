/**
 * 
 */
package org.archid.utils;

/**
 * Implementation of the {@link ITriple} interface
 * 
 * @author jim
 *
 * @param <K> Object type of the key
 * @param <V> Object type of the value
 * @param <D> Object type of the value data
 */
public class Triple <K, V, D> implements ITriple<K, V, D> {

	private K key;
	private V value;
	private D data;

	public Triple(K key, V value, D data) {
		this.key = key;
		this.value = value;
		this.data = data;
	}

	@Override
	public void setKey(K key) {
		this.key = key;
	}

	@Override
	public K getKey() {
		return key;
	}

	@Override
	public void setValue(V value) {
		this.value = value;
	}

	@Override
	public V getValue() {
		return value;
	}

	@Override
	public void setData(D data) {
		this.data = data;
	}

	@Override
	public D getData() {
		return data;
	}

}
