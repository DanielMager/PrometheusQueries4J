package com.github.DanielMager.prometheusQuery4J.response;

import com.google.gson.annotations.SerializedName;

/**
 * Possible statuses Prometheus may send upon querying its API.
 * 
 * Possible values are "success" and "error".
 *
 * @author Daniel Mager
 *
 */
public enum PrometheusStatus {
	
	@SerializedName("success") SUCCESS("success"), 
	@SerializedName("error") ERROR("error")
	;

	private final String status;

	/**
	 * Constructor.
	 * 
	 * @param status
	 */
	private PrometheusStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return status;
	}
}
