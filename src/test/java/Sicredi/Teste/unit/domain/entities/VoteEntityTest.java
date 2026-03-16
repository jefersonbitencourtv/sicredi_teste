package Sicredi.Teste.unit.domain.entities;

import Sicredi.Teste.domain.entity.AgendaEntity;
import Sicredi.Teste.domain.entity.VoteEntity;
import Sicredi.Teste.domain.entity.VotingSessionEntity;
import Sicredi.Teste.domain.exception.DomainException;
import Sicredi.Teste.domain.valueObject.VoteType;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class VoteEntityTest {

    @Test
    void shouldCreateVote() {

        var agenda = AgendaEntity.createAgenda(
                "Pauta",
                "Descrição"
        );

        var session = VotingSessionEntity.createVotingSession(
                agenda,
                LocalDateTime.now().plusMinutes(1)
        );

        var vote = VoteEntity.createVote(
                session,
                "associate-1",
                VoteType.YES
        );

        assertNotNull(vote);
        assertEquals("associate-1", vote.getAssociateId());
        assertEquals(VoteType.YES, vote.getVoteType());
    }

    @Test
    void shouldThrowExceptionWhenSessionIsNull() {

        assertThrows(
                DomainException.class,
                () -> VoteEntity.createVote(
                        null,
                        "associate-1",
                        VoteType.YES
                )
        );
    }

    @Test
    void shouldThrowExceptionWhenAssociateIdIsBlank() {

        var agenda = AgendaEntity.createAgenda(
                "Pauta",
                "Descrição"
        );

        var session = VotingSessionEntity.createVotingSession(
                agenda,
                LocalDateTime.now().plusMinutes(1)
        );

        assertThrows(
                DomainException.class,
                () -> VoteEntity.createVote(
                        session,
                        "",
                        VoteType.YES
                )
        );
    }

    @Test
    void shouldThrowExceptionWhenVoteTypeIsNull() {

        var agenda = AgendaEntity.createAgenda(
                "Pauta",
                "Descrição"
        );

        var session = VotingSessionEntity.createVotingSession(
                agenda,
                LocalDateTime.now().plusMinutes(1)
        );

        assertThrows(
                DomainException.class,
                () -> VoteEntity.createVote(
                        session,
                        "associate-1",
                        null
                )
        );
    }
}
