package Sicredi.Teste.integration;

import Sicredi.Teste.application.dto.CreateAgendaRequest;
import Sicredi.Teste.application.dto.CreateAgendaResponse;
import Sicredi.Teste.application.useCase.CreateAgendaUseCase;
import Sicredi.Teste.infrastructure.persistence.jpa.AgendaJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CreateAgendaUseCaseIntegrationTest  extends AbstractIntegrationTest {

    @Autowired
    private CreateAgendaUseCase createAgendaUseCase;

    @Autowired
    private AgendaJpaRepository agendaJpaRepository;

    @Test
    void shouldCreateAgenda() {

        CreateAgendaRequest request =
                new CreateAgendaRequest("Nova pauta", "Descrição da pauta");

        CreateAgendaResponse response = createAgendaUseCase.execute(request);

        assertNotNull(response);
        assertNotNull(response.id());

        var agenda = agendaJpaRepository.findById(response.id());

        assertTrue(agenda.isPresent());
        assertEquals("Nova pauta", agenda.get().getTitle());
    }
}