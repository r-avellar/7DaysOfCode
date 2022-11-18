package com.ravellar.SevenDaysOfCode.entities;

public class Movie {

    private String title;
    private  String urlImage;
    private String year;
    private String rating;

    public Movie(String title, String urlImage, String year, String rating) {
        this.title = title;
        this.urlImage = urlImage;
        this.year = year;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String id) {
        this.title = id;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return          "title = " + title + "\n" +
                        "url = " + urlImage + "\n" +
                        "year = " + year + "\n" +
                        "rating = " + rating + "\n";
    }
}
