package utils;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;

import com.google.common.collect.Lists;

/**
 * sort map by value
 * 
 * @author Cris Zhao
 * 
 */
public class MapSortor {

	public static <K, V> LinkedHashMap<K, V> sort(Map<K, V> map,
			final Comparator<Entry<K, V>> comparator) {
		List<Entry<K, V>> list = Lists.newArrayList(map.entrySet());

		Collections.sort(list, comparator);
		LinkedHashMap<K, V> sortedMap = new LinkedHashMap<K, V>();
		for (Entry<K, V> entry : list) {
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		return sortedMap;
	}

	public static <K, V> LinkedHashMap<K, V> sort(Map<K, V> map) {
		List<Entry<K, V>> list = Lists.newArrayList(map.entrySet());

		Collections.sort(list, new Comparator<Entry<K, V>>() {

			@SuppressWarnings("unchecked")
			@Override
			public int compare(Entry<K, V> o1, Entry<K, V> o2) {
				return ((Comparable<V>) o1.getValue()).compareTo(o2.getValue());
			}
		});
		LinkedHashMap<K, V> sortedMap = new LinkedHashMap<K, V>();
		for (Entry<K, V> entry : list) {
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		return sortedMap;
	}

	@Test
	public void testSort() {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("ss3", 24);
		map.put("first", 1);
		map.put("ss2", 23);
		map.put("ss", 2);
		LinkedHashMap<String, Integer> sortedMap = sort(map);

		for (Entry<String, Integer> entry : sortedMap.entrySet()) {

			System.out.println(entry.getKey() + " : " + entry.getValue());
		}

	}

	@Test
	public void testSortComparator() {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("ss3", 24);
		map.put("first", 1);
		map.put("ss2", 23);
		map.put("ss", 2);
		LinkedHashMap<String, Integer> sortedMap = sort(map,
				new Comparator<Entry<String, Integer>>() {

					@Override
					public int compare(Entry<String, Integer> o1,
							Entry<String, Integer> o2) {
						return o1.getValue().compareTo(o2.getValue());
					}
				});
		for (Entry<String, Integer> entry : sortedMap.entrySet()) {

			System.out.println(entry.getKey() + " : " + entry.getValue());
		}

	}

}
