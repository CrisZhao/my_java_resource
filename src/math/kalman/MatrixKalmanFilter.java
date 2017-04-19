package math.kalman;

import Jama.Matrix;

/**
 * 卡尔曼滤波
 * Created by Chris Zhao on 2016/12/22.
 */
public class MatrixKalmanFilter {
    private double r;
    private double sigemaA2;
    private int dimension;
    //dimension * 2
    private int dimension2;
    //前一时刻时间
    private long preTime;
    private Matrix preMatrix;
    private Matrix p;
    //单位阵
    private Matrix unitMatrix;
    //观测误差矩阵
    private Matrix rk;
    //观测矩阵
    private Matrix h;
    private Matrix hTransport;

    public MatrixKalmanFilter(int dimension, double sigemaA2, double r) {
        this.r = r;
        this.sigemaA2 = sigemaA2;
        this.dimension = dimension;
        this.dimension2 = dimension * 2;
        init();
    }


    /**
     * 前demition个值代表对应的输入值，后demition个值表示一阶导
     *
     * @param data
     * @param time
     * @return
     */
    public double[] process(double[] data, long time) {
        if (data == null || data.length != dimension) {
            throw new UnsupportedOperationException("error input data");
        }
        if (preMatrix == null) {
            preTime = time;
            preMatrix = new Matrix(dimension2, 1);

            for (int i = 0; i < dimension; i++) {
                preMatrix.set(i, 0, data[i]);
            }
            return preMatrix.getRowPackedCopy();
        }
        double t = (double) (time - preTime);
        t/=1000;
        preTime = time;
//     状态转移矩阵[1,t]
//                 [0,1]
        Matrix fmatrix = unitMatrix.copy();
        for (int i = 0; i < dimension; i++) {
            fmatrix.set(i, dimension + i, t);
        }
        preMatrix = fmatrix.times(preMatrix);


        p = fmatrix.times(p).times(fmatrix.transpose());
        double t2 = t * t;
        double t3 = t2 * t;
        double t4 = t3 * t;
        t4 /= 4;
        t3 /= 2;
//        double[][] qnow = {
//                {t4, 0, t3, 0},
//                {0, t4, 0, t3},
//                {t3, 0, t2, 0},
//                {0, t3, 0, t2}};
        Matrix q = new Matrix(dimension2, dimension2);
        for (int i = 0; i < dimension; i++) {
            q.set(i, i, t4);
            q.set(dimension + i, dimension + i, t2);
            q.set(i, dimension + i, t3);
            q.set(dimension + i, i, t3);
        }

        p = p.plus(q.times(sigemaA2));

//        double[][] y = {
//                {point.getLongitude()},
//                {point.getLatitude()},
//                {0},
//                {0}
//        };
        Matrix y = new Matrix(dimension2, 1);
        for (int i = 0; i < dimension; i++) {
            y.set(i, 0, data[i]);
        }

        Matrix deltaY = y.minus(h.times(preMatrix));

        Matrix sk = h.times(p).times(hTransport);

        sk = sk.plus(rk);

        Matrix gk = p.times(hTransport).times(sk.inverse());

        preMatrix = preMatrix.plus(gk.times(deltaY));

        double[] result = preMatrix.getRowPackedCopy();

        p = unitMatrix.minus(gk.times(h)).times(p);
        return result;

    }

    private void init() {
        //init p 10
        p = new Matrix(dimension2, dimension2);
        for (int i = 0; i < dimension2; i++) {
            p.set(i, i, 10);
        }
        //init unitMatrix
        unitMatrix = new Matrix(dimension2, dimension2);
        for (int i = 0; i < dimension2; i++) {
            unitMatrix.set(i, i, 1);
        }

        //init rk
//            double[][] rk = {
//                    {r, 0, 0, 0},
//                    {0, r, 0, 0},
//                    {0, 0, 10000 * r, 0},
//                    {0, 0, 0, 10000 * r}};
//            Matrix rkmatrix = new Matrix(rk);
        rk = new Matrix(dimension2, dimension2);
        for (int i = 0; i < dimension2; i++) {
            rk.set(i, i, r);
        }

        h = new Matrix(dimension2, dimension2, 0);
        for (int i = 0; i < dimension; i++) {
            h.set(i, i, 1);
        }

        hTransport = h.transpose();
    }


}
