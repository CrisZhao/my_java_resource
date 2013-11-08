package testclass;

import java.lang.reflect.Method;
import java.util.Map.Entry;
import java.util.Stack;

import org.junit.Test;

public class SubClass extends ParentClass {
	@Override
	public void method() {
		System.out.println("sub");
	}
	public SubClass() {
		in(new Tt());
	}
public class Tt extends ParentClass.Tt{
	public Tt() {
		System.out.println("iner");
	}
}
@Test	
public void test() {
	Class<?> c =SubClass.class;
	Method[] method = c.getMethods();
	for (Method method2 : method) {
		System.out.println("method:   "+method2.toString());
	}
	for (Entry<String, String> entry : System.getenv().entrySet()) {
		System.out.println(entry.getKey()+" : "+entry.getValue());
	}
	System.out.println("-------");
		SubClass sb= new SubClass();
		System.out.println(sb);
	}
}
