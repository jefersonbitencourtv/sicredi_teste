package Sicredi.Teste.domain.exception;

public class AlreadyExistsOpenVotingSession extends RuntimeException {
    public AlreadyExistsOpenVotingSession(Long agendaId) {
        super("Agenda with id " + agendaId + " already exists open voting session");
    }
}
