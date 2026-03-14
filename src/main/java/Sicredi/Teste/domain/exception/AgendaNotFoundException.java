package Sicredi.Teste.domain.exception;

public class AgendaNotFoundException extends RuntimeException{
    public AgendaNotFoundException(Long agendaId) {
        super("Agenda com id " + agendaId + " não encontrada");
    }
}
