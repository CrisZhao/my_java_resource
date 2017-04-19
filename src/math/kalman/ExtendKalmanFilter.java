package math.kalman;

import Jama.Matrix;
import utils.DegreeLengthUtil;

/**
 * 二阶卡尔曼滤波
 * Created by Chris Zhao on 2016/12/22.
 */
public class ExtendKalmanFilter {
    private double r;
    private double sigemaA2;


    public ExtendKalmanFilter(double sigemaA2, double r) {
        this.r = r;
        this.sigemaA2 = sigemaA2;
    }


    private Point pre;
    private Matrix preMatrix;
    private Matrix p;

    double[][] initP = {
            {1, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 0},
            {0, 0, 1, 0, 0, 0},
            {0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 1, 0},
            {0, 0, 0, 0, 0, 1}};


    public Matrix process(Point point) {
        if (pre == null) {
            pre = new Point(point.getTime() - 1000, 100, 100);
//            double[][] initPre = {{point.getLongitude()}, {point.getLatitude()}, {point.getLov()}, {point.getLav()}, {0}, {0}};
            double[][] initPre = {{100}, {100}, {0}, {0}, {0}, {0}};
            preMatrix = new Matrix(initPre);
            p = new Matrix(initP);
//            return preMatrix;
            return preMatrix;
        }
        double t = (double) (point.getTime() - pre.getTime());
        t /= 1000;

        double t2 = t * t;
        double t3 = Math.pow(t, 3);
        double t4 = Math.pow(t, 4);
        double t5 = Math.pow(t, 5);
        double t6 = Math.pow(t, 6);
        pre = point;

        double[][] fnow = {
                {1, 0, t, 0, t2 / 2, 0},
                {0, 1, 0, t, 0, t2 / 2},
                {0, 0, 1, 0, t, 0},
                {0, 0, 0, 1, 0, t},
                {0, 0, 0, 0, 1, 0},
                {0, 0, 0, 0, 0, 1}
        };
        Matrix fmatrix = new Matrix(fnow);
        preMatrix = fmatrix.times(preMatrix);
//        System.out.println("estimate value:");
//        preMatrix.print(4,10);
//       pre.setLatitude(pre.getLatitude()+pre.getLav()*t);
//       pre.setLongitude(pre.getLov()*t + pre.getLongitude());


        p = fmatrix.times(p).times(fmatrix.transpose());

        t6 /= 36;
        t5 /= 12;


        double[][] qnow = {
                {t6, 0, t5, 0, t4 / 6, 0},
                {0, t6, 0, t5, 0, t4 / 6},
                {t5, 0, t4 / 4, 0, t3 / 2, 0},
                {0, t5, 0, t4 / 4, 0, t3 / 2},
                {t4 / 6, 0, t3 / 2, 0, t2, 0},
                {0, t4 / 6, 0, t3 / 2, 0, t2}
        };

        p = p.plus(new Matrix(qnow).times(sigemaA2));

        double[][] y = {
                {point.getLongitude()},
                {point.getLatitude()},
                {0},
                {0},
                {0},
                {0}
        };

        Matrix h = new Matrix(6, 6, 0);
        h.set(0, 0, 1);
        h.set(1, 1, 1);


        Matrix deltaY = new Matrix(y).minus(h.times(preMatrix));

//        System.out.println("pbefore");
//        p.print(4,10);
//        System.out.println("cal sk");
        Matrix sk = h.times(p).times(h.transpose());

        Matrix rkmatrix = new Matrix(6, 6, 0);
        for (int i = 0; i < 6; i++) {
            rkmatrix.set(i, i, r);
        }
        sk = sk.plus(rkmatrix);
//        System.out.println("sk");
//        sk.print(4,10);
        Matrix gk = p.times(h.transpose()).times(sk.inverse());

//       double[][] xk = {{pre.getLongitude()}, {pre.getLatitude()}, {pre.getLov()}, {pre.getLav()}};
        preMatrix = preMatrix.plus(gk.times(deltaY));

        appendLine();
//        System.out.println("time: "+point.getTime());
//        preMatrix.print(4, 10);

        Matrix i = new Matrix(6, 6, 0);
        i.set(0, 0, 1);
        i.set(1, 1, 1);
        i.set(2, 2, 1);
        i.set(3, 3, 1);
        for (int j = 0; j < 6; j++) {
            i.set(j, j, 1);
        }
        p = i.minus(gk.times(h)).times(p);


//        System.out.println(" p ");
//        p.print(4, 10);

        return preMatrix;
    }

    private void appendLine() {
        StringBuilder sb = new StringBuilder();
        sb.append(pre.getTime()).append(",").append(preMatrix.get(0, 0)).append(",").append(preMatrix.get(1, 0)).append(",").append(preMatrix.get(2, 0)).append(",").append(preMatrix.get(3, 0)).append(",").append(preMatrix.get(4, 0)).append(",").append(preMatrix.get(5, 0));
        sb.append("\n");
//        try {
//            FileUtil.appendWrite("C:\\Users\\risk\\Desktop\\mywork\\accelerator\\result\\resultNon2.csv", sb.toString());
//            FileUtil.appendWrite("C:\\Users\\risk\\Desktop\\mywork\\zhou\\data\\calmanfilter\\resultNon2.csv", sb.toString());
//            FileUtil.appendWrite("C:\\Users\\risk\\Desktop\\mywork\\zhou\\data\\calmanfilter\\10.27\\result_100_100.csv", sb.toString());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }


    public static void main(String[] args) throws Exception {

//        ExtendKalmanFilter filter = new ExtendKalmanFilter(0.001, 0.0025);
//        Random r = new Random();
//        int l = 0;
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < 200; i++) {
//
//            double value = getY(i);
//            Point p = new Point(i, value, value);
//            sb.append(p.toString())
//                    .append(",").append(l)
//                    .append("\n");
//            filter.process(p);
//        }
//
//        try {
//            FileUtil.appendWrite("C:\\Users\\risk\\Desktop\\mywork\\zhou\\data\\calmanfilter\\startNon_2.csv", sb.toString());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }

    private static double getY(int index) {
        double a0 = 0;
        double a1 = 2;
        double a2 = 1;
        double b1 = 5;
        double b2 = 1;
        double w = Math.PI / 4;
        double b3 = 10;
        double b4 = 50;
        double y = a0 + a1 * index + a2 * index * index + b1 * Math.sin(w * index) + b2 * Math.cos(w * index) + b3 * Math.exp(index / b4);
        return y;
    }
}
