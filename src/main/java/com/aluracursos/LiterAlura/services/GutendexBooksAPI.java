package com.aluracursos.LiterAlura.services;

import com.aluracursos.LiterAlura.model.Data;
import com.aluracursos.LiterAlura.model.DataBook;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.Charset;
import java.util.List;

public class GutendexBooksAPI {
    private final String URL_BASE = "https://gutendex.com/books/";

    private final HttpClient client = HttpClient.newHttpClient();

    public List<DataBook> searchByTitle(String title) {
        var json = this.callAPI(URL_BASE + "?search=" + URLEncoder.encode(title, Charset.defaultCharset()));
        var data = this.parseJson(json, Data.class);

        return data.results();
    }

    public List<DataBook> searchByDeath (Integer birthDate, Integer deathDate) {
        var json = this.callAPI(URL_BASE + "?author_year_start=" + birthDate + "&?author_year_end=" + deathDate);
        var data = this.parseJson(json, Data.class);
        return data.results();
    }

    public List<DataBook> searchByLanguage(String language) {
        var json = this.callAPI(URL_BASE + "?language=" + language);
        var data = this.parseJson(json, Data.class);
        return data.results();
    }

    private String callAPI(String url) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response;

        try {
            response = this.client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        return response.body();
    }

    private <T> T parseJson(String json, Class<T> dataClass) {
        try {
            return new ObjectMapper().readValue(json, dataClass);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
