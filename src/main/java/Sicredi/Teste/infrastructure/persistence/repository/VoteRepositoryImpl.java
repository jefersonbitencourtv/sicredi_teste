package Sicredi.Teste.infrastructure.persistence.repository;

import Sicredi.Teste.domain.entity.VoteEntity;
import Sicredi.Teste.domain.repository.VoteRepository;
import Sicredi.Teste.domain.valueObject.VoteType;
import Sicredi.Teste.infrastructure.persistence.jpa.VoteJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class VoteRepositoryImpl implements VoteRepository {

    private final VoteJpaRepository voteJpaRepository;

    @Override
    public Optional<VoteEntity> findByVotingSessionIdAndAssociateId(
            Long votingSessionId,
            String associateId
    ) {
        return voteJpaRepository
                .findByVotingSession_IdAndAssociateId(votingSessionId, associateId);
    }

    @Override
    public VoteEntity createVote(VoteEntity voteEntity) {
        return voteJpaRepository.save(voteEntity);
    }

    @Override
    public long countByVotingSessionIdAndVoteType(Long sessionId, VoteType voteType) {
        return voteJpaRepository.countByVotingSessionIdAndVoteType(sessionId, voteType);
    }
}
