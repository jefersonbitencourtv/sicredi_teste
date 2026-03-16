package Sicredi.Teste.integration;

import Sicredi.Teste.application.dto.ResultAgendaRequest;
import Sicredi.Teste.application.dto.ResultAgendaResponse;
import Sicredi.Teste.application.useCase.ResultAgendaUseCase;
import Sicredi.Teste.domain.entity.AgendaEntity;
import Sicredi.Teste.domain.entity.VoteEntity;
import Sicredi.Teste.domain.entity.VotingSessionEntity;
import Sicredi.Teste.domain.valueObject.ResultAgenda;
import Sicredi.Teste.domain.valueObject.VoteType;
import Sicredi.Teste.infrastructure.persistence.jpa.AgendaJpaRepository;
import Sicredi.Teste.infrastructure.persistence.jpa.VoteJpaRepository;
import Sicredi.Teste.infrastructure.persistence.jpa.VotingSessionJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ResultAgendaUseCaseIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private ResultAgendaUseCase useCase;

    @Autowired
    private AgendaJpaRepository agendaRepository;

    @Autowired
    private VotingSessionJpaRepository sessionRepository;

    @Autowired
    private VoteJpaRepository voteRepository;

    @Test
    void shouldReturnVotingResult() {

        AgendaEntity agenda =
                agendaRepository.save(
                        AgendaEntity.createAgenda("Pauta", "Descrição"));

        VotingSessionEntity session =
                sessionRepository.save(
                        VotingSessionEntity.createVotingSession(
                                agenda,
                                LocalDateTime.now().minusMinutes(1)
                        )
                );

        voteRepository.save(
                VoteEntity.createVote(session, "1", VoteType.YES));

        voteRepository.save(
                VoteEntity.createVote(session, "2", VoteType.NO));

        voteRepository.save(
                VoteEntity.createVote(session, "3", VoteType.YES));

        ResultAgendaResponse response =
                useCase.execute(new ResultAgendaRequest(agenda.getId()));

        assertEquals(2, response.yesVotes());
        assertEquals(1, response.noVotes());
        assertEquals(ResultAgenda.APPROVED, response.result());
    }
}
