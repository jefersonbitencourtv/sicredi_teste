package Sicredi.Teste.E2E;

import Sicredi.Teste.domain.entity.AgendaEntity;
import Sicredi.Teste.infrastructure.persistence.jpa.AgendaJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class OpenVotingSessionE2ETest extends AbstractE2ETest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AgendaJpaRepository agendaRepository;

    @Test
    void shouldOpenVotingSession() throws Exception {

        AgendaEntity agenda =
                agendaRepository.save(
                        AgendaEntity.createAgenda("Pauta", "Descrição"));

        String body = """
            {
              "agenda_id": %d,
              "end_time": "2030-01-01T10:00:00"
            }
            """.formatted(agenda.getId());

        mockMvc.perform(post("/api/v1/voting-sessions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.agenda_id").value(agenda.getId()));
    }
}
