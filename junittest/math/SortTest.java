package math;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class SortTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}


	private void print(int[] datalist) {
		for (int i : datalist) {
			System.out.print(i + " ");
		}
	}

	@Test
	public void testSort() {
		int[] original = new int[] { 6, 1, 2, 5 };
		Sort.getInstance().sort(original);
		print(original);
		print(Sort.getInstance().getOrder());

	}

}
