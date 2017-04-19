package math.calculator;

import java.util.Map;

import com.google.common.collect.Maps;

public enum OperatorType {

	number(0, null),
	add(2, "+"),
	sub(2, "-"),
	mul(1, "*"),
	div(1, "/");
	
	private int priori;
	private String value;
	private OperatorType(int priori, String value) {
		this.priori = priori;
		this.value = value;
	}
	
	public int getLevel() {
		return priori;
	}
	
	public String getValue() {
		return value;
	}
	private static Map<String, OperatorType> lookupTable = Maps.newHashMap();
	static {
		for (OperatorType in : OperatorType.values()) {
			lookupTable.put(in.getValue(), in);
		}
	};

	/**
	 * 
	 * @param desc
	 *            ,
	 * @return type of reportdef
	 */
	public static OperatorType lookup(String desc) {
		OperatorType ret = lookupTable.get(desc);
		return ret == null ? OperatorType.number : ret;
	}
}
