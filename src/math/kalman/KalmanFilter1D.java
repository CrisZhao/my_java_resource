package math.kalman;

import Jama.Matrix;

/**
 * 卡尔曼滤波
 * Created by Chris Zhao on 2016/12/22.
 */
public class KalmanFilter1D {
    //观测误差大小,
    private double r;
    //影响速度的稳定性
    private double sigemaA2;
    //最小时间间隔,默认1s
    private int time_interval = 1000;
    //单位阵
    private static Matrix unitMatrix;
    //观测矩阵
    private static Matrix h;
    private static Matrix hTransport;
    //观测误差矩阵
    private Matrix rk = new Matrix(2, 2);

    static {
        h = new Matrix(2, 2, 0);
        h.set(0, 0, 1);
        hTransport = h.transpose();

        unitMatrix = new Matrix(2, 2);
        for (int i = 0; i < 2; i++) {
            unitMatrix.set(i, i, 1);
        }

    }

    //计算中间值
    private long tempTime;
    private Matrix tempPredict;
    private Matrix tempP;

    /**
     * default r=1, sigemaA2=1,time_interval = 1000ms
     */
    public KalmanFilter1D() {
        //观测误差为1
        this.r = 1;
        //速度较平滑
        this.sigemaA2 = 1;

        for (int i = 0; i < 2; i++) {
            rk.set(i, i, r);
        }
    }

    /**
     * @param r        观测误差,应接近于现实误差,过小滤波效果差，过大数据变动不敏感
     * @param sigemaA2 速度稳定性，反应模型跟踪能力
     */
    public KalmanFilter1D(double r, double sigemaA2) {
        this.r = r;
        this.sigemaA2 = sigemaA2;

        for (int i = 0; i < 2; i++) {
            rk.set(i, i, r);
        }
    }

    /**
     * @param r             观测误差,应接近于现实误差,过小滤波效果差，过大数据变动不敏感
     * @param sigemaA2      速度稳定性，反应模型跟踪能力
     * @param time_interval 最小时间间隔，ms
     */
    public KalmanFilter1D(double r, double sigemaA2, int time_interval) {
        this.r = r;
        this.sigemaA2 = sigemaA2;
        this.time_interval = time_interval;

        for (int i = 0; i < 2; i++) {
            rk.set(i, i, r);
        }
    }

    private long preTime;
    private Matrix preMatrix;
    private Matrix p;

    private double[][] initP = {
            {1, 0},
            {0, 0.36}};

    public double process(long time, double point) {
        if (preTime <= 0) {
//            preTime = time;
            double[][] initPre = {{point}, {0}};
//            preMatrix = new Matrix(initPre);
//            p = new Matrix(initP);
            tempPredict = new Matrix(initPre);
            tempTime = time;
            tempP = new Matrix(initP);
            return point;
        }
        double t = (double) (time - preTime);
        t /= time_interval;

//        状态转移矩阵
//        double[][] f = {
//                {1, t},
//                {0, 1}
//        };
        Matrix fmatrix = unitMatrix.copy();
        fmatrix.set(0, 1, t);
        //先验
        Matrix predict = fmatrix.times(preMatrix);
        Matrix pnow = fmatrix.times(p).times(fmatrix.transpose());

        double t2 = t * t;
        double t3 = t2 * t;
        double t4 = t3 * t;
        t4 /= 4;
        t3 /= 2;
        double[][] qnow = {
                {t4, t3},
                {t3, t2}
        };

        pnow = pnow.plus(new Matrix(qnow).times(sigemaA2));

        double[][] y = {
                {point},
                {0}
        };

        Matrix deltaY = new Matrix(y).minus(h.times(predict));

        Matrix sk = h.times(pnow).times(hTransport);

        sk = sk.plus(rk);
        Matrix gk = pnow.times(hTransport).times(sk.inverse());

        predict = predict.plus(gk.times(deltaY));

        //model output
        double result = predict.get(0, 0);

        tempTime = time;
        tempPredict = predict;
        tempP = unitMatrix.minus(gk.times(h)).times(pnow);

        //update
//        updateModel();
        return result;
    }

    public double processAndUpdate(long time, double point) {
        double result = process(time, point);
        updateModel();
        return result;
    }

    public void resetModelValue(double value) {
        tempPredict.set(0,0,value);
    }

    public void updateModel() {
        preMatrix = tempPredict;
        preTime = tempTime;
        p = tempP;
    }

}
