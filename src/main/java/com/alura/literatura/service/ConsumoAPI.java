package com.alura.literatura.service;

import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class ConsumoAPI {
    private final HttpClient client = HttpClient.newHttpClient();

    public String obtenerDatos(String url){
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            return response.body();
        }catch (Exception e){
            throw new RuntimeException("Error al consumir API: " + e.getMessage());
        }
    }
}
