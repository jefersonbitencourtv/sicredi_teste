package Sicredi.Teste.application.dto;

import Sicredi.Teste.domain.valueObject.ResultAgenda;
import lombok.Builder;
import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.annotation.JsonNaming;

@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record ResultAgendaResponse (Long agendaId, Long yesVotes, Long noVotes, ResultAgenda result){}

