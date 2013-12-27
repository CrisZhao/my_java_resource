package pongo;

public class MinOperateNum {

	public static int getNumber(String a, String b) {
		int length = a.length();
		int i = length - 1;
		int result = 0;
		for (int j = i; j >= 0; j--) {
			if (a.charAt(j) != b.charAt(i)) {
				result++;
			} else {
				i--;
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		System.out.println(getNumber("abcd", "bcad"));
	}

}
