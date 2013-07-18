package utils;

import java.util.Map;
import java.util.Map.Entry;

public class PrintUtils {
	public <K, V> String printMap(Map<K, V> map) {
		StringBuilder sb = new StringBuilder();
		for (Entry<K, V> entry : map.entrySet()) {
			sb.append(entry.getKey().toString()).append(" : ").append(entry.getValue());
		}
		return sb.toString();
	}

}
