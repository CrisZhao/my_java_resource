package search;

import java.util.Collection;
import java.util.Collections;

import org.junit.Test;

import com.google.common.collect.Lists;

public class UnmodifiableTest {
	private Collection<String> data = Lists.newArrayList("11", "22");

	@Test
	public void test() {
		Collection<String> collection = Collections
				.unmodifiableCollection(data);
		System.out.println(collection);
		data.add("223");
		System.out.println(collection);

	}

}
