package math;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by Chris Zhao on 2017/8/11.
 */
public class DoubleNumFormat {
    public static double format(int scale, double inputNum) {
        BigDecimal bigDecimal   =   new   BigDecimal(inputNum);
        return bigDecimal.setScale(scale, RoundingMode.HALF_UP).doubleValue();
    }

}
