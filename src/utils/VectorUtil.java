package utils;

/**
 * Created by Chris Zhao on 2016/12/21.
 */
public class VectorUtil {

    public static double[] toStandardVector(double[] vector) {
        if (vector == null) {
            throw new UnsupportedOperationException("null input vector");
        }
        double vectorLength = norm(vector);
        int length = vector.length;
        double[] result = new double[length];
        for (int i = 0; i < length; i++) {
            result[i] = vector[i] / vectorLength;
        }
        return result;
    }


    public static double norm(double[] vector) {
        if (vector == null) {
            return 0;
        }
        double sqrSum = 0;
        for (double v : vector) {
            sqrSum += Math.pow(v, 2);
        }
        double length = Math.sqrt(sqrSum);

        return length;
    }

    public static double distance(double[] a, double[] b) {
        if (a == null && b == null) {
            return 0;
        }
        if (a == null) {
            return norm(b);
        }
        if (b == null) {
            return norm(a);
        }
        if (a.length != b.length) {
            throw new UnsupportedOperationException("a.norm = " + a.length + " b.norm= " + b.length);
        }
        double dis = Math.abs(norm(a) - norm(b));
        return dis;
    }

    /**
     * @param a
     * @param b
     * @return new data
     */
    public static double[] plus(double[] a, double[] b) {
        checkMatrixDimensions(a, b);
        double[] result = new double[a.length];
        for (int i = 0; i < a.length; i++) {
            result[i] = a[i] + b[i];
        }
        return result;
    }

    /**
     * @param a
     * @param b
     * @return
     */
    public static void plusInPlace(double[] a, double[] b) {
        checkMatrixDimensions(a, b);
        for (int i = 0; i < a.length; i++) {
            a[i] = a[i] + b[i];
        }
    }

    /**
     * @param a
     * @param b
     * @return
     */
    public static double[] minus(double[] a, double[] b) {
        checkMatrixDimensions(a, b);
        double[] result = new double[a.length];
        for (int i = 0; i < a.length; i++) {
            result[i] = a[i] - b[i];
        }
        return result;
    }

    private static void checkMatrixDimensions(double[] a, double[] b) {
        if (a == null || b == null) {
            throw new IllegalArgumentException("null input vector");
        }
        if (a.length != b.length) {
            throw new IllegalArgumentException("Matrix dimensions must agree.");
        }
    }

    public static void init(double[] a, double v) {
        if (a == null) {
            throw new IllegalArgumentException("null input vector");
        }
        for (int i = 0; i < a.length; i++) {
            a[i] = v;

        }
    }

    public static void init(double[] a) {
        init(a, 0);
    }

    public static void divide(double[] a, double v) {
        if (a == null) {
            throw new IllegalArgumentException("null input vector");
        }
        for (int i = 0; i < a.length; i++) {
            a[i] /= v;
        }
    }

    public static void filter(double[] data, double[] newData, double factor) {
        checkMatrixDimensions(data, newData);
        for (int i = 0; i < data.length; i++) {
            data[i] = (1 - factor) * data[i] + factor * newData[i];
        }
    }

    public static double maxAxisAbsDiff(double[] a, double[] b) {
        checkMatrixDimensions(a, b);
        double max = 0;
        for (int i = 0; i < a.length; i++) {
            double v = Math.abs(a[i]-b[i]);
            if(max<v) {
                max = v;
            }
        }
        return max;

    }


}
