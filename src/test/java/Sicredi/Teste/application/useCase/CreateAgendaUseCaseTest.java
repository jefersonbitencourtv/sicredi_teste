package Sicredi.Teste.application.useCase;

import Sicredi.Teste.application.dto.CreateAgendaRequest;
import Sicredi.Teste.application.dto.CreateAgendaResponse;
import Sicredi.Teste.domain.entity.AgendaEntity;
import Sicredi.Teste.domain.repository.AgendaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreateAgendaUseCaseTest {

    @Mock
    private AgendaRepository agendaRepository;

    @InjectMocks
    private CreateAgendaUseCase createAgendaUseCase;

    @Test
    void shouldCreateAgenda() {
        CreateAgendaRequest request = new CreateAgendaRequest("Title", "Description");
        AgendaEntity agendaEntity = new AgendaEntity(1L, "Title", "Description");

        when(agendaRepository.createAgenda(any())).thenReturn(agendaEntity);

        CreateAgendaResponse response = createAgendaUseCase.execute(request);

        verify(agendaRepository, times(1)).createAgenda(any());
        assertEquals(request.getTitle(), response.getTitle());
    }

}
