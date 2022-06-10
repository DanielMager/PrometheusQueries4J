# PrometheusQuery4J
Java tool for querying prometheus using PromQL

## Disclaimer
It should be noted that currently only Prometheus instant queries (see [Prometheus Docs](https://prometheus.io/docs/prometheus/latest/querying/api/#instant-queries)) have been tested!

## How to use
To get the result JSON for the API request `http://localhost:9090/api/v1/query?query=go_gc_duration_seconds_count` :

Run the following code:

	PrometheusQueryClient queryClient = new PrometheusQueryClient(new URL("http://localhost:9090/"));
	PrometheusQueryResponse response = queryClient.query("go_gc_duration_seconds_count")";
	
	Gson gson = new GsonBuilder().setPrettyPrinting().create();
	System.out.println(gson.toJson(response));
	
Which should result in a response as follows:

	{
		"status": "success",
		"data": {
			"resultType": "vector",
			"result": [
				{
					"metric": {
						"__name__": "go_gc_duration_seconds_count",
						"instance": "localhost:9090",
						"job": "prometheusMonitoring"
					},
					"value": [
						1.654852972097E9,
						"297"
					]
				}
    		]
    	}
    }

## Testing
The tool can be tested with help from the unit test. Start up a simple Prometheus instance e.g. via Docker by running the command:

	docker run -p 9090:9090 -rm prom/prometheus:v2.36.1
	
Let it run for a brief moment so it can gather some data and run PrometheusQueryClientTest#instantQueryTest(). This should result in a response similar to that described in [How to use](#how-to-use).