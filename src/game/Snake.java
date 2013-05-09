package game;

public class Snake {

	interface extension {
		public void set_timeout(int timeout);

		public long get_timeout();

		public long star_time();

		public void set_score(int score);

		public int get_score();
	}

	public static void main(String[] args) {
		new GameThread();
	}
}
