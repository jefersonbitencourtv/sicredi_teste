package Sicredi.Teste.domain.repository;

import Sicredi.Teste.application.dto.CreateAgendaRequest;
import Sicredi.Teste.domain.entity.AgendaEntity;

public interface AgendaRepository {

    AgendaEntity createAgenda(CreateAgendaRequest createAgendaRequest);

}
