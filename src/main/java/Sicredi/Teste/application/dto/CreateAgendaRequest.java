package Sicredi.Teste.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateAgendaRequest (
        @NotBlank(message = "title is mandatory and not blank") @Size(min = 1, max = 50) String title,
        @NotBlank(message = "description is mandatory and not blank") @Size(min = 1, max = 250) String description) {}
