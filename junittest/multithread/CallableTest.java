package multithread;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import org.junit.Test;

public class CallableTest {
	@Test
	public void test() throws InterruptedException, ExecutionException {
		Callable<Integer> callable = new Callable<Integer>() {
            public Integer call() throws Exception {
                System.out.println("callable executed.");
                return new Random().nextInt(100);
            }
        };
         
        FutureTask<Integer> future = new FutureTask<Integer>(callable);
        new Thread(future).start();
        System.out.println("do somethings here");
         
        System.out.println(future.get());
	}

}
