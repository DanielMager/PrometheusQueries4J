package com.github.DanielMager.prometheusQueries4J.result;

import com.google.gson.annotations.SerializedName;

/**
 * Possible result types inside a PrometheusResponseData object
 *
 * @author Daniel Mager
 *
 */
public enum PrometheusResultType {

	@SerializedName("matrix") MATRIX("matrix"), 
	@SerializedName("vector") VECTOR("vector"), 
	@SerializedName("scalar") SCALAR("scalar"), 
	@SerializedName("string") STRING("string")
	;

	private final String resultType;

	/**
	 * Constructor.
	 * 
	 * @param resultType the resultType
	 */
	private PrometheusResultType(String resultType) {
		this.resultType = resultType;
	}

	public String getResultType() {
		return resultType;
	}

	@Override
	public String toString() {
		return resultType;
	}
}
