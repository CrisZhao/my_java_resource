package cpdetector;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Ignore;
import org.junit.Test;

import testclass.ParentClass;
import testclass.SubClass;

import com.google.common.collect.Lists;

public class StringTest {
	@Test
	@Ignore
	public void testList2String() {
		List<String> list = Lists.newArrayList("a", "b");
		System.out.println(list.toString());
		
	}
	
	@Test
	public void testEncoding() {
		String s = "abdbsa";
		System.out.println(perfect(s));
		
	}
	public static int perfect(String s) {
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		for (char c : s.toCharArray()) {
			if(!map.containsKey(c)) {
				map.put(c, 0);
			}
			map.put(c, map.get(c)+1);
		}
		List<Entry<Character, Integer>> entries = new ArrayList<Map.Entry<Character,Integer>>(map.entrySet());
		Collections.sort(entries, new Comparator<Entry<Character, Integer>>() {

			@Override
			public int compare(Entry<Character, Integer> o1,
					Entry<Character, Integer> o2) {
				return o2.getValue().compareTo(o1.getValue());
			}
		});
		int total =0;
		int value=26;
		for (Entry<Character, Integer> entry : entries) {
			total+=entry.getValue()*value;
			value--;
		}
		return total;
	}

	@Test
	@Ignore
	public void test() {
		StringBuilder sb = new StringBuilder();
		sb.append("wulllalallaand ");
		sb.delete(sb.indexOf("and"), sb.length());
		System.out.println(sb.toString());
		System.out.println("2+2=" + 2 + 2);
		ParentClass p = new SubClass();
		p.method();
		stringCheese();
	}

	private void stringCheese() {
		byte[] bytes = new byte[256];
		for (int i = 0; i < 256; i++) {
			bytes[i] = (byte)i;
		}
		System.out.println(Charset.defaultCharset());
//		String str = new String(bytes, Charset.forName("iso-8859-1"));
		String str = new String(bytes);
		for (int i = 0, n = str.length(); i < n; i++) {
			System.out.print((int)str.charAt(i)+" ");
			
		}
		
	}
}