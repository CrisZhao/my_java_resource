package multithread;

import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;

import thread.ThreadRunner;

public class ThreadTest {
	public static HashMap<String, Integer> map = new HashMap<>();
	@Test
	public void test() throws  Exception{
		ExecutorService exec = Executors.newFixedThreadPool(5);
		for (int i = 0; i < 5; i++) {
			exec.execute(new ThreadRunner() );
//			Thread t = new Thread(new ThreadRunner());
//			t.start();
		}
//		exec.shutdown();
		System.out.println("running");
	}
	

}
