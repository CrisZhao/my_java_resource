package multithread;

import thread.Joiner;
import thread.Sleeper;

public class Joining {
	public static void main(String[] args) {
		Sleeper sleeper = new Sleeper("sleepy", 1500), grumpy = new Sleeper(
				"grumpy", 1500);
		Joiner joiner = new Joiner("dopey", sleeper), doc = new Joiner("doc",
				grumpy);
		grumpy.interrupt();

	}

}
