package org.archid.utils;

/**
 * Implementation of the {@link IPair} interface
 * 
 * @author jim
 *
 * @param <K> Object type of the key
 * @param <V> Object type of the value
 */
public class Pair<K, V> implements IPair<K, V> {
	
	private K key;
	private V value;
	
	public Pair(K key, V value) {
		this.key = key;
		this.value = value;
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

}
