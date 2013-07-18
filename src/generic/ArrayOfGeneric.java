package generic;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

public class ArrayOfGeneric {

	class Generic<T> {
		
	}
	Generic<Integer>[] gia;
	@Test
	public void genricTest() {
		ArrayList<String> aa = new ArrayList<String>();
		aa.add("22");
		System.out.println(Arrays.toString(aa.getClass().getTypeParameters()));
		System.out.println(aa.getClass().getComponentType());
		gia= (Generic<Integer>[]) new Object[100];
		System.out.println(gia.getClass().getSimpleName());
		gia[0]=new Generic<Integer>();
		

	}
}
