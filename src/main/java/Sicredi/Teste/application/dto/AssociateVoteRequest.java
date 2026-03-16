package Sicredi.Teste.application.dto;

import Sicredi.Teste.domain.valueObject.VoteType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record AssociateVoteRequest (
        @NotNull Long votingSessionId,
        @NotBlank String associateId,
        @NotNull VoteType voteType) {}
