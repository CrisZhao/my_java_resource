package multithread;

import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

import org.junit.Test;

import com.google.common.collect.Lists;

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
        ExecutorService exec = Executors.newCachedThreadPool();
        Future<Integer> futurea = exec.submit(callable);
        System.out.println(futurea.get());
         
        System.out.println(future.get());
        exec.shutdown();
	}
	
	@Test
	public void testInvokeAll() throws InterruptedException, ExecutionException {
		List<Callable<String>> call = Lists.newArrayList();
		
		for (int i = 0; i < 5; i++) {
			final int id = i;
			call.add(new Callable<String>() {
				
				@Override
				public String call() throws Exception {
					return "taskId" + id;
				}
			});
		}
		
		ExecutorService exec = Executors.newCachedThreadPool();
		List<Future<String>> futures = exec.invokeAll(call);
		for (Future<String> future : futures) {
			System.out.println(future.get());
		}
		exec.shutdown();
	}

}
