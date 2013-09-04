package pongo;

/**
 * User: Cris Zhao
 * Date: 13-8-7
 * Time: 上午11:20
 */
public class Palindrom {

    private static int NUM;
    private static int a=0;

	public static boolean isPalindrom(String word) {
        int i=0;
        int j = word.length()-1;
        while(j>i) {
            if(word.charAt(i)!=word.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    private static void sort(StringBuilder datas, StringBuilder target) {
        if (target.length() == NUM) {
            boolean flag = isPalindrom(target.toString());
            if (flag) {
            	System.out.println(target.toString());
            	a+=1;
                return ;
            }
            return;
        }
        for (int i = 0; i < datas.length(); i++) {
        	StringBuilder newDatas = new StringBuilder(datas);
        	StringBuilder newTarget = new StringBuilder(target);
            newTarget.append(newDatas.charAt(i));
            newDatas.deleteCharAt(i);
            sort(newDatas, newTarget);

        }

    }

    public static void main(String[] args) {
        String test = "abba";
        StringBuilder sb = new StringBuilder(test);
        NUM = test.length();
        sort(sb, new StringBuilder());
        System.out.println(a);
        System.out.println(isPalindrom(test));
    }
}
