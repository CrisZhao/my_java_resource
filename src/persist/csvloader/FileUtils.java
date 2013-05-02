package persist.csvloader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class FileUtils {
	private static FileUtils instance = new FileUtils();

	private FileUtils() {
	}

	public static FileUtils getInstance() {
		return instance;
	}
/**
 * works for small source file 
 * @param sourceFileName
 * @return
 */
	public List<String[]> getCSVContent(String sourceFileName) {
		String entryfileName = sourceFileName;
		BufferedReader reader = null;
		List<String[]> contents = null;
		try {
			reader = new BufferedReader(new FileReader(new File(entryfileName)));
			contents = readLineContent(reader);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(reader);
		}
		return contents;
	}

	private List<String[]> readLineContent(BufferedReader reader)
			throws IOException {
		List<String[]> contents = Lists.newArrayList();
		String line = null;
		while ((line = reader.readLine()) != null) {
			String trimedLine = line.trim();
			if (trimedLine.isEmpty() || trimedLine.startsWith("#")) {
				continue;
			}
			contents.add(trimedLine.split(","));
		}
		return contents;
	}
	
	public List<Map<String, String>> convertCSVFile2Map(List<String[]> contents) {
		Map<String, String> map = Maps.newHashMap();
		if(contents.size()<2) {
			return map;
		}
		String[] titles = contents.get(0);
		for (int i = 1; i < contents.size(); i++) {
			if(contents.get(i).length==titles.length) {
				throw new RuntimeException("data doesn't match titles." + " line: "
						+ reader.currentLine() + " line number:"
						+ reader.getLineNum());
			}
		}
	}
	

}
