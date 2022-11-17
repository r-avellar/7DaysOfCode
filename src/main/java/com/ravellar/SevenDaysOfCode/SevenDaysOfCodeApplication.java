package com.ravellar.SevenDaysOfCode;

import io.github.cdimascio.dotenv.Dotenv;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Properties;

public class SevenDaysOfCodeApplication {

	public static void main(String[] args) throws Exception {
		Dotenv dotenv = Dotenv.load();
		String url= "https://imdb-api.com/en/API/Top250Movies/" + dotenv.get("api.key");

		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
				.GET()
				.uri(URI.create(url))
				.build();

		HttpResponse<String> response = client.send(request,HttpResponse.BodyHandlers.ofString());

		System.out.println(response.statusCode());
		System.out.println(response.body());


	}

}
