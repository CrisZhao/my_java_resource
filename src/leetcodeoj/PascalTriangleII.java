package leetcodeoj;

import java.util.ArrayList;

import org.junit.Test;

/**
 * Given an index k, return the kth row of the Pascal's triangle.
 * 
 * For example, given k = 3, Return [1,3,3,1].
 * 
 * Note: Could you optimize your algorithm to use only O(k) extra space?
 * 
 * 
 */
public class PascalTriangleII {

	public ArrayList<Integer> getRow(int rowIndex) {
		ArrayList<Integer> line = new ArrayList<Integer>();
		for (int i = 0; i < rowIndex+2; i++) {
			line.add(0);
		}
		line.set(1, 1);
		for (int i = 0; i <= rowIndex; i++) {

			for (int j = i+1; j >0; j--) {
				line.set(j, line.get(j-1)+line.get(j));
			}
			
		}
		line.remove(0);
		return line;

	}

	// overflow for input 30
	public ArrayList<Integer> getRow1(int rowIndex) {
		ArrayList<Integer> row = new ArrayList<Integer>();
		if (rowIndex < 0) {
			return row;
		}
		row.add(1);
		for (int i = 1; i <= rowIndex; i++) {
			row.add(row.get(i - 1) * (rowIndex - i + 1) / i);
		}
		return row;

	}

	@Test
	public void test() {
		ArrayList<Integer> result = getRow(30);
		System.out.println(result);
	}
}
