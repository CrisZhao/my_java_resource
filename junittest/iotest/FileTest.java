package iotest;

import java.io.File;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileTest {
	Logger log = LoggerFactory.getLogger(FileTest.class);
	@Test
	public void test() {
		File path = new File(".");
		for (String ele : path.list()) {
			
			log.info("folder names: {}", ele);
		}
	}

}
