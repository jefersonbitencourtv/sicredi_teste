package Sicredi.Teste.infrastructure.persistence.repository;

import Sicredi.Teste.domain.entity.AgendaEntity;
import Sicredi.Teste.domain.repository.AgendaRepository;
import Sicredi.Teste.infrastructure.persistence.jpa.AgendaJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class AgendaRepositoryImpl implements AgendaRepository {

    private final AgendaJpaRepository agendaJpaRepository;

    @Override
    public AgendaEntity createAgenda(AgendaEntity entity) {
        return agendaJpaRepository.save(entity);
    }

    @Override
    public Optional<AgendaEntity> findAgenda(Long id) {
        return agendaJpaRepository.findById(id);
    }
}
