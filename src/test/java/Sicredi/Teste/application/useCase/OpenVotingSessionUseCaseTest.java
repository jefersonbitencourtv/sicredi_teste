package Sicredi.Teste.application.useCase;

import Sicredi.Teste.application.dto.OpenVotingSessionRequest;
import Sicredi.Teste.domain.entity.AgendaEntity;
import Sicredi.Teste.domain.exception.AgendaNotFoundException;
import Sicredi.Teste.domain.exception.AlreadyExistsOpenVotingSession;
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
        when(agendaRepository.findAgenda(anyLong())).thenReturn(Optional.empty());

        assertThrows(AgendaNotFoundException.class, () -> openVotingSessionUseCase.execute(request));

        verify(agendaRepository, times(1)).findAgenda(anyLong());
    }

    @Test
    void shouldThrowExceptionWhenExistsOpenSessionForAgenda() {
        OpenVotingSessionRequest request = new OpenVotingSessionRequest(1L, LocalDateTime.now());
        AgendaEntity agendaEntity = new AgendaEntity(1L, "title", "description");

        when(agendaRepository.findAgenda(anyLong())).thenReturn(Optional.of(agendaEntity));
        when(votingSessionRepository.existsByAgendaIdAndEndTimeAfter(anyLong(), any(LocalDateTime.class))).thenReturn(true);


        assertThrows(AlreadyExistsOpenVotingSession.class, () -> openVotingSessionUseCase.execute(request));

        verify(agendaRepository, times(1)).findAgenda(anyLong());
        verify(votingSessionRepository, times(1)).existsByAgendaIdAndEndTimeAfter(anyLong(), any(LocalDateTime.class));
    }



}
