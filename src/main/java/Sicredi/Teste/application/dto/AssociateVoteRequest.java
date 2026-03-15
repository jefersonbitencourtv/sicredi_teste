package Sicredi.Teste.application.dto;

import Sicredi.Teste.domain.valueObject.VoteType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AssociateVoteRequest (
        @NotNull Long votingSessionId,
        @NotBlank String associateId,
        @NotNull VoteType voteType) {}
