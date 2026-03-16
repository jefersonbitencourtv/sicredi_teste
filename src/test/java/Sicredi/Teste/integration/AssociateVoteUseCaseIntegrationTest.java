package Sicredi.Teste.integration;

import Sicredi.Teste.application.dto.AssociateVoteRequest;
import Sicredi.Teste.application.useCase.AssociateVoteUseCase;
import Sicredi.Teste.domain.entity.AgendaEntity;
import Sicredi.Teste.domain.entity.VotingSessionEntity;
import Sicredi.Teste.domain.valueObject.VoteType;
import Sicredi.Teste.infrastructure.persistence.jpa.AgendaJpaRepository;
import Sicredi.Teste.infrastructure.persistence.jpa.VoteJpaRepository;
import Sicredi.Teste.infrastructure.persistence.jpa.VotingSessionJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class AssociateVoteUseCaseIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private AssociateVoteUseCase useCase;

    @Autowired
    private AgendaJpaRepository agendaRepository;

    @Autowired
    private VotingSessionJpaRepository sessionRepository;

    @Autowired
    private VoteJpaRepository voteRepository;

    @Test
    void shouldRegisterVote() {

        AgendaEntity agenda =
                agendaRepository.save(
                        AgendaEntity.createAgenda("Pauta", "Descrição"));

        VotingSessionEntity session =
                sessionRepository.save(
                        VotingSessionEntity.createVotingSession(
                                agenda,
                                LocalDateTime.now().plusMinutes(2)
                        )
                );

        AssociateVoteRequest request =
                new AssociateVoteRequest(
                        session.getId(),
                        "associate-1",
                        VoteType.YES
                );

        useCase.execute(request);

        var vote = voteRepository
                .findByVotingSession_IdAndAssociateId(session.getId(), "associate-1");

        assertTrue(vote.isPresent());
    }
}
