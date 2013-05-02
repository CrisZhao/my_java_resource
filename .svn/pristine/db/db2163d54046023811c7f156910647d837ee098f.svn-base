package exception;

public enum ErrorCode {
	// TODO: define description in a property file

	PRICING_ERROR(10000, "pricing error"),

	ATTRIBUTE_MISSING_ERROR(11001, "product attribute missing error"),

	METHOD_NAME_ERROR(11002, "invalid method name in pricing model parameter"),

	PRICING_CALCULATOR_ERROR(12000, "an error occur in pricing process"),

	// ----------------------------------------------------------------
	DATA_ERROR(20000, "data error"),

	DATA_TYPE_ERROR(21000, "data type error"),

	PRODUCT_TYPE_ERROR(21001, "product type error"),

	PRODUCT_PARSING_ERROR(21002, "product parsing error"),

	INVALID_DATA_VALUE(22000, "data value error"),

	INVALID_PARAMETER_VALUE(21800, "invalid parameter value"),

	// ----------------------------------------------------------------
	DATABASE_ERROR(30000, "database error"),

	// ----------------------------------------------------------------
	CONFIG_ERROR(40000, "configuration error"),

	MAPPING_ERROR(50000, "Mapping error"),

	// ----------------------------------------------------------------
	ACTION_ERROR(60000, "Operation is not supported"),

	SESSION_INVALID(65001, "Session invalid"),

	// ----------------------------------------------------------------
	FINCAD_CONNECTION_ERROR(70000, "Fincad connection error");

	private int code;
	private String description;

	/**
	 * 
	 * @param code
	 *            ,
	 * @param description
	 *            ,
	 */
	private ErrorCode(int code, String description) {
		this.code = code;
		this.description = description;
	}

	/**
	 * 
	 * @return description
	 */
	public String getDecription() {
		return this.description;
	}

	/**
	 * 
	 * @return code
	 */
	public int getCode() {
		return this.code;
	}
}
