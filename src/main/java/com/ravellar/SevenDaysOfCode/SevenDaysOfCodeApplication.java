package com.ravellar.SevenDaysOfCode;

import io.github.cdimascio.dotenv.Dotenv;

import java.util.List;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SevenDaysOfCodeApplication {

	public static void main(String[] args) throws Exception {
		Dotenv dotenv = Dotenv.load();
		System.out.println(dotenv.get("api.key"));
		String url = "https://imdb-api.com/en/API/Top250Movies/" + dotenv.get("api.key");
		System.out.println(url);

		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
				.GET()
				.uri(URI.create(url))
				.build();

		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

		String[] movies = parseJsonMovies(response.body());

		System.out.println(response.statusCode());
		System.out.println(response.body());

		List<String> titles = parseTitle(movies);
		titles.forEach(System.out::println);

		List<String> urlImg = parseUrlImg(movies);
		urlImg.forEach(System.out::println);





	}

	private static List<String> parseUrlImg(String[] movies) {
		return parseAtributes(movies,5);
	}

	private static List<String> parseTitle(String[] movies) {
		return parseAtributes(movies, 3);
	}

	private static List<String> parseAtributes(String[] movies, int pos) {
		return Stream.of(movies)
				.map(e -> e.split("\",\"")[pos])
				.map(e -> e.split(":\"")[1])
				.map(e -> e.replaceAll("\"", ""))
				.collect(Collectors.toList());
	}

	private static String[] parseJsonMovies(String json) {
		Matcher matcher = Pattern.compile(".*\\[(.*)\\].*").matcher(json);

		if (!matcher.matches()) {
			throw new IllegalArgumentException("no match in " + json);
		}
		String[] movies = matcher.group(1).split("\\},\\{");
		movies[0] = movies[0].substring(1);
		int last = movies.length - 1;
		String lastString = movies[last];
		movies[last] = lastString.substring(0, lastString.length() - 1);
		return movies;
	}
}
