package leetcodeoj;

import org.junit.Test;

import java.util.Stack;

/**
 * User: Cris Zhao
 * Date: 14-7-1
 * Time: 下午6:23
 */
public class ValidParentheses {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<Character>();
        for (char c : s.toCharArray()) {
            if (c == '{' || c == '[' || c == '(') {
                stack.add(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                char r = stack.pop();
                if (c == '}' && r != '{') {
                    return false;
                } else if (c == ']' && r != '[') {
                    return false;
                } else if (c == ')' && r != '(') {
                    return false;
                }
            }
        }
        if (!stack.isEmpty()) {
            return false;
        }
        return true;
    }

    @Test
    public void test() {
        String s = "([)]";
        System.out.println(isValid(s));
    }
}
