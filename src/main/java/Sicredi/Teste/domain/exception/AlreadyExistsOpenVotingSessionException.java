package Sicredi.Teste.domain.exception;

public class AlreadyExistsOpenVotingSessionException extends RuntimeException {
    public AlreadyExistsOpenVotingSessionException(Long agendaId) {
        super("Agenda with id " + agendaId + " already exists voting session");
    }
}
