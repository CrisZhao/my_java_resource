package multithread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;

import thread.ThreadRunner;

public class ThreadTest {
	@Test
	public void test() {
		ExecutorService exec = Executors.newFixedThreadPool(3);
		for (int i = 0; i < 5; i++) {
			exec.execute(new ThreadRunner() );
//			Thread t = new Thread(new ThreadRunner());
//			t.start();
		}
		exec.shutdown();
		System.out.println("running");
	}
	

}
