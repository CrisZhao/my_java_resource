package search;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import org.junit.Test;

import com.google.common.collect.Lists;

public class UnmodifiableTest {
	private Collection<String> data = Lists.newArrayList("11", "22");
	@Test
	public void test() {
		Collection<String> collection = Collections.unmodifiableCollection(data);
		System.out.println(collection);
		data.add("223");
		System.out.println(collection);
		
	}

}
