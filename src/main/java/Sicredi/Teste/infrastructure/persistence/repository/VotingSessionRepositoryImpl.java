package Sicredi.Teste.infrastructure.persistence.repository;

import Sicredi.Teste.domain.entity.VotingSessionEntity;
import Sicredi.Teste.domain.repository.VotingSessionRepository;
import Sicredi.Teste.infrastructure.persistence.jpa.VotingSessionJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class VotingSessionRepositoryImpl implements VotingSessionRepository {

    private final VotingSessionJpaRepository jpaRepository;

    @Override
    public boolean existsByAgendaId(Long agendaId) {
        return jpaRepository.existsByAgendaId(agendaId);
    }

    @Override
    public VotingSessionEntity createVotingSession(VotingSessionEntity entity) {
        return jpaRepository.save(entity);
    }

    @Override
    public Optional<VotingSessionEntity> findById(Long votingSessionId) {
        return jpaRepository.findById(votingSessionId);
    }

    @Override
    public Optional<VotingSessionEntity> findByAgendaId(Long agendaId) {
        return jpaRepository.findByAgendaId(agendaId);
    }
}
