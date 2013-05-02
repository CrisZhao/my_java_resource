package cpdetector;

import java.nio.charset.Charset;

import org.junit.Test;

import testclass.ParentClass;
import testclass.SubClass;

public class StringTest {

	@Test
	public void test() {
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