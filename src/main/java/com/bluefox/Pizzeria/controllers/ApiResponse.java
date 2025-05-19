package com.bluefox.Pizzeria.controllers;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
public class ApiResponse<T> {
    private String status;
    private Integer length;
    private T data;

    public ApiResponse(String status, T data) {
        this.status = status;
        this.length = 1;
        this.data = data;
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>("success", data);
    }

    public static <T> ApiResponse<T> successWithCount(T data, int length) {
        return new ApiResponse<>("success", length, data);
    }

    public static <T> ApiResponse<Map<String, T>> error(T message) {
        Map<String, T> errorData = new HashMap<>();
        errorData.put("message", message);

        ApiResponse<Map<String, T>> response = new ApiResponse<>("error", errorData);
        response.length = 0;

        return response;
    }
}
