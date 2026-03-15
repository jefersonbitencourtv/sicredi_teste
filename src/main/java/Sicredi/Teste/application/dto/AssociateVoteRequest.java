package Sicredi.Teste.application.dto;

import Sicredi.Teste.domain.valueObject.VoteType;

public record AssociateVoteRequest (Long votingSessionId, String associateId, VoteType voteType) {}
