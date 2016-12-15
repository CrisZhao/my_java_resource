package utils;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomUtils {
    private static Random rand = new Random(47);

    public static <T extends Enum<T>> T random(Class<T> clazz) {
        return random(clazz.getEnumConstants());
    }

    public static <T> T random(T[] values) {
        return values[rand.nextInt(values.length)];
    }

    public static double nextDouble() {
        return rand.nextDouble();
    }

    /**
     * [0,n)
     *
     * @param n
     * @return
     */
    public static int nextInt(int n) {
        return rand.nextInt(n);
    }


    public static <T> T randomOne(List<T> values) {
        if (isEmpty(values)) {
            return null;
        }
        int index = nextInt(values.size());
        return values.get(index);
    }

    public static <T> List<T> randomSome(List<T> values, int n) {
        if (isEmpty(values)) {
            return Lists.newArrayList();
        }

        int size = values.size();
        List<Integer> idxList = new ArrayList<Integer>(size);
        for (int i = 0; i < size; i++) {
            idxList.add(i);
        }

        List<T> result = new ArrayList<T>(n);
        int rand;
        for (int i = 0; i < n; i++) {
            if (idxList.isEmpty()) {
                break;
            }
            size = idxList.size();
            rand = nextInt(size);
            result.add(values.get(idxList.get(rand)));
            idxList.remove(rand);

        }
        idxList.clear();
        return result;

    }

    /**
     * select from n bucket
     *
     * @param values
     * @param n
     * @param <T>
     * @return
     */
    public static <T> List<T> randomSome2(List<T> values, int n) {

        if (isEmpty(values)) {
            return Lists.newArrayList();
        }

        int size = values.size();
        int scale = Math.max(1, size / n);
        List<T> result = Lists.newArrayList();

        for (int i = 0; i < n; i++) {
            int index = scale * i + nextInt(scale);
            if (index > size) {
                break;
            }
            result.add(values.get(index));
        }
        return result;
    }


    public static <T> T random(T[] array, int start, int length) {
        if (start + length > array.length) {
            throw new IndexOutOfBoundsException();
        }
        return array[nextInt(length) + start];
    }

    public static <T> T weightedRandom(T[] values, double[] weights) {
        return values[weightedRandom(weights)];
    }

    public static <T> T weightedRandom(T[] values, int[] weights) {
        return values[weightedRandom(weights)];
    }

    public static int weightedRandom(int[] weights) {

        int total = 0;
        for (int weight : weights) {
            total += weight;
        }
        int r = nextInt(total);
        int weight = 0;
        for (int i = 0; i < weights.length; i++) {
            weight += weights[i];
            if (weight > r) {
                return i;
            }
        }
        return weights.length - 1;
    }

    public static int weightedRandom(double[] weights) {
        double r = nextDouble();
        double weight = 0;
        for (int i = 0; i < weights.length; i++) {
            weight += weights[i];
            if (weight > r) {
                return i;
            }
        }
        return weights.length - 1;
    }


    private static <T> boolean isEmpty(List<T> list) {
        if (null == list) {
            return true;
        }
        if (list.isEmpty()) {
            return true;
        }
        return false;
    }


}
