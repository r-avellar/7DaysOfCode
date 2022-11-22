package com.ravellar.SevenDaysOfCode.entities;

import com.ravellar.SevenDaysOfCode.repositories.Content;
import org.jetbrains.annotations.NotNull;

public record Movie(String title, String urlImage, String rating, String year) implements Content{
    @Override
    public int compareTo(@NotNull Content content) {
        return this.rating.compareTo(content.rating());
    }
}


