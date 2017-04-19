package math;

import math.fft.real.RealDoubleFFT;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Chris Zhao on 2017/1/9.
 */
public class FFTTest {

    private static double delta = 0.001;

    @Test
    public void test() {
        int length = 7;
        RealDoubleFFT fft = new RealDoubleFFT(length);
        double[] results = new double[length];
        for(int i=0;i<length;i++) {
             results[i] = 2;
        }
        fft.ft(results);
        Assert.assertEquals(14, results[0], delta);

    }

    @Test
    public void testFFT() {

        int length = 7;
        RealDoubleFFT rdfft = new RealDoubleFFT(length);
        double [] input = new double[length];
        input[0] = 0.0;
        input[1] = 162.6345596729059;
        input[2] = 230.0;
        input[3] = 162.63455967290594;
        input[4] = 2.8166876380389125E-14;
        input[5] = -162.6345596729059;
        input[6] = -230.0;
//        input[7] = -162.63455967290597;

//        FastFourierTransformer fft = new FastFourierTransformer(DftNormalization.STANDARD);
//        Complex[] fftResult = fft.transform(input, TransformType.FORWARD);
//        for (Complex complex : fftResult) {
////            System.out.println(Math.sqrt(complex.getReal()*complex.getReal()+complex.getImaginary()*complex.getImaginary()));
//            System.out.println(complex.abs());
//        }
        System.out.println();
        rdfft.ft(input);
        for(int i=0;i<7;i++) {
            if(i==0) {
                System.out.println(input[i]);
            }
            else {
                System.out.println(Math.sqrt(input[i]*input[i]+input[i+1]*input[i+1]));
                i++;
            }
        }

    }

    @Test
    public void testNewFFT() {
        int length = 16;

        double [] input = new double[length];
        input[0] = 0.0;
        input[1] = 162.6345596729059;
        input[2] = 230.0;
        input[3] = 162.63455967290594;
        input[4] = 2.8166876380389125E-14;
        input[5] = -162.6345596729059;
        input[6] = -230.0;
        input[7] = -162.63455967290597;
        input[8] = 0;
        input[9] = 162.6345596729059;
        input[10] = 230.0;
        input[11] = 162.63455967290594;
        input[12] = 2.8166876380389125E-14;
        input[13] = -162.6345596729059;
        input[14] = -230.0;
        input[15] = -162.63455967290597;


        double[] result = FFT.ft(input);

        Assert.assertEquals(1840, result[2], delta);

    }

}
