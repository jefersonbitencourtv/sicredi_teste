package Sicredi.Teste.application.useCase;

import Sicredi.Teste.application.dto.ResultAgendaRequest;
import Sicredi.Teste.domain.exception.AlreadyExistsOpenVotingSessionException;
import Sicredi.Teste.domain.repository.VotingSessionRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ResultAgendaUseCase {

    private VotingSessionRepository votingSessionRepository;

    void execute(ResultAgendaRequest request){
        votingSessionRepository.findByAgendaId(request.agendaId())
                .ifPresent(votingSessionEntity -> {
                    throw new AlreadyExistsOpenVotingSessionException(request.agendaId());
                });
    }
}
