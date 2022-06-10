package com.github.DanielMager.prometheusQueries4J;

import com.github.DanielMager.prometheusQueries4J.response.PrometheusQueryResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Interface contains methods for different Prometheus API calls.
 *
 * @author Daniel Mager
 *
 */
public interface PrometheusApiRestController {

	static final String INSTANT_QUERY_API = "api/v1/query";
	static final String RANGE_QUERY_API = "api/v1/range_query";

	/**
	 * Performs a Prometheus instant query.
	 * 
	 * @param query   the Prometheus expression query string.
	 * @param time    the evaluation timestamp (unix_timestamp). Optional.
	 * @param timeout the evaluation timeout value described with Prometheus duration string
	 *                (e.g. "5ms" - units: "ms; s; m; h; d; y"). Optional.
	 * @return {@link Call}
	 */
	@GET(INSTANT_QUERY_API)
	Call<PrometheusQueryResponse> instantQuery(@Query(value = "query", encoded = true) String query,
			@Query(value = "time", encoded = true) Double time,
			@Query(value = "timeout", encoded = true) String timeout);

	/**
	 * Performs a Prometheus range query.
	 * 
	 * @param query the Prometheus expression query string.
	 * @param start the start timestamp, inclusive (unix_timestamp).
	 * @param end the end timestamp, inclusive (unix_timestamp).
	 * @param step the query resolution step width in Prometheus duration format (e.g. "5ms" - units: "ms; s; m; h; d; y")
	 * @param timeout the evaluation timeout. Optional.
	 * @return
	 */
	@GET(RANGE_QUERY_API)
	Call<PrometheusQueryResponse> rangeQuery(@Query(value = "query", encoded = true) String query,
			@Query(value = "start", encoded = true) Double start, 
			@Query(value = "end", encoded = true) Double end,
			@Query(value = "step", encoded = true) String step,
			@Query(value = "timeout", encoded = true) String timeout);
}
