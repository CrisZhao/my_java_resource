package math;

import math.calculator.MyCalculator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class MyCalculatorTest {

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

	@Test
	public void test() {
		double result = new MyCalculator().cal("2+3*5-(6-3/2)");
		Assert.assertEquals(12.5, result, 0.0001);
	}

	@Test
	public void testExp() {
		String exp = "(10.5 + 15) * 3.2 - 20 * 6 /5 - (8 + 14*(2.5- 1))*2 + 11*(12 - 11)*5";
		MyCalculator cal = new MyCalculator();
		Assert.assertEquals(54.6, cal.cal(exp), 0.0001);
	}

}
