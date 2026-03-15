package Sicredi.Teste.domain.repository;

import Sicredi.Teste.application.dto.CreateAgendaRequest;
import Sicredi.Teste.domain.entity.AgendaEntity;

import java.util.Optional;

public interface AgendaRepository {

    AgendaEntity createAgenda(CreateAgendaRequest createAgendaRequest);

    Optional<AgendaEntity> findAgenda(Long id);


}
