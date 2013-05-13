package thread;

public class Joiner extends Thread {
	private Sleeper sleeper;
	public Joiner (String name, Sleeper sleeper) {
		super(name);
		this.sleeper = sleeper;
		start();
	}
	public void run(){
		try {
			sleeper.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println("interrupted");
		}
		System.out.println(getName()+" join completed");
	}

}
