package junit;

 
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
   
@RunWith(MyTestRunner.class)  
public class MyTest {  
   
    @BeforeClass  
    public static void beforeClass() {  
        System.out.println("##  execute beforeClass");  
    }  
   
    @Before  
    public void before() {  
        System.out.println("##  execute before");  
    }  
       
    @Test  
    public void test18() {  
        System.out.println("##  execute should_return_something_if_age_equals_18");  
    }  
       
    @After  
    public void after() {  
        System.out.println("##  execute after");  
    }  
   
    @AfterClass  
    public static void afterClass() {  
        System.out.println("##  execute afterClass");  
    }  
} 