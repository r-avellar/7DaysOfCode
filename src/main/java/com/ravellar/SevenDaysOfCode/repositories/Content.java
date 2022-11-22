package com.ravellar.SevenDaysOfCode.repositories;

public interface Content extends Comparable<Content>{
    String title();
    String urlImage();
    String rating();
    String year();
}
