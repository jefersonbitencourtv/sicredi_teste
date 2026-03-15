package Sicredi.Teste.application.useCase;

import Sicredi.Teste.application.dto.ResultAgendaRequest;
import Sicredi.Teste.application.dto.ResultAgendaResponse;
import Sicredi.Teste.domain.entity.VotingSessionEntity;
import Sicredi.Teste.domain.exception.AlreadyExistsOpenVotingSessionException;
import Sicredi.Teste.domain.exception.VotingSessionNotFoundException;
import Sicredi.Teste.domain.repository.VoteRepository;
import Sicredi.Teste.domain.repository.VotingSessionRepository;
import Sicredi.Teste.domain.valueObject.ResultAgenda;
import Sicredi.Teste.domain.valueObject.VoteType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ResultAgendaUseCase {

    private VotingSessionRepository votingSessionRepository;

    private VoteRepository voteRepository;

    public ResultAgendaResponse execute(ResultAgendaRequest request){
        VotingSessionEntity session = votingSessionRepository
                .findByAgendaId(request.agendaId())
                .orElseThrow(() -> new VotingSessionNotFoundException(request.agendaId()));

        if (!session.isClosed()) {
            throw new AlreadyExistsOpenVotingSessionException(request.agendaId());
        }

        long yesVotes = voteRepository.countByVotingSessionIdAndVoteType(session.getId(), VoteType.YES);
        long noVotes = voteRepository.countByVotingSessionIdAndVoteType(session.getId(), VoteType.NO);
        ResultAgenda result = calculateResult(yesVotes, noVotes);

        return ResultAgendaResponse.builder()
                .agendaId(request.agendaId())
                .noVotes(noVotes)
                .yesVotes(yesVotes)
                .result(result)
                .build();
    }

    private ResultAgenda calculateResult(long yesVotes, long noVotes) {
        if (yesVotes > noVotes) {
            return ResultAgenda.APPROVED;
        }

        if (noVotes > yesVotes) {
            return ResultAgenda.REFUSED;
        }

        return ResultAgenda.DRAW;
    }
}
