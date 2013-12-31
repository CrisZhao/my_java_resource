package leetcodeoj;

import java.util.ArrayList;

import org.junit.Test;

public class GenerateParenthesis {
	public ArrayList<String> generateParenthesis(int n) {

		ArrayList<String> result = new ArrayList<String>();
//		if (n == 0) {
//			return result;
//		}
//		result.add("()");
//		for (int i = 0; i < n - 1; i++) {
//			ArrayList<String> line = new ArrayList<String>(result);
//			result.clear();
//			for (int j = 0; j < line.size(); j++) {
//				String term = line.get(j);
//				String in = "(" + term + ")";
//				String pre = "()" + term;
//				String suffix = term + "()";
//				result.add(in);
//				result.add(pre);
//				if (j != line.size() - 1) {
//					result.add(suffix);
//				}
//			}
//		}
		return result;
	}

	@Test
	public void test() {
		System.out.println(generateParenthesis(4));
	}
}
