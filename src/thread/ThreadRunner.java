package thread;

public class ThreadRunner implements Runnable{
	private static int taskCount = 0;
	private int countDown = 10;
	private final int id = taskCount++;

	@Override
	public void run() {
		while(countDown-->0) {
			System.out.println(status());
		}
		
	}

	private String status() {
		return id + " -- "+ countDown;
	}
}
