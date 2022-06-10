package com.github.DanielMager.prometheusQueries4J.response.data;

import com.github.DanielMager.prometheusQueries4J.result.PrometheusResultType;

/**
 * Wrapper for the data field in the JSON Response for a Prometheus Query
 * Request. The JSON format is as follows:
 * 
 * <pre>
 * {@code {
 *   "resultType": "matrix" | "vector" | "scalar" | "string",
 *   "result": <value>
 * }
 * </pre>
 *
 * @see <a href=
 * "https://prometheus.io/docs/prometheus/latest/querying/api/#instant-queries">https://prometheus.io/docs/prometheus/latest/querying/api/#instant-queries</a>
 * 
 * @author Daniel Mager
 *
 */
public abstract class PrometheusQueryResponseData {
	
	public PrometheusResultType resultType;

	/**
	 * @return the type
	 */
	public PrometheusResultType getResultType() {
		return resultType;
	}

	/**
	 * @param type the type to set
	 */
	public void setResultType(PrometheusResultType resultType) {
		this.resultType = resultType;
	}
}