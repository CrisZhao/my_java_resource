package pongo;

/**
 * User: Cris Zhao
 * Date: 13-9-9
 * Time: 下午2:12
 */
public class Water {

    public static int gcd(int a, int b) {
        int c = 1;
        while (true) {
            c = a % b;
            if (c == 0) {
                return b;
            }
            a = b;
            b = c;
        }
    }

    public static boolean can(int a, int b, int c) {
        int gcb = gcd(a, b);
        if (c % gcb == 0) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        int a=654321;
        int b=123456;
        int c=33333;
        System.out.println(can(a,b,c));


    }
}
