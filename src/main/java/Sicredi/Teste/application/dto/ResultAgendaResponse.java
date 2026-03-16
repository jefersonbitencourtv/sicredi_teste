package Sicredi.Teste.application.dto;

import Sicredi.Teste.domain.valueObject.ResultAgenda;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.annotation.JsonNaming;

@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record ResultAgendaResponse (
        @Schema(name = "agenda_id")Long agendaId,
        @Schema(name = "yes_votes")Long yesVotes,
        @Schema(name = "no_votes")Long noVotes,
        ResultAgenda result){}

