package math.feature;



import math.FFT;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Chris Zhao on 2017/1/11.
 */
public class Features {
    private double[] data;
    private double[] ascData;
    //0为DC分量,[1,norm/2+1]代表各幅值分量
    private double[] fftResult;
    private double sum;
    private double sumSquare;
    private int num;
    private double max;
    private double min;
    private double mean;
    private double median;
    private double standardDeviation;
    private double range;
    private double interQuartileRange;
    private double rootMeanSquare;
    private double energy;
    private double entropy;

    public Features(double[] data) {
        this.data = data;
    }

    public void calculate() {
        calculateStatistic();
        calculateRange();
        calculateFrequency();
    }

    public void calculateStatistic() {
        num = data.length;
        sum = 0;
        sumSquare = 0;
        for (double value : data) {
            sum += value;
            sumSquare += value * value;
        }
        mean = sum / num;
        rootMeanSquare = Math.sqrt(sumSquare / num);

        standardDeviation = 0;
        for (double value : data) {
            standardDeviation += Math.pow(value - mean, 2);
        }
        standardDeviation = Math.sqrt(standardDeviation);
    }

    public void calculateRange() {
        num = data.length;
        ascData = new double[data.length];
        System.arraycopy(data, 0, ascData, 0, data.length);
        Arrays.sort(ascData);
        min = ascData[0];
        max = ascData[data.length - 1];
        range = max - min;
        if (num % 2 == 0) {
            median = ascData[num / 2 - 1] + ascData[num / 2];
        } else {
            median = ascData[num / 2];
        }
        double a = (1.0 + num) / 4.0;
        double b = 3.0 * (1.0 + num) / 4.0;
        int aInt = (int) a;
        int bInt = (int) b;
        double q1 = ascData[aInt - 1] + (a - aInt) * (ascData[aInt + 1] - ascData[aInt]);
        double q3 = ascData[bInt - 1] + (b - bInt) * (ascData[bInt + 1] - ascData[bInt]);
        interQuartileRange = q3 - q1;

    }

    public double getPercentile(double percentile) {
        num = data.length;
        double g = (num-1)*percentile;
        int j = (int) g;
        g = g-j;
        if(Math.abs(g)<0.00001) {
            return ascData[j];
        }
        return ascData[j]+g*(ascData[j+1]-ascData[j]);
    }

    public void calculateFrequency() {
        num = data.length;
        fftResult = FFT.ft(data);
        energy = 0;
        double freSum = 0;
        double[] p = new double[fftResult.length];
        for (int i = 1; i < fftResult.length; i++) {
            double value = fftResult[i];
            p[i] = Math.abs(value);

            if (p[i] < 0.0000001) {
                fftResult[i] = 0;
                p[i] = 0;
                continue;
            }
            energy += value * value;
            freSum += p[i];
        }

        entropy = 0;
        for (int i = 1; i < fftResult.length; i++) {
            double value = p[i];
            if (value < 0.0000001) {
                continue;
            }
            p[i] /= freSum;
            entropy += -p[i] * Math.log(p[i]);
        }

    }

    public double[] getFftResult() {
        return fftResult;
    }

    public double getSum() {
        return sum;
    }

    public double getSumSquare() {
        return sumSquare;
    }

    public double getMax() {
        return max;
    }

    public double getMin() {
        return min;
    }

    public double getMean() {
        return mean;
    }

    public double getStandardDeviation() {
        return standardDeviation;
    }

    public double getRange() {
        return range;
    }

    public double getInterQuartileRange() {
        return interQuartileRange;
    }

    public double getRootMeanSquare() {
        return rootMeanSquare;
    }

    public double getEnergy() {
        return energy;
    }

    public double getEntropy() {
        return entropy;
    }

    public double getMedian() {
        return median;
    }

    @Override
    public String toString() {
        StringBuilder content = new StringBuilder();
        content.append(mean).append(",")
                .append(standardDeviation).append(",")
                .append(range).append(",")
                .append(interQuartileRange).append(",")
                .append(rootMeanSquare).append(",")
                .append(energy).append(",")
                .append(entropy).append(",");
        for (int i = 1; i < 7; i++) {
            content.append(fftResult[i]).append(",");
        }
        content.deleteCharAt(content.length() - 1);
        return content.toString();
    }

    public static Features calculateFeatures(List<Double> frameData) {
        double[] results = new double[frameData.size()];

        for (int i = 0; i < frameData.size(); i++) {
            double value = frameData.get(i);
            results[i] = value;
        }
        Features features = new Features(results);
        features.calculate();
        return features;

    }

    public double[] buildRangeIns() {
        double[] data = new double[5];
        data[0] = getMean();
        data[1] = getStandardDeviation();
        data[2] = getRange();
        data[3] = getInterQuartileRange();
        return data;
    }

    public double[] buildAllFeaturesIns() {
        double[] data = new double[13];
        data[0] = getMean();
        data[1] = getStandardDeviation();
        data[2] = getRange();
        data[3] = getInterQuartileRange();
        data[4] = getEnergy();
        data[5] = getEntropy();
        data[6] = getFftResult()[1];
        data[7] = getFftResult()[2];
        data[8] = getFftResult()[3];
        data[9] = getFftResult()[4];
        return data;
    }
}
