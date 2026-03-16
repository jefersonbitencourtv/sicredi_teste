package Sicredi.Teste.unit.domain.entities;

import Sicredi.Teste.domain.entity.AgendaEntity;
import Sicredi.Teste.domain.entity.VotingSessionEntity;
import Sicredi.Teste.domain.exception.DomainException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class VotingSessionEntityTest {

    @Test
    void shouldCreateVotingSession() {

        var agenda = AgendaEntity.createAgenda(
                "Pauta",
                "Descrição"
        );

        var session = VotingSessionEntity.createVotingSession(
                agenda,
                LocalDateTime.now().plusMinutes(5)
        );

        assertNotNull(session);
        assertEquals(agenda, session.getAgenda());
    }

    @Test
    void shouldCreateSessionWithDefaultTime() {

        var agenda = AgendaEntity.createAgenda(
                "Pauta",
                "Descrição"
        );

        var session = VotingSessionEntity.createVotingSession(
                agenda,
                null
        );

        assertNotNull(session.getEndTime());
    }

    @Test
    void shouldThrowExceptionWhenAgendaIsNull() {

        assertThrows(
                DomainException.class,
                () -> VotingSessionEntity.createVotingSession(
                        null,
                        LocalDateTime.now()
                )
        );
    }

    @Test
    void shouldReturnClosedSession() {

        var agenda = AgendaEntity.createAgenda(
                "Pauta",
                "Descrição"
        );

        var session = VotingSessionEntity.createVotingSession(
                agenda,
                LocalDateTime.now().minusMinutes(1)
        );

        assertTrue(session.isClosed());
    }

    @Test
    void shouldReturnOpenSession() {

        var agenda = AgendaEntity.createAgenda(
                "Pauta",
                "Descrição"
        );

        var session = VotingSessionEntity.createVotingSession(
                agenda,
                LocalDateTime.now().plusMinutes(1)
        );

        assertFalse(session.isClosed());
    }
}
