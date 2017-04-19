package math.kalman;

/**
 * Created by Chris Zhao on 2016/12/22.
 */
public class Point {
    private long time;
    //经
    private double longitude;
    //纬
    private double latitude;
    private double lov;
    private double lav;

    public Point(long time, double longitude, double latitude) {
        this.time = time;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Point(int time, double longitude, double latitude, double lov, double lav) {
        this.time = time;
        this.longitude = longitude;
        this.latitude = latitude;
        this.lov = lov;
        this.lav = lav;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLov() {
        return lov;
    }

    public void setLov(double lov) {
        this.lov = lov;
    }

    public double getLav() {
        return lav;
    }

    public void setLav(double lav) {
        this.lav = lav;
    }

    @Override
    public String toString() {
        return "" +
                "" + time +
                "," + longitude +
                "," + latitude
//                +
//                "," + lov +
//                "," + lav
                ;
    }
}
