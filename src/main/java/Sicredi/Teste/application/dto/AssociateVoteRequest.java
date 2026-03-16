package Sicredi.Teste.application.dto;

import Sicredi.Teste.domain.valueObject.VoteType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;
import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record AssociateVoteRequest (
        @Schema(name = "voting_session_id") @NotNull(message = "voting_session_id is mandatory") Long votingSessionId,
        @Schema(name = "associate_id")
        @Pattern(regexp = "\\d+", message = "associateId must contain only numbers")
        @CPF @Size(min = 11, max = 11, message = "Invalid cpf") @NotBlank(message = "associate_id is mandatory and not blank") String associateId,
        @Schema(name = "vote_type") @NotNull(message = "vote_type is mandatory") VoteType voteType) {}
