package utils;

import static java.lang.Math.cos;

/**
 * 经纬度/距离计算
 * Created by Chris Zhao on 2016/12/26.
 */
public class DegreeLengthUtil {

    //单位米
    public static final double R = 6371000;
    public static final double actLength = Math.PI * R / 180.0;
    public static final double act = Math.PI / 180.0;

    public static double convertLatitude(double latitudeDelta) {
        return actLength * latitudeDelta;
    }

    public static double convertLength2Latidute(double length) {
        return length / actLength;
    }

    public static double convertLength2Longitude(double length, double latitude) {
        return length / (actLength * cos(act * latitude));
    }

    public static double convertLongitude(double latitude, double longitudeDelta) {
        return longitudeDelta * actLength * cos(act * latitude);
    }

    /**
     * hav方法计算经纬度距离转换
     * https://en.wikipedia.org/wiki/Haversine_formula
     * @param lat1
     * @param lon1
     * @param lat2
     * @param lon2
     * @return
     */
    public static double distanceHav(double lat1, double lon1, double lat2, double lon2) {
        double la2 = degreeToRadians(lat2);
        double lo2 = degreeToRadians(lon2);
        double la1 = degreeToRadians(lat1);
        double lo1 = degreeToRadians(lon1);


        double h = hav(la2 - la1) + Math.cos(la2) * Math.cos(la1) * hav(lo2 - lo1);
        double distance = 2 * DegreeLengthUtil.R * Math.asin(Math.sqrt(h));
        return distance;

    }

    public static double degreeToRadians(double degree) {
        return degree * Math.PI / 180.0;
    }

    public static double radiansToDegree(double radian) {
        return radian * 180.0 / Math.PI;
    }

    private static double hav(double theta) {
        double v = Math.sin(theta / 2);
        return v * v;
    }
}
