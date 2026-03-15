package Sicredi.Teste.application.dto;

import jakarta.validation.constraints.NotNull;

public record ResultAgendaRequest(@NotNull Long agendaId) {
}
