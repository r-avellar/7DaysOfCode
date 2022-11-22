package com.ravellar.SevenDaysOfCode;

import com.ravellar.SevenDaysOfCode.repositories.Content;
import com.ravellar.SevenDaysOfCode.repositories.JsonParser;
import com.ravellar.SevenDaysOfCode.utils.ImdbApiClient;
import com.ravellar.SevenDaysOfCode.utils.HTMLGenerator;
import com.ravellar.SevenDaysOfCode.utils.ImdbJsonParse;
import io.github.cdimascio.dotenv.Dotenv;

import java.io.PrintWriter;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class SevenDaysOfCodeApplication {

	public static void main(String[] args) throws Exception {
		Dotenv dotenv = Dotenv.load();
		String apiKey = dotenv.get("api.key");

		ImdbApiClient apiClient = new ImdbApiClient(apiKey);
		String response = apiClient.getBody();

		JsonParser jsonParser = new ImdbJsonParse(response);
		List<? extends Content> contentList = jsonParser.parse();

		Collections.sort(contentList, Comparator.comparing(Content::year));


		PrintWriter writer = new PrintWriter("index.html");
		new HTMLGenerator(writer).generate(contentList);
		writer.close();


	}




}


