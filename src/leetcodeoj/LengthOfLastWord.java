package leetcodeoj;

import org.junit.Test;

/**
 * Given a string s consists of upper/lower-case alphabets and empty space characters ' ', return the length of last word in the string.

 * If the last word does not exist, return 0.

 * Note: A word is defined as a character sequence consists of non-space characters only.

 * For example,
 * Given s = "Hello World",
 * return 5.
 */
public class LengthOfLastWord {
    public int lengthOfLastWord(String s) {
        String str = s.trim();
        int index = str.lastIndexOf(" ");
        return str.substring(index+1).length();
    }

    @Test
    public void test() {
        String s = "Hello World";
        System.out.println(lengthOfLastWord(s));
    }
}
