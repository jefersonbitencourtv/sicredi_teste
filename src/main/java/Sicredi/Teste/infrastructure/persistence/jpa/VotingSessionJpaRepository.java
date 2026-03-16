package Sicredi.Teste.infrastructure.persistence.jpa;

import Sicredi.Teste.domain.entity.VotingSessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VotingSessionJpaRepository extends JpaRepository<VotingSessionEntity, Long> {

    boolean existsByAgenda_Id(Long agendaId);

    Optional<VotingSessionEntity> findByAgenda_Id(Long agendaId);

}
