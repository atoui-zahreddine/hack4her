package com.example.hackforher.Utils;

import com.example.hackforher.Exception.ErrorResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class Utils {

    public static String convertObjectToJson(ErrorResponse errorResponse) throws JsonProcessingException {
        if (errorResponse == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(errorResponse);
    }
}