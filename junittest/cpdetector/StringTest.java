package cpdetector;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import com.google.common.collect.Lists;

import testclass.ParentClass;
import testclass.SubClass;

public class StringTest {
	@Test
	public void testList2String() {
		List<String> list = Lists.newArrayList("a", "b");
		System.out.println(list.toString());
		
	}

	@Test
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