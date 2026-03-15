package Sicredi.Teste.application.useCase;

import Sicredi.Teste.application.dto.AssociateVoteRequest;
import Sicredi.Teste.domain.entity.VotingSessionEntity;
import Sicredi.Teste.domain.exception.VotingSessionNotFoundException;
import Sicredi.Teste.domain.repository.VotingSessionRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AssociateVoteUseCase {

    private VotingSessionRepository votingSessionRepository;



    public void execute(AssociateVoteRequest request){
        VotingSessionEntity votingSession = votingSessionRepository.findById(request.votingSessionId())
                .orElseThrow(()-> new VotingSessionNotFoundException(request.votingSessionId()));
    }
}
