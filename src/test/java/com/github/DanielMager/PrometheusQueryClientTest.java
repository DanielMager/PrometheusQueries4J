package com.github.DanielMager;

import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.github.DanielMager.prometheusQuery4J.PrometheusQueryClient;
import com.github.DanielMager.prometheusQuery4J.response.PrometheusQueryResponse;
import com.github.DanielMager.prometheusQuery4J.response.PrometheusStatus;
import com.github.DanielMager.prometheusQuery4J.response.data.PrometheusQueryMatrixResponseData;
import com.github.DanielMager.prometheusQuery4J.response.data.PrometheusQueryResponseData;
import com.github.DanielMager.prometheusQuery4J.response.data.PrometheusQueryScalarResponseData;
import com.github.DanielMager.prometheusQuery4J.response.data.PrometheusQueryStringResponseData;
import com.github.DanielMager.prometheusQuery4J.response.data.PrometheusQueryVectorResponseData;
import com.github.DanielMager.prometheusQuery4J.result.PrometheusResultType;
import com.github.DanielMager.prometheusQuery4J.result.PrometheusVectorResult;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;

public class PrometheusQueryClientTest {

	private PrometheusQueryClient queryClient;

	private Gson gson;
	
	@Before
	public void setUp() throws Exception {
		queryClient = new PrometheusQueryClient(new URL("http://localhost:9090/"));
		
		RuntimeTypeAdapterFactory<PrometheusQueryResponseData> runtimeTypeAdapterFactory = 
                RuntimeTypeAdapterFactory
               .of(PrometheusQueryResponseData.class, "resultType", true)
               .registerSubtype(PrometheusQueryResponseData.class, "PrometheusQueryResponseData")
               .registerSubtype(PrometheusQueryVectorResponseData.class, "vector")
               .registerSubtype(PrometheusQueryMatrixResponseData.class, "matrix")
               .registerSubtype(PrometheusQueryScalarResponseData.class, "scalar")
               .registerSubtype(PrometheusQueryStringResponseData.class, "string");
		
		gson = new GsonBuilder()
				.registerTypeAdapterFactory(runtimeTypeAdapterFactory)
				.setPrettyPrinting()
				.create();
	}

	/**
	 * Test JSON serialization.
	 */
	@Test
	public void testJsonSerializationAndDeserialization() {
		System.out.println("##### testJsonSerializationAndDeserialization() started... #####");

		// Create example Response
		PrometheusVectorResult vectorResult = new PrometheusVectorResult();
		vectorResult.setMetric(new HashMap<String, String>() {
			private static final long serialVersionUID = 1L;
			{
				put("__name__", "someMetric");
				put("instance", "localhost:12345");
				put("job", "someJob");
			}
		});
		vectorResult.setValue(Arrays.asList("1654846400.275", "100"));

		PrometheusQueryVectorResponseData prometheusQueryVectorResponseData = new PrometheusQueryVectorResponseData();
		prometheusQueryVectorResponseData.setResultType(PrometheusResultType.VECTOR);
		prometheusQueryVectorResponseData.setResult(List.of(vectorResult));

		PrometheusQueryResponse prometheusQueryResponse = new PrometheusQueryResponse();
		prometheusQueryResponse.setStatus(PrometheusStatus.SUCCESS);
		prometheusQueryResponse.setData(prometheusQueryVectorResponseData);

		// Test
		System.out.println("Serializing response...");
		String serialized = gson.toJson(prometheusQueryResponse);
		System.out.println("Serialized JSON:");
		System.out.println(serialized);
		
		System.out.println("Deserializing response...");
		PrometheusQueryResponse deserialized = gson.fromJson(serialized, PrometheusQueryResponse.class);
		System.out.println("Result type: " + deserialized.getData().getResultType());
		
		System.out.println("Serializing response again...");
		serialized = gson.toJson(deserialized);
		System.out.println("Serialized JSON:");
		System.out.println(serialized);
	}

	/**
	 * Test instant query API calls.
	 * 
	 * @throws Exception is thrown when an error occurs.
	 */
	@Test
	public void instantQueryTest() throws Exception {
		System.out.println("##### instantQueryTest() started... #####");

		PrometheusQueryResponse response = queryClient.instantQuery("go_gc_duration_seconds_count[5m]");
		
		System.out.println(gson.toJson(response));
	}
}
