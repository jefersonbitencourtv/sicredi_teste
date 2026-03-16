package Sicredi.Teste.application.dto;

import Sicredi.Teste.domain.valueObject.VoteType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;
import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record AssociateVoteRequest (
        @NotNull Long votingSessionId,
        @Pattern(regexp = "\\d+", message = "associateId must contain only numbers")
        @CPF @Size(min = 11, max = 11) @NotBlank String associateId,
        @NotNull VoteType voteType) {}
