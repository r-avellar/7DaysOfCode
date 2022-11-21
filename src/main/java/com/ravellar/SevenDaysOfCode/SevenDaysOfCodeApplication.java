package com.ravellar.SevenDaysOfCode;

import com.ravellar.SevenDaysOfCode.entities.Movie;
import com.ravellar.SevenDaysOfCode.utils.ImdbApiClient;
import com.ravellar.SevenDaysOfCode.utils.ImdbJsonParse;
import com.ravellar.SevenDaysOfCode.views.HTMLGenerator;
import io.github.cdimascio.dotenv.Dotenv;

import java.io.PrintWriter;
import java.util.ArrayList;
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
		String apiKey = dotenv.get("api.key");

		String response = new ImdbApiClient(apiKey).getBody();

		List<Movie> movies = new ImdbJsonParse(response).parse();

		PrintWriter writer = new PrintWriter("index.html");
		new HTMLGenerator(writer).generate(movies);
		writer.close();


	}


}


