package math;

public class Search {
	
	public int binarySearch(int start, int end, int value2search) {
		if(start>=end) {
			return -1;
		}
		int mid = (start + end) / 2;
		if(value2search == mid) {
			return mid;
		}
		if(value2search<mid) {
			return binarySearch(start, mid, value2search);
		} else if(value2search>mid) {
			return binarySearch(mid, end, value2search);
		}
		return end;
	}
	
	public static void main(String[] args) {
		int a = new Search().binarySearch(1, 44, 32);
		System.out.println(a);
	}

}
