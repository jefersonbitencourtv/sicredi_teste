package Sicredi.Teste.application.useCase;

import Sicredi.Teste.application.dto.CreateAgendaRequest;
import Sicredi.Teste.application.dto.CreateAgendaResponse;
import Sicredi.Teste.domain.entity.AgendaEntity;
import Sicredi.Teste.domain.repository.AgendaRepository;

public class CreateAgendaUseCase {

    private AgendaRepository agendaRepository;

    public CreateAgendaUseCase(AgendaRepository agendaRepository) {
        this.agendaRepository = agendaRepository;
    }

    public CreateAgendaResponse execute(CreateAgendaRequest createAgendaRequest) {
        AgendaEntity entity = AgendaEntity.createAgenda(createAgendaRequest.title(), createAgendaRequest.description());
        AgendaEntity agendaEntity = agendaRepository.createAgenda(entity);
        return CreateAgendaResponse.fromEntity(agendaEntity);

    }

}
