package Sicredi.Teste.application.useCase;

import Sicredi.Teste.application.dto.OpenVotingSessionRequest;
import Sicredi.Teste.application.dto.OpenVotingSessionResponse;
import Sicredi.Teste.domain.entity.AgendaEntity;
import Sicredi.Teste.domain.entity.VotingSessionEntity;
import Sicredi.Teste.domain.exception.AgendaNotFoundException;
import Sicredi.Teste.domain.exception.AlreadyExistsOpenVotingSessionException;
import Sicredi.Teste.domain.repository.AgendaRepository;
import Sicredi.Teste.domain.repository.VotingSessionRepository;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
public class OpenVotingSessionUseCase {

    private AgendaRepository agendaRepository;

    private VotingSessionRepository votingSessionRepository;

    public OpenVotingSessionResponse execute(OpenVotingSessionRequest request) {

        AgendaEntity agendaEntity = agendaRepository.findAgenda(request.agendaId())
                .orElseThrow(() -> new AgendaNotFoundException(request.agendaId()));

        validateIfSessionAlreadyOpen(request.agendaId());

        VotingSessionEntity entity = VotingSessionEntity.createVotingSession(agendaEntity, request.endTime());
        var entityDB = votingSessionRepository.createVotingSession(entity);

        return OpenVotingSessionResponse.fromEntity(entityDB);
    }

    private void validateIfSessionAlreadyOpen(Long agendaId) {
        if (votingSessionRepository.existsByAgendaId(agendaId)) {
            throw new AlreadyExistsOpenVotingSessionException(agendaId);
        }
    }
}
