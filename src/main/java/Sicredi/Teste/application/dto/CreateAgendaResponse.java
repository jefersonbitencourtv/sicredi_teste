package Sicredi.Teste.application.dto;

import Sicredi.Teste.domain.entity.AgendaEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateAgendaResponse(
        @NotNull Long id,
        @NotBlank @Size(min = 1, max = 50) String title,
        @NotBlank @Size(min = 1, max = 250)String description) {
    public static CreateAgendaResponse fromEntity(AgendaEntity entity) {
        return new CreateAgendaResponse(
                entity.getId(),
                entity.getTitle(),
                entity.getDescription()
        );
    }
}
