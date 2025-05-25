package com.bluefox.Pizzeria.controllers;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
public class ApiResponses<T> {
    private String status;
    private Integer length;
    private T data;

    public ApiResponses(String status, T data) {
        this.status = status;
        this.length = 1;
        this.data = data;
    }

    public static <T> ApiResponses<T> success(T data) {
        return new ApiResponses<>("success", data);
    }

    public static <T> ApiResponses<T> successWithCount(T data, int length) {
        return new ApiResponses<>("success", length, data);
    }

    public static <T> ApiResponses<Map<String, T>> error(T message) {
        Map<String, T> errorData = new HashMap<>();
        errorData.put("message", message);

        ApiResponses<Map<String, T>> response = new ApiResponses<>("error", errorData);
        response.length = 0;

        return response;
    }
}
