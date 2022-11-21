package com.ravellar.SevenDaysOfCode.repositories;

import java.io.IOException;

public interface ApiClient {
    String getBody() throws IOException, InterruptedException;
}
