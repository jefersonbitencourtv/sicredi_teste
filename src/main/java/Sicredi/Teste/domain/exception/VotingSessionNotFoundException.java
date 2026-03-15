package Sicredi.Teste.domain.exception;

public class VotingSessionNotFoundException extends RuntimeException{
    public VotingSessionNotFoundException(Long votingSessionId) {
        super("Voting session with id " + votingSessionId + " not found");
    }
}
