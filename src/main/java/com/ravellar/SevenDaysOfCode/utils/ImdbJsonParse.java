package com.ravellar.SevenDaysOfCode.utils;

import com.ravellar.SevenDaysOfCode.entities.Movie;

import com.ravellar.SevenDaysOfCode.repositories.JsonParser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;



public class ImdbJsonParse implements JsonParser {

    private String json;

    public ImdbJsonParse(String json){
        this.json = json;
    }

    public List<Movie> parse(){
        String[] moviesArray = parseJsonMovies(json);


        List<String> title = parseTitle(moviesArray);
        List<String> urlImg = parseUrlImg(moviesArray);
        List<String> rating = parseRating(moviesArray);
        List<String> year = parseYear(moviesArray);

        List<Movie> movies = new ArrayList<>();

        for(int i = 0; i < moviesArray.length; i++){
            movies.add(new Movie(title.get(i), urlImg.get(i), year.get(i), rating.get(i)));
        }

        return movies;
    }

    private static List<String> parseUrlImg(String[] movies) {
        return parseAtributes(movies,5);
    }

    private static List<String> parseTitle(String[] movies) {
        return parseAtributes(movies, 3);
    }

    private static List<String> parseYear(String[] movies) {
        return parseAtributes(movies,4);
    }

    private static List<String> parseRating(String[] movies) {
        return parseAtributes(movies, 7);
    }

    private static List<String> parseAtributes(String[] movies, int pos) {
        return Stream.of(movies)
                .map(e -> e.split("\",\"")[pos])
                .map(e -> e.split(":\"")[1])
                .map(e -> e.replaceAll("\"", ""))
                .collect(Collectors.toList());
    }

    public static String[] parseJsonMovies(String json) {
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
