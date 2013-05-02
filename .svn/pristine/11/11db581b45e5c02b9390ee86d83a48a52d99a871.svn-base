package persist.csvloader;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;

public class BaseParser{

	protected boolean checkStartLine(NavigableReader reader, String startLine)
			throws IOException {
		List<String> startline = CSVUtils.parseLine(reader.readLine());
		if (!startLine.equals(startline.get(0))) {
			throw new RuntimeException("Missing start line." + startline.get(0)
					+ "line number:" + reader.getLineNum());
		}
		return true;
	}

	protected List<String> checkHeader(NavigableReader reader)
			throws IOException {
		String titlesStr = reader.readLine();
		List<String> titles = CSVUtils.parseLine(titlesStr);
		if (titles.get(0).isEmpty()) {
			throw new RuntimeException("Missing header." + titlesStr
					+ " line number:" + reader.getLineNum());
		}
		return titles;
	}

	protected String getValue(List<String> titles, NavigableReader reader,
			String attr) {
		List<String> value = reader.currentLineList();
		int index = titles.indexOf(attr);

		if (index < 0) {
			throw new RuntimeException("No souch attriubte : " + attr
					+ " line: " + reader.currentLine() + " line number:"
					+ reader.getLineNum());
		}
		if (index >= value.size()) {
			return "";
		}

		return value.get(index);

	}

	protected Map<String, String> getValues(List<String> titles,
			NavigableReader reader) {
		Map<String, String> map = Maps.newHashMap();
		List<String> value = reader.currentLineList();
		if (value.size() < titles.size()) {
			throw new RuntimeException("data doesn't match titles." + " line: "
					+ reader.currentLine() + " line number:"
					+ reader.getLineNum());
		}
		for (int i = 0; i < titles.size(); i++) {
			map.put(titles.get(i), value.get(i));
		}
		return map;
	}

}
