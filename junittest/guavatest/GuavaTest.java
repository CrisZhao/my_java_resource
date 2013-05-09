package guavatest;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;
import com.google.common.collect.MapMaker;

public class GuavaTest {
	@Test
	public void test() {
		ImmutableList<Integer> list = ImmutableList.of(1,2,3);
		Map<String, Integer> map = ImmutableMap.of("1", 1, "2", 2);
		Builder<String, Integer> builder = ImmutableMap.builder();
		builder.put("1", 1);
		ImmutableMap<String, Integer> map2 = builder.build();
		new MapMaker().expiration(30, TimeUnit.MINUTES).makeComputingMap(new Function<String, String>() {

			@Override
			public String apply(String arg0) {
				return null;
			}
		});
	}

}
