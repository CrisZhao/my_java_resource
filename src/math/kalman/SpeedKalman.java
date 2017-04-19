package math.kalman;

/**
 * 考虑速度物理意义速度不小于0
 * Created by Chris Zhao on 2017/1/24.
 */
public class SpeedKalman {
    private KalmanFilter1D kalmanFilter1D;

    public SpeedKalman() {
        kalmanFilter1D = new KalmanFilter1D();
    }

    public SpeedKalman(KalmanFilter1D kalmanFilter1D) {
        this.kalmanFilter1D = kalmanFilter1D;
    }

    public double process(long time, double value) {
        double result =  kalmanFilter1D.process(time, value);
        if(result<0) {
            result = 0;
            kalmanFilter1D.resetModelValue(result);
        }
        return result;
    }

    public void updateModel() {
        kalmanFilter1D.updateModel();
    }


}
