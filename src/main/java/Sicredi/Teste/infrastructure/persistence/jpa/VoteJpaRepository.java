package Sicredi.Teste.infrastructure.persistence.jpa;

import Sicredi.Teste.domain.entity.VoteEntity;
import Sicredi.Teste.domain.valueObject.VoteType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface VoteJpaRepository extends JpaRepository<VoteEntity, UUID> {

    Optional<VoteEntity> findByVotingSessionIdAndAssociateId(Long votingSessionId, String associateId);

    long countByVotingSessionIdAndVoteType(Long sessionId, VoteType voteType);

}
