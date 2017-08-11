package thread;

import java.util.HashMap;

public class ThreadRunner implements Runnable{
	public static HashMap<String, Integer> map = new HashMap<>();
	private static int taskCount = 0;
	private int countDown = 10000;
	private final int id = taskCount++;

	@Override
	public void run() {
		try {
			while(countDown-->0) {
				map.put(""+countDown, countDown);
				System.out.println(status());
			}

		} catch (Exception e) {
			System.out.println(e.toString());
		}

	}

	private String status() {
		return id + " -- "+ countDown;
	}
}
