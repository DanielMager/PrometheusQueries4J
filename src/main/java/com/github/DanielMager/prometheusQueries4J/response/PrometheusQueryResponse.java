package com.github.DanielMager.prometheusQueries4J.response;

import java.util.List;

import com.github.DanielMager.prometheusQueries4J.response.data.PrometheusQueryResponseData;

/**
 * Wrapper for the JSON Response for an instant query request. The JSON Response
 * format is as follows:
 * 
 * <pre>
 * {@code {
 * 	"status": "success" | "error",
 * 	"data": <data>,
 *
 * 	// Only set if status is "error". The data field may still hold
 * 	// additional data.
 * 	"errorType": "<string>",
 * 	"error": "<string>",
 *
 * 	// Only if there were warnings while executing the request.
 * 	// There will still be data in the data field.
 * 	"warnings": ["<string>"]
 * 	}
 * }
 * </pre>
 *
 * @see <a href=
 *      "https://prometheus.io/docs/prometheus/latest/querying/api/#format-overview">https://prometheus.io/docs/prometheus/latest/querying/api/#format-overview</a>
 * @author Daniel Mager
 *
 */
public class PrometheusQueryResponse {

	private PrometheusStatus status;
	private PrometheusQueryResponseData data;

	private String errorType;
	private String error;

	private List<String> warnings;

	/**
	 * @return the status
	 */
	public PrometheusStatus getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(PrometheusStatus status) {
		this.status = status;
	}

	/**
	 * @return the data
	 */
	public PrometheusQueryResponseData getData() {
		return data;
	}

	/**
	 * @param <T>  type of the result data
	 * @param data the data to set
	 */
	public <T> void setData(PrometheusQueryResponseData data) {
		this.data = data;
	}

	/**
	 * @return the errorType
	 */
	public String getErrorType() {
		return errorType;
	}

	/**
	 * @param errorType the errorType to set
	 */
	public void setErrorType(String errorType) {
		this.errorType = errorType;
	}

	/**
	 * @return the error
	 */
	public String getError() {
		return error;
	}

	/**
	 * @param error the error to set
	 */
	public void setError(String error) {
		this.error = error;
	}

	/**
	 * @return the warnings
	 */
	public List<String> getWarnings() {
		return warnings;
	}

	/**
	 * @param warnings the warnings to set
	 */
	public void setWarnings(List<String> warnings) {
		this.warnings = warnings;
	}
}
