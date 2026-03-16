package Sicredi.Teste.domain.exception;

public class VoteTypeInvalidException extends RuntimeException{
    public VoteTypeInvalidException(String vote) {
        super("Vote with value " + vote + " is invalid");
    }
}
