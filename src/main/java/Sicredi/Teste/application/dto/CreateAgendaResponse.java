package Sicredi.Teste.application.dto;

import Sicredi.Teste.domain.entity.AgendaEntity;

public record CreateAgendaResponse(Long id, String title, String description) {
    public static CreateAgendaResponse fromEntity(AgendaEntity entity) {
        return new CreateAgendaResponse(
                entity.getId(),
                entity.getTitle(),
                entity.getDescription()
        );
    }
}
