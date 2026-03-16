package Sicredi.Teste.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record ResultAgendaRequest(
        @Schema(name = "agenda_id") @NotNull(message = "agenda_id is mandatory") Long agendaId) {
}
