package Sicredi.Teste.application.useCase;

import Sicredi.Teste.application.dto.AssociateVoteRequest;
import Sicredi.Teste.domain.entity.VotingSessionEntity;
import Sicredi.Teste.domain.exception.AssociateAlreadyVotedInThisVotingSessionException;
import Sicredi.Teste.domain.exception.VotingSessionIsClosedException;
import Sicredi.Teste.domain.exception.VotingSessionNotFoundException;
import Sicredi.Teste.domain.repository.VoteRepository;
import Sicredi.Teste.domain.repository.VotingSessionRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AssociateVoteUseCase {

    private VotingSessionRepository votingSessionRepository;

    private VoteRepository voteRepository;



    public void execute(AssociateVoteRequest request){
        VotingSessionEntity votingSession = votingSessionRepository.findById(request.votingSessionId())
                .orElseThrow(()-> new VotingSessionNotFoundException(request.votingSessionId()));

        if (votingSession.isClosed()){
            throw new VotingSessionIsClosedException(request.votingSessionId());
        }

        voteRepository.findByVotingSessionIdAndAssociateId(request.votingSessionId(), request.associateId())
                .ifPresent(vote -> {
                    throw new AssociateAlreadyVotedInThisVotingSessionException(
                            request.votingSessionId(),
                            request.associateId()
                    );
                });

    }
}
