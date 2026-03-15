package Sicredi.Teste.presentation.controller.v1.exceptions.dto;

import java.time.LocalDateTime;

public record ErrorResponse(int status, String message, LocalDateTime timestamp) {}
