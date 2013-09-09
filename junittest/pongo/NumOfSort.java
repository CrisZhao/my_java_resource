package pongo;

/**
 * User: Cris Zhao
 * Date: 13-9-9
 * Time: 下午2:57
 */
public class NumOfSort {
    public static int run(int[] a) {
        int length = a.length;
        int count = 0;
        for (int i=0;i<length;i++) {
            if(a[i]!=i+1) {
                for (int j=i+1;j<length;j++) {
                    if (a[j] == i + 1) {
                        int temp = a[i];
                        a[i]=a[j];
                        a[j]=temp;
                        count++;
                        break;
                    }
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] a = {2,3,1};
        System.out.println(run(a));
    }
}
