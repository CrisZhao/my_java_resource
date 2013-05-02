package iotest;

import junit.framework.TestCase;

import org.junit.ClassRule;
import org.junit.rules.ExternalResource;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ RuleTest.class })
public class TestSuiteBeforeTests {
	
	

public static void oneTimeSetUp() {  
    // one-time initialization code  
}  

public static void oneTimeTearDown() {  
    // one-time cleanup code  
}  
	private static int bCount = 0;
    private static int aCount = 0;

    @ClassRule
    public static ExternalResource testRule = new ExternalResource(){
    	
            @Override
            protected void before() throws Throwable{
            	System.out.println("suitbefore");
                System.err.println( "before test class: " + ++bCount );
                sss = "asdf";
            };

            @Override
            protected void after(){
            	System.out.println("suiteafter");
                System.err.println( "after test class: " + ++aCount );
            };
        };


    public static String sss;
}
