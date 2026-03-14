package Sicredi.Teste.application.useCase;

import Sicredi.Teste.application.dto.OpenVotingSessionRequest;
import Sicredi.Teste.application.dto.OpenVotingSessionResponse;
import Sicredi.Teste.domain.exception.AgendaNotFoundException;
import Sicredi.Teste.domain.repository.AgendaRepository;

public class OpenVotingSessionUseCase {

    private AgendaRepository agendaRepository;

    public OpenVotingSessionResponse execute(OpenVotingSessionRequest request) {

        agendaRepository.buscarAgenda(request.getAgendaId())
                .orElseThrow(() -> new AgendaNotFoundException(request.getAgendaId()));

        return null;
    }
}
