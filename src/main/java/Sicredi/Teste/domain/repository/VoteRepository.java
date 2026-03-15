package Sicredi.Teste.domain.repository;

import Sicredi.Teste.domain.entity.VoteEntity;
import Sicredi.Teste.domain.valueObject.VoteType;

import java.util.Optional;

public interface VoteRepository {

    Optional<VoteEntity> findByVotingSessionIdAndAssociateId(Long votingSessionId, String associateId);

    VoteEntity createVote(VoteEntity voteEntity);

    long countByVotingSessionIdAndVoteType(Long sessionId, VoteType voteType);
}
