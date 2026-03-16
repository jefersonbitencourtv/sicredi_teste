package Sicredi.Teste.E2E;

import Sicredi.Teste.domain.entity.AgendaEntity;
import Sicredi.Teste.domain.entity.VotingSessionEntity;
import Sicredi.Teste.infrastructure.persistence.jpa.AgendaJpaRepository;
import Sicredi.Teste.infrastructure.persistence.jpa.VotingSessionJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class VoteE2ETest extends AbstractE2ETest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AgendaJpaRepository agendaRepository;

    @Autowired
    private VotingSessionJpaRepository sessionRepository;

    @Test
    void shouldVote() throws Exception {

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

        String body = """
            {
              "voting_session_id": %d,
              "associate_id": "67908938060",
              "vote_type": "Sim"
            }
            """.formatted(session.getId());

        mockMvc.perform(post("/api/v1/votes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk());
    }
}
