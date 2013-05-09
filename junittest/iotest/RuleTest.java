package iotest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class RuleTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("beforeclass");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("afterclass");
	}

	@Before
	public void setUp() throws Exception {
		System.out.println("before");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("after");
	}

	 @Test
	    public void asdf1(){
	        assertNotNull( "A value should've been set by a rule.", TestSuiteBeforeTests.sss );
	    }

	    @Test
	    public void asdf2(){
	        assertEquals( "This value should be set by the rule.", "asdf", TestSuiteBeforeTests.sss );
	    }

}
