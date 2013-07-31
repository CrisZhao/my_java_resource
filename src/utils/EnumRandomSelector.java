package utils;

import java.util.Random;

public class EnumRandomSelector {
	private static Random rand = new Random(47);

	public static <T extends Enum<T>> T random(Class<T> clazz) {
		return random(clazz.getEnumConstants());
	}

	public static <T> T random(T[] values) {
		return values[rand.nextInt(values.length)];
	}

}
