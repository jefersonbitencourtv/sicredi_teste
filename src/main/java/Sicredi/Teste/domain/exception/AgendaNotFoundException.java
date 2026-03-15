package Sicredi.Teste.domain.exception;

public class AgendaNotFoundException extends RuntimeException{
    public AgendaNotFoundException(Long agendaId) {
        super("Agenda with id " + agendaId + " not found");
    }
}
