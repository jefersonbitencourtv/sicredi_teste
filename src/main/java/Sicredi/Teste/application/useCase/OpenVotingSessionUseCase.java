package Sicredi.Teste.application.useCase;

import Sicredi.Teste.application.dto.OpenVotingSessionRequest;
import Sicredi.Teste.application.dto.OpenVotingSessionResponse;
import Sicredi.Teste.domain.exception.AgendaNotFoundException;
import Sicredi.Teste.domain.exception.AlreadyExistsOpenVotingSession;
import Sicredi.Teste.domain.repository.AgendaRepository;
import Sicredi.Teste.domain.repository.VotingSessionRepository;

import java.time.LocalDateTime;

public class OpenVotingSessionUseCase {

    private AgendaRepository agendaRepository;

    private VotingSessionRepository votingSessionRepository;

    public OpenVotingSessionResponse execute(OpenVotingSessionRequest request) {

        agendaRepository.findAgenda(request.getAgendaId())
                .orElseThrow(() -> new AgendaNotFoundException(request.getAgendaId()));

        validateIfSessionAlreadyOpen(request.getAgendaId(), request.getEndTime());

        return null;
    }

    private void validateIfSessionAlreadyOpen(Long agendaId, LocalDateTime endTime) {
        if (votingSessionRepository.existsByAgendaIdAndEndTimeAfter(agendaId, endTime)) {
            throw new AlreadyExistsOpenVotingSession(agendaId);
        }
    }
}
