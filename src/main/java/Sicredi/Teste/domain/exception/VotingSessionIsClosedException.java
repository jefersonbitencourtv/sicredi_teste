package Sicredi.Teste.domain.exception;

public class VotingSessionIsClosedException extends RuntimeException{
    public VotingSessionIsClosedException(Long votingSession) {
        super("Voting session with id " + votingSession + " is closed");
    }
}
