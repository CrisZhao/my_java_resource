package leetcodeoj;

import org.junit.Test;

/**
 * Given an input string, reverse the string word by word.
 * For example,
 * Given s = "the sky is blue",
 * return "blue is sky the".
 *
 * Clarification:
 * What constitutes a word?
 * A sequence of non-space characters constitutes a word.
 * Could the input string contain leading or trailing spaces?
 * Yes. However, your reversed string should not contain leading or trailing spaces.
 * How about multiple spaces between two words?
 * Reduce them to a single space in the reversed string.
 */
public class ReverseWordsInString {
    public String reverseWords(String s) {

        String[] splitStr = s.trim().split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (String str : splitStr) {
            if(!str.isEmpty()) {
              sb.insert(0," ").insert(0,str);
            }
        }

        return sb.toString().trim();
    }

    @Test
    public void test() {
        String s = " the sky   is  blue ";
        String s1 = " a b c d  e   ";
        System.out.println(reverseWords(s1));
    }
}
