package com.github.DanielMager.prometheusQuery4J.result;

import java.util.List;
import java.util.Map;

/**
 * Wrapper for the result field in the data field in the JSON Response for a
 * Prometheus Query Request with requestType "string". The JSON format is as
 * follows:
 * 
 * <pre>
 * {@code  [
 *   {
 *     "metric": { "&lt;label_name&gt;": "&lt;label_value&gt;", ... },
 *     "values": [ [ &lt;unix_time&gt;, "&lt;sample_value&gt;" ], ... ]
 *   },
 *   ...
 *  ]
 * </pre>
 *
 * @see <a href=
 * "https://prometheus.io/docs/prometheus/latest/querying/api/#instant-queries">https://prometheus.io/docs/prometheus/latest/querying/api/#instant-queries</a>
 * 
 * @author Daniel Mager
 *
 */
public class PrometheusMatrixResult {

	private Map<String, String> metric;
	private List<List<String>> values;

	/**
	 * @return the metric
	 */
	public Map<String, String> getMetric() {
		return metric;
	}

	/**
	 * @param metric the metric to set
	 */
	public void setMetric(Map<String, String> metric) {
		this.metric = metric;
	}

	/**
	 * @return the value
	 */
	public List<List<String>> getValue() {
		return values;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(List<List<String>> value) {
		this.values = value;
	}
}
