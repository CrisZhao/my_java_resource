package utils;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;

public class BeanUtils {

	public static Map<String, Object> beantoMap(Object javaBean) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		Map<String, Object> result = new HashMap<String, Object>();
		Method[] methods = javaBean.getClass().getDeclaredMethods();

		for (Method method : methods) {

				if (method.getName().startsWith("get")) {
					String field = method.getName();
					field = field.substring(field.indexOf("get") + 3);
					field = field.toLowerCase().charAt(0) + field.substring(1);

					Object value = method.invoke(javaBean, new Object[0]);
					if (value != null) {
						result.put(field, value);
					}
				}

		}
		return result;
	}

	public static Object convertMap(Class<?> type, Map<String, Object> map)
			throws Exception {
		return convertMap(type.newInstance(), map);
	}

	public static <T> Object convertMap(T object, Map<String, Object> map)
			throws IntrospectionException, IllegalArgumentException,
			IllegalAccessException, InvocationTargetException {

		for (PropertyDescriptor propertyDescriptor : Introspector.getBeanInfo(
				object.getClass()).getPropertyDescriptors()) {
			String propertyName = propertyDescriptor.getName();
			// key in map should equal to propertyName in bean class
			if (map.containsKey(propertyName)) {
				propertyDescriptor.getWriteMethod().invoke(object, map.get(propertyName));
			}
		}

		return object;
	}

	public static <T> List<T> convert(Class<T> clazz, Collection<?> collection) {
		List<T> des = Lists.newArrayList();
		for (Object obj : collection) {
			des.add(clazz.cast(obj));
		}
		return des;
	}

}
