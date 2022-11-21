package com.ravellar.SevenDaysOfCode.entities;

import com.ravellar.SevenDaysOfCode.repositories.Content;

public record Movie(String title, String urlImage, String rating, String year) implements Content{}


