package utils;

import java.beans.BeanInfo;
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

	public static Map<String, Object> beantoMap(Object javaBean) {
		Map<String, Object> result = new HashMap<String, Object>();
		Method[] methods = javaBean.getClass().getDeclaredMethods();

		for (Method method : methods) {

			try {
				if (method.getName().startsWith("get")) {
					String field = method.getName();
					field = field.substring(field.indexOf("get") + 3);
					field = field.toLowerCase().charAt(0) + field.substring(1);

					Object value = method.invoke(javaBean, (Object[]) null);
					if (value != null) {
						result.put(field, value);
					}
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}

		}
		return result;
	}

	public static Object convertMap(Class<?> type, Map<String, Object> map)
			throws Exception {
		return convertMap(type.newInstance(), map);
	}

	public static <T> Object convertMap(T object, Map<String, Object> map)
			throws Exception {

		BeanInfo beanInfo = Introspector.getBeanInfo(object.getClass());
		PropertyDescriptor[] propertyDescriptors = beanInfo
				.getPropertyDescriptors();
		for (int i = 0; i < propertyDescriptors.length; i++) {
			PropertyDescriptor descriptor = propertyDescriptors[i];
			String propertyName = descriptor.getName();
			// key in map should equal to propertyName in bean class
			if (map.containsKey(propertyName)) {
				Object value = map.get(propertyName);
				Object[] args = new Object[1];
				args[0] = value;
				descriptor.getWriteMethod().invoke(object, args);
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
