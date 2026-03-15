package Sicredi.Teste.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateAgendaRequest (
        @NotBlank @Size(min = 1, max = 50) String title,
        @NotBlank @Size(min = 1, max = 250) String description) {}
