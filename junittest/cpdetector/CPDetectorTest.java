package cpdetector;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import org.junit.Test;

import com.google.common.collect.Lists;

public class CPDetectorTest {
	private boolean startWithIngoreCase(String src, String prifix) {
		if (prifix == null) {
			return true;
		}
		if (prifix.length() > src.length() || src == null) {
			return false;
		}
		return src.substring(0, prifix.length()).equalsIgnoreCase(prifix);
	}
	@Test
	public void test1(){
		startWithIngoreCase(null, "1");
	}

	@Test
	public void test() {
		List<String> filepaths = Lists.newArrayList("d:/test/222.csv",
				"d:/test/test.txt");
		for (String path : filepaths) {
			testCharset(path);
		}
		Charset ca = null;
		try {
			ca = CPDetector
					.getInstance()
					.getDetectorProxy()
					.detectCodepage(
							new BufferedInputStream(new FileInputStream(
									filepaths.iterator().next())), 25);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(ca);
	}

	private void testCharset(String filepath) {
		Charset charset = CPDetector.getInstance().getCharset(
				new File(filepath));
		System.out.println(charset);
	}

}
