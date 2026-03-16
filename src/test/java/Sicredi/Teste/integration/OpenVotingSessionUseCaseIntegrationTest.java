package Sicredi.Teste.integration;

import Sicredi.Teste.application.dto.OpenVotingSessionRequest;
import Sicredi.Teste.application.dto.OpenVotingSessionResponse;
import Sicredi.Teste.application.useCase.OpenVotingSessionUseCase;
import Sicredi.Teste.domain.entity.AgendaEntity;
import Sicredi.Teste.infrastructure.persistence.jpa.AgendaJpaRepository;
import Sicredi.Teste.infrastructure.persistence.jpa.VotingSessionJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class OpenVotingSessionUseCaseIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private OpenVotingSessionUseCase useCase;

    @Autowired
    private AgendaJpaRepository agendaRepository;

    @Autowired
    private VotingSessionJpaRepository sessionRepository;

    @Test
    void shouldOpenVotingSession() {

        AgendaEntity agenda =
                agendaRepository.save(
                        AgendaEntity.createAgenda("Pauta", "Descrição"));

        OpenVotingSessionRequest request =
                new OpenVotingSessionRequest(
                        agenda.getId(),
                        LocalDateTime.now().plusMinutes(2)
                );

        OpenVotingSessionResponse response = useCase.execute(request);

        assertNotNull(response.id());

        var session = sessionRepository.findById(response.id());

        assertTrue(session.isPresent());
        assertEquals(agenda.getId(), session.get().getAgenda().getId());
    }
}