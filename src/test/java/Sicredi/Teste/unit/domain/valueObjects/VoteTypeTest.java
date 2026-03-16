package Sicredi.Teste.unit.domain.valueObjects;

import Sicredi.Teste.domain.exception.VoteTypeInvalidException;
import Sicredi.Teste.domain.valueObject.VoteType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class VoteTypeTest {

    @Test
    void shouldParseYesVote() {

        var vote = VoteType.fromValue("Sim");

        assertEquals(VoteType.YES, vote);
    }

    @Test
    void shouldParseNoVote() {

        var vote = VoteType.fromValue("Não");

        assertEquals(VoteType.NO, vote);
    }

    @Test
    void shouldThrowExceptionForInvalidValue() {

        assertThrows(
                VoteTypeInvalidException.class,
                () -> VoteType.fromValue("Nao")
        );
    }
}
