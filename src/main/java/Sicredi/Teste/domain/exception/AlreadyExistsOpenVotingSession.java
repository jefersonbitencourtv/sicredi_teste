package Sicredi.Teste.domain.exception;

public class AlreadyExistsOpenVotingSession extends RuntimeException {
    public AlreadyExistsOpenVotingSession(Long agendaId) {
        super("Agenda com id " + agendaId + " já possui sessão de votação em aberto");
    }
}
