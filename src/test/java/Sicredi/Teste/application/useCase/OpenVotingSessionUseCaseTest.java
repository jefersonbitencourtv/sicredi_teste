package Sicredi.Teste.application.useCase;

import Sicredi.Teste.application.dto.OpenVotingSessionRequest;
import Sicredi.Teste.domain.exception.AgendaNotFoundException;
import Sicredi.Teste.domain.repository.AgendaRepository;
import Sicredi.Teste.domain.repository.VotingSessionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OpenVotingSessionUseCaseTest {

    @Mock
    private AgendaRepository agendaRepository;

    @Mock
    private VotingSessionRepository votingSessionRepository;

    @InjectMocks
    private OpenVotingSessionUseCase openVotingSessionUseCase;

    @Test
    void shouldThrowExceptionWhenAgendaNotFound() {
        OpenVotingSessionRequest request = new OpenVotingSessionRequest(1L, LocalDateTime.now());
        when(agendaRepository.buscarAgenda(anyLong())).thenReturn(Optional.empty());

        assertThrows(AgendaNotFoundException.class, () -> openVotingSessionUseCase.execute(request));

        verify(agendaRepository, times(1)).buscarAgenda(anyLong());
    }

}
