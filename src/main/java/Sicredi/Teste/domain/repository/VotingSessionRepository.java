package Sicredi.Teste.domain.repository;

import Sicredi.Teste.application.dto.OpenVotingSessionRequest;
import Sicredi.Teste.domain.entity.VotingSessionEntity;

import java.time.LocalDateTime;
import java.util.Optional;

public interface VotingSessionRepository {

    boolean existsByAgendaIdAndEndTimeAfter(Long agendaId, LocalDateTime endTime);

    VotingSessionEntity createVotingSession(VotingSessionEntity entity);

    Optional<VotingSessionEntity> findById(Long votingSessionId);
}
