package dev.matheuscruz.infra.resources.data;

public record ErrorResponse(String message) {

    public static ErrorResponse of(String message) {
        return new ErrorResponse(message);
    }
}