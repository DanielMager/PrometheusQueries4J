package com.github.DanielMager.prometheusQueries4J.response.data;

import java.util.List;

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
public class PrometheusQueryStringResponseData extends PrometheusQueryResponseData {

	private List<String> result;

	/**
	 * @return the result
	 */
	public List<String> getResult() {
		return result;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(List<String> result) {
		this.result = result;
	}
}