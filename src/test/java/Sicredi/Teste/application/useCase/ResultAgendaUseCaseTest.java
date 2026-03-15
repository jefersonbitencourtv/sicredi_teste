package Sicredi.Teste.application.useCase;

import Sicredi.Teste.application.dto.ResultAgendaRequest;
import Sicredi.Teste.domain.entity.AgendaEntity;
import Sicredi.Teste.domain.entity.VotingSessionEntity;
import Sicredi.Teste.domain.exception.AlreadyExistsOpenVotingSessionException;
import Sicredi.Teste.domain.repository.VotingSessionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ResultAgendaUseCaseTest {

    @Mock
    private VotingSessionRepository votingSessionRepository;

    @InjectMocks
    private ResultAgendaUseCase resultAgendaUseCase;

    @Test
    void shouldThrowVotingSessionIsOpen(){
        ResultAgendaRequest resultAgendaRequest = new ResultAgendaRequest(1L);
        AgendaEntity agendaEntity = AgendaEntity.createAgenda("Title", "Description");
        VotingSessionEntity votingSessionEntity = VotingSessionEntity.createVotingSession(agendaEntity, null);

        when(votingSessionRepository.findByAgendaId(anyLong())).thenReturn(Optional.of(votingSessionEntity));

        assertThrows(AlreadyExistsOpenVotingSessionException.class, () -> resultAgendaUseCase.execute(resultAgendaRequest));

        verify(votingSessionRepository, times(1)).findByAgendaId(anyLong());
    }

}
