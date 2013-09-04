package pongo;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * User: Cris Zhao Date: 13-8-26 Time: 下午4:03
 */
public class MinLength {

	public static int minLength(String s) {
		if (s.length() == 1 || s.isEmpty()) {
			return s.length();
		}
		LinkedList<Character> characters = new LinkedList<Character>();
		for (char c : s.toCharArray()) {
			characters.add(c);
		}

		int a = getLength(characters);
		return a;

	}

	public static int getLength(LinkedList<Character> characters) {
		LinkedList<Character> temp = new LinkedList<Character>(characters);
		int length = 0;
		do {
			length = temp.size();
			temp = buildSequence(temp);
		} while (length != temp.size());
		return length;
	}

	public static LinkedList<Character> buildSequence(
			LinkedList<Character> characters) {
		Iterator<Character> itr = characters.iterator();
		LinkedList<Character> result = new LinkedList<Character>();
		while (itr.hasNext()) {
			char c = itr.next();

			// init first
			if (result.isEmpty()) {
				result.push(c);
				continue;
			}

			// a==b
			if (c == result.peek()) {
				result.push(c);
				continue;
			}

			result.push(getThirdChar(c, result.pop()));
		}
		return result;
	}

	public static char getThirdChar(char first, char second) {
		int sum = first + second;
		if (sum == 'a' + 'b')
			return 'c';
		if (sum == 'a' + 'c')
			return 'b';
		return 'a';
	}

	public static void main(String[] args) {
		String s = "caaabbaaaaccbbcaacbabacccaccbabbbbbbacbbabacccaabcbaacbcaacababacabaaabbccbcbcaaccbccccaccaaacbaccbbbaaacbcbaabaacaabaabcbaaabbbcccaacbaabbccaacaacabbbcaccaccccbbaabcacaccaacaabbaccabcacaabacaccabcbba";
		System.out.println(minLength(s));
	}
}
