package persist.csvloader;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;

public class CSVUtils {

	private static final Pattern CSVSPLITPTN = Pattern.compile(",");

	private static boolean isQuotesEven(String ss) {
		int cnt = 0;
		for (char c : ss.toCharArray()) {
			cnt += ((c == '"') ? 1 : 0);
		}
		return cnt % 2 == 0;
	}

	public static List<String> parseLine(String linein) {
		List<String> mainList = new ArrayList<String>();
		List<String> subList = new ArrayList<String>();
		for (String curStr : CSVSPLITPTN.split(linein)) {
			if (isQuotesEven(curStr)) {
				((subList.isEmpty()) ? mainList : subList).add(curStr);
			} else if (!subList.isEmpty()) {
				subList.add(curStr);
				mainList.add(mergeToString(subList, ","));
				subList.clear();
			} else {
				subList.add(curStr);
			}
		}

		mainList.addAll(subList);
		return quote(trim(mainList));
	}

	private static <T> String mergeToString(Collection<T> lst, String separator) {
		StringBuilder sb = new StringBuilder();
		for (Object str : lst) {
			sb.append(str).append(separator);
		}
		sb.delete(sb.length() - separator.length(), sb.length());
		return sb.toString();
	}

	private static List<String> trim(List<String> in) {
		int i = in.size() - 1;
		for (int idx = i; idx >= 0; --idx) {
			if ((in.get(idx)).isEmpty()) {
				i = idx;
				continue;
			}
			return in.subList(0, i + 1);
		}
		return in;
	}

	private static List<String> quote(List<String> in) {
		for (int i = 0; i < in.size(); i++) {
			String str = in.get(i);
			if (str.length() > 2 && str.startsWith("\"") && str.endsWith("\"")) {
				str = str.substring(1, str.length() - 1);
				if (str.indexOf("\"\"") != -1) {
					str = str.replaceAll("\"\"", "\"");
				}
				in.set(i, str);
			}
		}
		return in;
	}

}
