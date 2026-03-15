package Sicredi.Teste.infrastructure.persistence.jpa;

import Sicredi.Teste.domain.entity.AgendaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgendaJpaRepository extends JpaRepository<AgendaEntity, Long> {
}
