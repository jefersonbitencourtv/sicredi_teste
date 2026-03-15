package Sicredi.Teste.application.useCase;

import Sicredi.Teste.application.dto.OpenVotingSessionRequest;
import Sicredi.Teste.application.dto.OpenVotingSessionResponse;
import Sicredi.Teste.domain.exception.AgendaNotFoundException;
import Sicredi.Teste.domain.exception.AlreadyExistsOpenVotingSessionException;
import Sicredi.Teste.domain.repository.AgendaRepository;
import Sicredi.Teste.domain.repository.VotingSessionRepository;

import java.time.LocalDateTime;

public class OpenVotingSessionUseCase {

    private AgendaRepository agendaRepository;

    private VotingSessionRepository votingSessionRepository;

    public OpenVotingSessionResponse execute(OpenVotingSessionRequest request) {

        agendaRepository.findAgenda(request.agendaId())
                .orElseThrow(() -> new AgendaNotFoundException(request.agendaId()));

        validateIfSessionAlreadyOpen(request.agendaId(), request.endTime());

        var entity = votingSessionRepository.createVotingSession(request);

        return OpenVotingSessionResponse.fromEntity(entity);
    }

    private void validateIfSessionAlreadyOpen(Long agendaId, LocalDateTime endTime) {
        if (votingSessionRepository.existsByAgendaIdAndEndTimeAfter(agendaId, endTime)) {
            throw new AlreadyExistsOpenVotingSessionException(agendaId);
        }
    }
}
