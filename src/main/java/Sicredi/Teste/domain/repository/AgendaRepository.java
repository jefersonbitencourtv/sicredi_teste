package Sicredi.Teste.domain.repository;

import Sicredi.Teste.domain.entity.AgendaEntity;

import java.util.Optional;

public interface AgendaRepository {

    AgendaEntity createAgenda(AgendaEntity agendaEntity);

    Optional<AgendaEntity> findAgenda(Long id);

}
