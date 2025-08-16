package com.alura.literatura.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class ConvierteDatos {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public <T> T convertir(String json, Class<T> clase){
        try {
            return objectMapper.readValue(json,clase);
        }catch (Exception e){
            throw new RuntimeException("Error al convertir JSON: " + e.getMessage());
        }
    }
}
