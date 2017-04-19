package math.kalman;

import Jama.Matrix;

/**
 * 卡尔曼滤波
 * Created by Chris Zhao on 2016/12/22.
 */
public class KalmanFilter {
    private double r;
    private double sigemaA2;


    public KalmanFilter(double sigemaA2, double r) {
        this.r = r;
        this.sigemaA2 = sigemaA2;
    }


    private Point pre;
    private Matrix preMatrix;
    private Matrix p;

    double[][] initP = {
            {1, 0, 0, 0},
            {0, 1, 0, 0},
            {0, 0, 0.36, 0},
            {0, 0, 0, 0.36}};


    public double[] process(Point point) {
        if (pre == null) {
            pre = point;
            double[][] initPre = {{point.getLongitude()}, {point.getLatitude()}, {point.getLov()}, {point.getLav()}};
//            double[][] initPre = {{-20}, {300}, {0}, {0}};
            preMatrix = new Matrix(initPre);
            p = new Matrix(initP);
            return preMatrix.getRowPackedCopy();
//            return;
        }
        double t = (double) (point.getTime() - pre.getTime());
        t /= 1000;

        pre = point;

        double[][] fnow = {
                {1, 0, t, 0},
                {0, 1, 0, t},
                {0, 0, 1, 0},
                {0, 0, 0, 1}
        };
        Matrix fmatrix = new Matrix(fnow);
        preMatrix = fmatrix.times(preMatrix);
//        System.out.println("estimate value:");
//        preMatrix.print(4,10);
//       pre.setLatitude(pre.getLatitude()+pre.getLav()*t);
//       pre.setLongitude(pre.getLov()*t + pre.getLongitude());


        p = fmatrix.times(p).times(fmatrix.transpose());
        double t2 = t * t;
        double t3 = t2 * t;
        double t4 = t3 * t;
        t4 /= 4;
        t3 /= 2;
        double[][] qnow = {
                {t4, 0, t3, 0},
                {0, t4, 0, t3},
                {t3, 0, t2, 0},
                {0, t3, 0, t2}};

        p = p.plus(new Matrix(qnow).times(sigemaA2));

        double[][] y = {
                {point.getLongitude()},
                {point.getLatitude()},
                {0},
                {0}
        };

        Matrix h = new Matrix(4, 4, 0);
        h.set(0, 0, 1);
        h.set(1, 1, 1);

        Matrix deltaY = new Matrix(y).minus(h.times(preMatrix));

//        System.out.println("pbefore");
//        p.print(4,10);
//        System.out.println("cal sk");
        Matrix sk = h.times(p).times(h.transpose());
        double[][] rk = {
                {r, 0, 0, 0},
                {0, r, 0, 0},
                {0, 0, 10000 * r, 0},
                {0, 0, 0, 10000 * r}};
        Matrix rkmatrix = new Matrix(rk);
        sk = sk.plus(rkmatrix);
//        System.out.println("sk");
//        sk.print(4,10);
        Matrix gk = p.times(h.transpose()).times(sk.inverse());

//       double[][] xk = {{pre.getLongitude()}, {pre.getLatitude()}, {pre.getLov()}, {pre.getLav()}};
        preMatrix = preMatrix.plus(gk.times(deltaY));


//        appendLine();
//        System.out.println("time: "+point.getTime());
//        preMatrix.print(4, 10);

        Matrix i = new Matrix(4, 4, 0);
        i.set(0, 0, 1);
        i.set(1, 1, 1);
        i.set(2, 2, 1);
        i.set(3, 3, 1);
        p = i.minus(gk.times(h)).times(p);

        return preMatrix.getRowPackedCopy();

//        System.out.println(" p ");
//        p.print(4, 10);

    }

//    private void appendLine() {
//        StringBuilder sb = new StringBuilder();
//        sb.append(pre.getTime()).append(",").append(preMatrix.get(0, 0)).append(",").append(preMatrix.get(1, 0)).append(",").append(preMatrix.get(2, 0)).append(",").append(preMatrix.get(3, 0));
//        sb.append("\n");
//        try {
////            FileUtil.appendWrite("C:\\Users\\risk\\Desktop\\mywork\\zhou\\data\\calmanfilter\\result10.2712.csv", sb.toString());
//            FileUtil.appendWrite("C:\\Users\\risk\\Desktop\\mywork\\zhou\\data\\calmanfilter\\result1y1.2.3.4.5.4.csv", sb.toString());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }


    public static void main(String[] args) throws Exception {

//        KalmanFilter filter = new KalmanFilter(1, 1);
//        Random r = new Random();
//        int step = 1;
//        int l = 0;
//        StringBuilder sb = new StringBuilder();
//        long startTime = 0;
////        Collection<SingleGpsData> result = Parser.parse("C:\\Users\\risk\\Desktop\\mywork\\zhou\\data\\10.27.txt");
////        for (SingleGpsData data: result) {
////            if(startTime<=0) {
////                startTime = data.getTime();
////            }
////            Point p = new Point(data.getTime()-startTime, data.getLongitude(),data.getLatitude());
////            sb.append(p.toString())
////                    .append("\n");
////            filter.process(p);
////        }
//        for (int i = 0; i < 1000; i++) {
//
////            if (i > 500) {
////                step = 2;
////            }
//            double value = getY(i);
//            Point p = new Point(i, value, value);
////            Point p = new Point(i, l, l);
//            l += step;
//            sb.append(p.toString())
//                    .append(",").append(l)
//                    .append("\n");
//            filter.process(p);
//        }
//
//        try {
//            FileUtil.appendWrite("C:\\Users\\risk\\Desktop\\mywork\\zhou\\data\\calmanfilter\\start1y1.2.3.4.5.4.csv", sb.toString());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }

    private static double getY(int index) {
        double a0 = 1;
        double a1 = 2;
        double a2 = 3;
        double b1 = 4;
        double b2 = 5;
        double w = Math.PI / 4;
        double y = a0 + a1 * index + a2 * index * index + b1 * Math.sin(w * index) + b2 * Math.cos(w * index);
        return y;
    }
}
