package iotest;

import java.io.File;

import org.junit.Test;

public class FileTest {
	@Test
	public void test() {
		File path = new File(".");
		for (String ele : path.list()) {
			
			System.out.println(ele);
		}
	}

}
