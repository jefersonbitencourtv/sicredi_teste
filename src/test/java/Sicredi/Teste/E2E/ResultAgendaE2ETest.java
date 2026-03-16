package Sicredi.Teste.E2E;

import Sicredi.Teste.domain.entity.AgendaEntity;
import Sicredi.Teste.domain.entity.VoteEntity;
import Sicredi.Teste.domain.entity.VotingSessionEntity;
import Sicredi.Teste.domain.valueObject.VoteType;
import Sicredi.Teste.infrastructure.persistence.jpa.AgendaJpaRepository;
import Sicredi.Teste.infrastructure.persistence.jpa.VoteJpaRepository;
import Sicredi.Teste.infrastructure.persistence.jpa.VotingSessionJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class ResultAgendaE2ETest extends AbstractE2ETest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AgendaJpaRepository agendaRepository;

    @Autowired
    private VotingSessionJpaRepository sessionRepository;

    @Autowired
    private VoteJpaRepository voteRepository;

    @Test
    void shouldReturnResult() throws Exception {

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

        voteRepository.save(VoteEntity.createVote(session,"1", VoteType.YES));
        voteRepository.save(VoteEntity.createVote(session,"2",VoteType.NO));
        voteRepository.save(VoteEntity.createVote(session,"3",VoteType.YES));

        mockMvc.perform(get("/api/v1/agendas/%d/result".formatted(agenda.getId())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.yes_votes").value(2))
                .andExpect(jsonPath("$.no_votes").value(1))
                .andExpect(jsonPath("$.result").value("Aprovado"));
    }
}
