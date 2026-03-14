package Sicredi.Teste.application.dto;

import Sicredi.Teste.domain.entity.AgendaEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateAgendaResponse {

    private Long id;

    private String title;

    private String description;

    public static CreateAgendaResponse fromEntity(AgendaEntity entity) {
        return new CreateAgendaResponse(
                entity.getId(),
                entity.getTitle(),
                entity.getDescription()
        );
    }

}
