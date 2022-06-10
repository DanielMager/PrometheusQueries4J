package com.github.DanielMager.prometheusQuery4J;

import java.io.IOException;
import java.net.URL;

import com.github.DanielMager.prometheusQuery4J.response.PrometheusQueryResponse;
import com.github.DanielMager.prometheusQuery4J.response.data.PrometheusQueryMatrixResponseData;
import com.github.DanielMager.prometheusQuery4J.response.data.PrometheusQueryResponseData;
import com.github.DanielMager.prometheusQuery4J.response.data.PrometheusQueryScalarResponseData;
import com.github.DanielMager.prometheusQuery4J.response.data.PrometheusQueryStringResponseData;
import com.github.DanielMager.prometheusQuery4J.response.data.PrometheusQueryVectorResponseData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *
 *
 * @author Daniel Mager
 *
 */
public class PrometheusQueryClient {

	private Retrofit retrofit;

	private PrometheusApiRestController restController;

	/**
	 * Constructor.
	 * 
	 * @param prometheusBaseUrl base URL of Prometheus instance that is to be
	 *                          queried
	 */
	public PrometheusQueryClient(URL prometheusBaseUrl) {

		RuntimeTypeAdapterFactory<PrometheusQueryResponseData> runtimeTypeAdapterFactory = 
                RuntimeTypeAdapterFactory
               .of(PrometheusQueryResponseData.class, "resultType", true)
               .registerSubtype(PrometheusQueryVectorResponseData.class, "vector")
               .registerSubtype(PrometheusQueryMatrixResponseData.class, "matrix")
               .registerSubtype(PrometheusQueryScalarResponseData.class, "scalar")
               .registerSubtype(PrometheusQueryStringResponseData.class, "string");
		
		Gson gson = new GsonBuilder().registerTypeAdapterFactory(runtimeTypeAdapterFactory).create();

		this.retrofit = new Retrofit.Builder().baseUrl(prometheusBaseUrl)
				.addConverterFactory(GsonConverterFactory.create(gson)).build();
		this.restController = retrofit.create(PrometheusApiRestController.class);
	}

	/**
	 * Performs a Prometheus instant query with the given query.
	 * 
	 * @param query the Prometheus expression query string.
	 * @return {@link PrometheusQueryResponse}
	 * @throws IOException is thrown when an error occurs.
	 */
	public PrometheusQueryResponse instantQuery(String query) throws IOException {
		return restController.instantQuery(query, null, null).execute().body();
	}

	/**
	 * Performs a Prometheus instant query with the given parameters.
	 * 
	 * @param query the Prometheus expression query string.
	 * @param time  the evaluation timestamp (unix_timestamp). Optional.
	 * @return {@link PrometheusQueryResponse}
	 * @throws IOException is thrown when an error occurs.
	 */
	public PrometheusQueryResponse instantQuery(String query, Double time) throws IOException {
		return restController.instantQuery(query, time, null).execute().body();
	}

	/**
	 * Performs a Prometheus instant query with the given parameters.
	 * 
	 * @param query   the Prometheus expression query string.
	 * @param time    time the evaluation timestamp (unix_timestamp). Optional.
	 * @param timeout the evaluation timeout value described with Prometheus
	 *                duration string(e.g. "5ms" - units: "ms; s; m; h; d; y").
	 *                Optional.
	 * @return {@link PrometheusQueryResponse}
	 * @throws IOException is thrown when an error occurs.
	 */
	public PrometheusQueryResponse instantQuery(String query, Double time, String timeout) throws IOException {
		return restController.instantQuery(query, time, timeout).execute().body();
	}

	/**
	 * Performs a Prometheus range query with the given parameters.
	 * 
	 * @param query the Prometheus expression query string.
	 * @param start the start timestamp, inclusive (unix_timestamp).
	 * @param end   the end timestamp, inclusive (unix_timestamp).
	 * @param step  the query resolution step width in Prometheus duration format
	 *              (e.g. "5ms" - units: "ms; s; m; h; d; y")
	 * @return {@link PrometheusQueryResponse}
	 * @throws IOException is thrown when an error occurs.
	 */
	public PrometheusQueryResponse rangeQuery(String query, Double start, Double end, String step) throws IOException {
		return restController.rangeQuery(query, start, end, step, null).execute().body();
	}

	/**
	 * Performs a Prometheus range query with the given parameters.
	 * 
	 * @param query   the Prometheus expression query string.
	 * @param start   the start timestamp, inclusive (unix_timestamp).
	 * @param end     the end timestamp, inclusive (unix_timestamp).
	 * @param step    the query resolution step width in Prometheus duration format
	 *                (e.g. "5ms" - units: "ms; s; m; h; d; y")
	 * @param timeout the evaluation timeout. Optional.
	 * @return {@link PrometheusQueryResponse}
	 * @throws IOException is thrown when an error occurs.
	 */
	public PrometheusQueryResponse rangeQuery(String query, Double start, Double end, String step, String timeout)
			throws IOException {
		return restController.rangeQuery(query, start, end, step, timeout).execute().body();
	}
}
