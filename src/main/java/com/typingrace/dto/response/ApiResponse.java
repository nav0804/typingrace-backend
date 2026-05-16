package com.typingrace.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponse<T> {
    private String message;
    private boolean error;
    private T data;

    public static <T> ApiResponse<T> success(String message, T data){
        return new ApiResponse<>(message, false, data);
    }

    // Helper for quick error responses
    public static <T> ApiResponse<T> fail(String message) {
        return new ApiResponse<>(message, true, null);
    }
}
