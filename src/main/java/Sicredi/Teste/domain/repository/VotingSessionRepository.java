package Sicredi.Teste.domain.repository;

import java.time.LocalDateTime;

public interface VotingSessionRepository {

    boolean existsByAgendaIdAndEndTimeAfter(Long agendaId, LocalDateTime endTime);

}
