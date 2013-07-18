package reflact;

import java.lang.reflect.Proxy;

import org.junit.Test;

public class SimpleDynamicProxy {
	public interface Interface {
		public void dosomething();

		public void somethingelse(String arg);
	}

	public class RealObject implements Interface {

		@Override
		public void dosomething() {
			System.out.println("doing something");

		}

		@Override
		public void somethingelse(String arg) {
			System.out.println("somethingelse " + arg);
		}

	}

	public void consumer(Interface iface) {
		iface.dosomething();
		iface.somethingelse("testing");
	}

	@Test
	public void test() {
		RealObject rl = new RealObject();
		consumer(rl);
		Interface proxy = (Interface) Proxy.newProxyInstance(Interface.class.getClassLoader(), new Class[]{Interface.class}, new DynamicProxyHandler(rl));
		consumer(proxy);

	}
}
