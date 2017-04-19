package math.features;

import math.feature.Features;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by Chris Zhao on 2017/1/11.
 */
public class FeaturesTest {
    private static final double delta = 0.0000001;

    private static Features features;
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        double [] input = new double[16];
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
        features = new Features(input);
        features.calculate();

    }

    @Test
    public void getFftResult() throws Exception {
        double[] fftResult = features.getFftResult();
        Assert.assertEquals(9,fftResult.length);
        Assert.assertEquals(1840.0, fftResult[2], delta);
    }

    @Test
    public void getSum() throws Exception {
        Assert.assertEquals(0, features.getSum(),delta);
    }

    @Test
    public void getSumSquare() throws Exception {
        Assert.assertEquals(423200, features.getSumSquare(),delta);
    }

    @Test
    public void getMax() throws Exception {
        Assert.assertEquals(230, features.getMax(),delta);
    }

    @Test
    public void getMin() throws Exception {
        Assert.assertEquals(-230, features.getMin(),delta);
    }

    @Test
    public void getMean() throws Exception {
        Assert.assertEquals(0, features.getMean(),delta);
    }

    @Test
    public void getVariance() throws Exception {
        Assert.assertEquals(423200, features.getStandardDeviation(),delta);
    }

    @Test
    public void getRange() throws Exception {
        Assert.assertEquals(460, features.getRange(),delta);
    }

    @Test
    public void getInterQuartileRange() throws Exception {
        double [] input = {7, 15, 36, 39, 40, 41};
        Features iqrFeatures = new Features(input);
        iqrFeatures.calculateRange();
        Assert.assertEquals(27.25, iqrFeatures.getInterQuartileRange(),delta);
    }

//    @Test
//    public void getRootMeanSquare() throws Exception {
//        Assert.assertEquals(162.63455967290594, features.getRootMeanSquare(),delta);
//    }

    @Test
    public void getEnergy() throws Exception {
        Assert.assertEquals(3385600.0, features.getEnergy(),delta);
    }

    @Test
    public void getEntropy() throws Exception {
        Assert.assertEquals(0, features.getEntropy(),delta);
    }

    @Test
    public void fftTest() {
        double [] input = new double[16];
        for (int i = 0; i < input.length; i++) {
            input[i] = i;
        }

        features = new Features(input);
        features.calculate();
        double[] result = features.getFftResult();
        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }
    }

    @Test
    public void percentileTest() {
        double[] input = new double[100];
        for (int i = 0; i < 100; i++) {
            input[i] = i+1;
        }
        features = new Features(input);
        features.calculateRange();
        double result95 = features.getPercentile(0.95);
        Assert.assertEquals(95.05, result95, delta);
        double result5 = features.getPercentile(0.05);
        Assert.assertEquals(5.95, result5, delta);

    }

}