package pongo;

public class WordGame {

	private static char[] words;
	private static int length;
	private static int[] states;
	private static int[] positionHolder;
	private static int[] permutation;

	public static int who(String word) {
		words = word.toCharArray();
		length = word.length();
		states = new int[1 << length];
		positionHolder = new int[length];
		permutation = new int[1 << length];
		init(states, -1);
		init(permutation, -1);
		for (int i = 0; i < positionHolder.length; i++) {
			positionHolder[i] = 1 << i;
		}
		return checkState((1 << length) - 1);
	}

	private static int checkState(int state) {
		if (states[state] != -1) {
			return states[state];
		}

		int result = 0;
		int nextState;

		for (int i = 0; i < length; i++) {
			if ((state & positionHolder[i]) == 0) {
				continue;
			}

			nextState = state & ~positionHolder[i];
			if (checkPermutation(nextState) == 1 || checkState(nextState) == 0) {
				result = 1;
				break;
			}

		}
		states[state] = result;

		return result;
	}

	private static int checkPermutation(int state) {
		if (permutation[state] != -1) {
			return permutation[state];
		}
		char previous = 0;
		char now;
		int result = 1;
		for (int i = 0; i < length; i++) {
			if ((state & positionHolder[i]) == 0) {
				continue;
			}
			now = words[i];
			if (now <= previous) {
				result = 0;
				break;
			}
			previous = now;

		}
		permutation[state] = result;

		return result;

	}

	private static void init(int[] array, int initialValue) {
		for (int i = 0; i < array.length; i++) {
			array[i] = initialValue;
		}
	}

	public static void main(String[] args) {

		System.out.println(who("ajrtgfgtytyuihc"));
	}

}
