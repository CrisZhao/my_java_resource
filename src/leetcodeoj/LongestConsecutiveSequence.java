package leetcodeoj;

import org.junit.Test;

import java.util.HashSet;
import java.util.Iterator;

/**
 * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
 * <p/>
 * For example,
 * Given [100, 4, 200, 1, 3, 2],
 * The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.
 * <p/>
 * Your algorithm should run in O(n) complexity.
 */
public class LongestConsecutiveSequence {
    public int longestConsecutive(int[] num) {
        HashSet<Integer> set = new HashSet<Integer>();
        int maxLength = 0;
        for (int i : num) {
            set.add(i);
        }
        while (!set.isEmpty()) {
            Iterator<Integer> iterator = set.iterator();
            int value = iterator.next();
            iterator.remove();
            int length = 1;
            int bound = value - 1;
            while (set.contains(bound)) {
                set.remove(bound--);
                length++;
            }
            bound = value + 1;
            while (set.contains(bound)) {
                set.remove(bound++);
                length++;
            }
            if (length > maxLength) {
                maxLength = length;
            }
        }

        return maxLength;
    }

    @Test
    public void test() {
        int[] num = {100,4,200,1,3,2};
        System.out.println(longestConsecutive(num));
    }
}
