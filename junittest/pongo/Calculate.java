package pongo;

/**
 * Created with IntelliJ IDEA.
 * User: IT_Zhaoqince
 * Date: 13-8-6
 * Time: 下午3:31
 * To change this template use File | Settings | File Templates.
 */


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Calculate {
    //将NUM设置为待排列数组的长度即实现全排列
    private static int NUM = 4;


    private static boolean sort(List<Integer> datas, List<Integer> target) {
        if (target.size() == NUM) {
            boolean flag = calculateAndCheck(target);
            if (flag) {
                for (Integer obj : target) {
                    System.out.print(obj);
                }
                System.out.println();
                return true;
            }
//
            return false;
        }
        for (int i = 0; i < datas.size(); i++) {
            List<Integer> newDatas = new ArrayList<Integer>(datas);
            List<Integer> newTarget = new ArrayList<Integer>(target);
            newTarget.add(newDatas.get(i));
            newDatas.remove(i);
            if (sort(newDatas, newTarget)) {
                return true;
            }
        }

        return false;
    }


    private static boolean calculateAndCheck(List<Integer> nums) {
        for (int i = 0; i < 4; i++) {
            double fr = calculate(i, nums.get(0), nums.get(1));
            for (int j = 0; j < 4; j++) {
                double sc = calculate(j, fr, nums.get(2));
                double ssc = calculate(j, nums.get(2), nums.get(3));
                for (int k = 0; k < 4; k++) {
                    double result = calculate(k, sc, nums.get(3));
                    double result2 = calculate(k, fr, ssc);
//                    System.out.println(nums.get(0) +"  "+i+" "+nums.get(1)+" "+j+" "+nums.get(2)+" "+k+" "+nums.get(3)+" = "+result );
//                    System.out.println("("+nums.get(0) +"  "+i+" "+nums.get(1)+") "+k+" ("+nums.get(2)+" "+j+" "+nums.get(3)+") = "+result2 );
                    if (Math.abs(Math.abs(result) - 24) < 0.000001 || Math.abs(Math.abs(result2 - 24)) < 0.000001)
                        return true;
                }
            }
        }
        return false;
    }

    private static double calculate(int op, double a, double b) {
        switch (op) {
            case 0:
                return a + b;
            case 1:
                return a - b;
            case 2:
                return a * b;
            case 3:
                return a / b;
            default:
                throw new UnsupportedOperationException();
        }

    }

    public static void main(String[] args) {
        Integer[] datas = new Integer[]{5, 5, 5, 1};
        System.out.println(sort(Arrays.asList(datas), new ArrayList<Integer>()));
    }

}

