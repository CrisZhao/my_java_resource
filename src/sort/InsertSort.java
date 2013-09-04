package sort;

import java.util.Arrays;

/**
 * @author Cris Zhao
 * @email zhaoqingce@gmail.com
 * <p/>
 * An implementation of insert sort algorithm.
 */
public class InsertSort {

    public void sort(int[] array) {
        if (array == null) return;
        for (int i = 1; i < array.length; i++) {
            int index = i;
            int valueToInsert = array[i];
            while (index > 0 && valueToInsert < array[index - 1]) {
                array[index] = array[index - 1];
                index--;
            }
            array[index] = valueToInsert;
        }
    }

    public static void main(String[] args) {
        int[] array = {6, 2, 4, 6, 8, 2, 7, 9, 2, 4, 3};
        System.out.println(Arrays.toString(array));
        InsertSort insertSort = new InsertSort();
        insertSort.sort(array);
        System.out.println(Arrays.toString(array));
    }

}
