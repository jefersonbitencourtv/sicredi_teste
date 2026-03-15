package Sicredi.Teste.application.useCase;

import Sicredi.Teste.application.dto.ResultAgendaRequest;
import Sicredi.Teste.domain.entity.VotingSessionEntity;
import Sicredi.Teste.domain.exception.AlreadyExistsOpenVotingSessionException;
import Sicredi.Teste.domain.exception.VotingSessionNotFoundException;
import Sicredi.Teste.domain.repository.VotingSessionRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ResultAgendaUseCase {

    private VotingSessionRepository votingSessionRepository;

    void execute(ResultAgendaRequest request){
        VotingSessionEntity session = votingSessionRepository
                .findByAgendaId(request.agendaId())
                .orElseThrow(() -> new VotingSessionNotFoundException(request.agendaId()));

        if (!session.isClosed()) {
            throw new AlreadyExistsOpenVotingSessionException(request.agendaId());
        }
    }
}
