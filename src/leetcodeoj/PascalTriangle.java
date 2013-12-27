package leetcodeoj;

import java.util.ArrayList;

import org.junit.Test;

/**
 * Given numRows, generate the first numRows of Pascal's triangle.
 * 
 * For example, given numRows = 5, Return
 * 
 * [ 
 *        [1],
 *       [1,1], 
 *      [1,2,1], 
 *     [1,3,3,1], 
 *    [1,4,6,4,1] 
 *  ]
 * 
 * 
 */
public class PascalTriangle {

	public ArrayList<ArrayList<Integer>> generate(int numRows) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < numRows; i++) {
			ArrayList<Integer> line = new ArrayList<Integer>();

			for (int j = 0; j <= i; j++) {
				if (j == 0 || j == i) {
					line.add(1);
					continue;
				}
				line.add(result.get(i - 1).get(j - 1)
						+ result.get(i - 1).get(j));

			}
			result.add(line);
		}
		return result;
	}

	@Test
	public void test() {
		ArrayList<ArrayList<Integer>> result = generate(1);
		System.out.println(result);
	}
}
