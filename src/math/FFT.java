package math;


import math.fft.real.RealDoubleFFT;

/**
 * Created by Chris Zhao on 2017/1/10.
 */
public class FFT {
    /**
     *
     * @param inputData
     * @return result first is DC component, data[i] is the kth coefficient value
     */
    public static double[] ft(double[] inputData) {
        double[] data = new double[inputData.length];
        System.arraycopy(inputData, 0, data, 0, inputData.length);
        RealDoubleFFT fft = new RealDoubleFFT(inputData.length);
        fft.ft(data);
        int resultLength = (data.length+2)/2;
        double[] results = new double[resultLength];
        results[0] = data[0];
        if(data.length%2==0) {
            results[resultLength-1] = data[data.length - 1];
        }
        int m = (data.length+1)/2;

        for (int i = 1; i < m; i ++) {
            int real = 2*i;
            int imaginary = real-1;

            results[i] = Math.sqrt(data[real] * data[real] + data[imaginary] * data[imaginary]);
        }
        return results;
    }
}
