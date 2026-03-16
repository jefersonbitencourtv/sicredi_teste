package Sicredi.Teste.domain.exception;

public class AssociateAlreadyVotedInThisVotingSessionException extends RuntimeException{
    public AssociateAlreadyVotedInThisVotingSessionException(Long votingSession, String associateId) {
        super("Associate with id " + associateId.substring(associateId.length() - 4) + " already voted in this voting session with id " + votingSession);
    }
}
