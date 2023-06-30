/**
 * 
 */
package org.archid.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

/**
 * Set of static functions to perform various operations on {@code Strings}
 *
 */
public class CollectionUtils {

	/**
	 * Determine if a collection contains elements
	 * 
	 * @param col {@link Collection} to be examined.
	 * 
	 * @return {@code true} if the collection has elements; {@code false} if the collection is either null or has no elements.
	 */
	public static <T extends Collection<?>> boolean hasElements(T col) {
		return col != null && !col.isEmpty();
	}
	
	/**
	 * Determine if a collection of Integers contains elements, at least one of which is non zero
	 * 
	 * @param col {@link Collection<Integer>} to be examined.
	 * 
	 * @return {@code true} if the collection has elements at least one of which has a non-zero value; {@code false} if the collection is either null or has no elements.
	 */
	public static <T extends Collection<Integer>> boolean hasNonZeroElements(T col) {
		if  (col == null || col.isEmpty())
			return false;
		
		boolean bFound = false;
		for (Integer val: col) {
			if (val != null && val != 0)
			{
				bFound = true;
				break;
			}
		}
		
		return bFound;
	}
	
	/**
	 * Determine if a collection of Booleans contains elements, at least one of which is true
	 * 
	 * @param col {@link Collection<Boolean>} to be examined.
	 * 
	 * @return {@code true} if the collection has elements at least one of which has a value of {@code True}; {@code false} if the collection is either null or has no elements.
	 */
	public static <T extends Collection<Boolean>> boolean hasTrueElements(T col) {
		if  (col == null || col.isEmpty())
			return false;
		
		boolean bFound = false;
		for (Boolean val: col) {
			if (val != null && val)
			{
				bFound = true;
				break;
			}
		}
		
		return bFound;
	}
	
	/**
	 * Returns a {@link HashSet} populated with the elements of the passed in {@link Collection}
	 * 
	 * If the source collection is null or empty then an empty {@link HashSet} will be returned
	 * 
	 * @param source {@link Collection} to convert
	 * @return {@link HashSet} populated with the elements of {@code source} or empty if {@code source} is null.
	 */
	public static <T> HashSet<T> getHashSet(Collection<T> source) {
		return (source == null || source.isEmpty()) ? new HashSet<T>() : new HashSet<T>(source);
	}
	
	/**
	 * Returns a {@link HashSet} populated with the elements of the passed in {@link Collection}
	 * 
	 * If the source collection is null or empty then null will be returned
	 * 
	 * @param source {@link Collection} to convert
	 * @return {@link HashSet} populated with the elements of {@code source} or null if {@code source} is null.
	 */
	public static <T> HashSet<T> getHashSetOrNull(Collection<T> source) {
		return (source == null || source.isEmpty()) ? null : new HashSet<T>(source);
	}
	
	/**
	 * Returns an {@link ArrayList} populated with the elements of the passed in {@link Collection}
	 * 
	 * If the source collection is null or empty then an empty {@link ArrayList} will be returned
	 * 
	 * @param source {@link Collection} to convert
	 * @return {@link ArrayList} populated with the elements of {@code source} or empty if {@code source} is null.
	 */
	public static <T> ArrayList<T> getArrayList(Collection<T> source) {
		return (source == null || source.isEmpty()) ? new ArrayList<T>() : new ArrayList<T>(source);
	}
	
	/**
	 * Returns an {@link ArrayList} populated with the elements of the passed in {@link Collection}
	 * 
	 * If the source collection is null or empty then null will be returned
	 * 
	 * @param source {@link Collection} to convert
	 * @return {@link ArrayList} populated with the elements of {@code source} or null if {@code source} is null.
	 */
	public static <T> ArrayList<T> getArrayListOrNull(Collection<T> source) {
		return (source == null || source.isEmpty()) ? null : new ArrayList<T>(source);
	}
	
}
