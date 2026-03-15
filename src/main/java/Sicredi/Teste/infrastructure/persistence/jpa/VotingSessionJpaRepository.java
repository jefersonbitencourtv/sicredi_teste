package Sicredi.Teste.infrastructure.persistence.jpa;

import Sicredi.Teste.domain.entity.VotingSessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VotingSessionJpaRepository extends JpaRepository<VotingSessionEntity, Long> {

    boolean existsByAgendaId(Long agendaId);

    Optional<VotingSessionEntity> findByAgendaId(Long agendaId);

}
