package pongo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * User: Cris Zhao
 * Date: 13-8-23
 * Time: 下午4:47
 */
public class Permutation {

    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void permute(int[] array, List<int[]> cache, int k)
    {

        if (k == array.length-1)
        {
            cache.add(array.clone());
            System.out.println(Arrays.toString(array)); //TODO comment this line after test
            return;
        }
        for(int i=k; i<array.length; i++)
        {
            swap(array, i,k);
            permute(array, cache, k+1);
            swap(array, i, k);
        }

    }


    public static void main(String[] args) {
        int[] array = {1,9,4};
        ArrayList<int[]> result1 = new ArrayList<int[]>();
        permute(array, result1, 0);
    }
}
