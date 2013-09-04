package utils;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;

import com.google.common.collect.Lists;

public class PrintUtils {

	public interface Printer<T> {
		public String print(T object);

		public String getSplitor();
	}
	
	@Test
	public void test() {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("11", 1);
		map.put("12", 2);
		map.put("13", 3);
		map.put("14", 4);
		System.out.println(printMap(map));
		System.out.println(printMapInLine(map));
		Collection<String> c = Lists.newArrayList("test1", "test2");
		System.out.println(printCollection(c));
	}

	public static <E> String printCollection(Collection<E> collection) {
		return printCollection(collection, new Printer<E>() {

			@Override
			public String print(E object) {
				return object.toString();
			}

			@Override
			public String getSplitor() {
				return ",";
			}
		});
	}



	public static <K, V> String printMapInLine(Map<K, V> map) {
		return printCollection(map.keySet()) + "\n"
				+ printCollection(map.values());
	}

	public static <K, V> String printMapInLine(Map<K, V> map,
			Printer<? super K> keyPrinter, Printer<? super V> valuePrinter) {
		return printCollection(map.keySet(), keyPrinter) + "\n"
				+ printCollection(map.values(), valuePrinter);
	}

	public static <K, V> String printMap(Map<K, V> map) {
		return printCollection(map.entrySet(),
				new Printer<Entry<? super K, ? super V>>() {

					@Override
					public String print(Entry<? super K, ? super V> entry) {
						return String.format("%s : %s ", entry.getKey()
								.toString(), entry.getValue().toString());
					}

					@Override
					public String getSplitor() {
						return "\n";
					}
				});
	}

	public static <E> String printCollection(Collection<E> collection,
			Printer<? super E> printor) {

		Iterator<E> iterator = collection.iterator();
		if (!iterator.hasNext()) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for (;;) {
			sb.append(printor.print(iterator.next()));
			if (!iterator.hasNext()) {
				return sb.toString();
			}
			sb.append(printor.getSplitor());
		}
	}

}
