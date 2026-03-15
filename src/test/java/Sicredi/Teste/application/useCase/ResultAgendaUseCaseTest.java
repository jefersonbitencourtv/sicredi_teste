package Sicredi.Teste.application.useCase;

import Sicredi.Teste.application.dto.ResultAgendaRequest;
import Sicredi.Teste.domain.entity.AgendaEntity;
import Sicredi.Teste.domain.entity.VoteEntity;
import Sicredi.Teste.domain.entity.VotingSessionEntity;
import Sicredi.Teste.domain.exception.AlreadyExistsOpenVotingSessionException;
import Sicredi.Teste.domain.exception.VotingSessionNotFoundException;
import Sicredi.Teste.domain.repository.VoteRepository;
import Sicredi.Teste.domain.repository.VotingSessionRepository;
import Sicredi.Teste.domain.valueObject.ResultAgenda;
import Sicredi.Teste.domain.valueObject.VoteType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ResultAgendaUseCaseTest {

    @Mock
    private VotingSessionRepository votingSessionRepository;

    @Mock
    private VoteRepository voteRepository;

    @InjectMocks
    private ResultAgendaUseCase resultAgendaUseCase;

    @Test
    void shouldThrowVotingSessionIsOpen(){
        ResultAgendaRequest resultAgendaRequest = new ResultAgendaRequest(1L);

        when(votingSessionRepository.findByAgendaId(anyLong())).thenReturn(Optional.empty());

        assertThrows(VotingSessionNotFoundException.class, () -> resultAgendaUseCase.execute(resultAgendaRequest));

        verify(votingSessionRepository, times(1)).findByAgendaId(anyLong());
    }

    @Test
    void shouldThrowVotingSessionNotFound(){
        ResultAgendaRequest resultAgendaRequest = new ResultAgendaRequest(1L);
        AgendaEntity agendaEntity = AgendaEntity.createAgenda("Title", "Description");
        VotingSessionEntity votingSessionEntity = VotingSessionEntity.createVotingSession(agendaEntity, null);

        when(votingSessionRepository.findByAgendaId(anyLong())).thenReturn(Optional.of(votingSessionEntity));

        assertThrows(AlreadyExistsOpenVotingSessionException.class, () -> resultAgendaUseCase.execute(resultAgendaRequest));

        verify(votingSessionRepository, times(1)).findByAgendaId(anyLong());
    }

    @Test
    void shouldApprovedAgenda(){
        ResultAgendaRequest resultAgendaRequest = new ResultAgendaRequest(1L);
        AgendaEntity agendaEntity = AgendaEntity.createAgenda("Title", "Description");
        VotingSessionEntity votingSessionEntity = VotingSessionEntity.createVotingSession(agendaEntity, LocalDateTime.now().minusHours(1));

        when(votingSessionRepository.findByAgendaId(anyLong())).thenReturn(Optional.of(votingSessionEntity));
        when(voteRepository.countByVotingSessionIdAndVoteType(any(), any(VoteType.class))).thenReturn(10L).thenReturn(2L);

        var response = resultAgendaUseCase.execute(resultAgendaRequest);

        verify(votingSessionRepository, times(1)).findByAgendaId(anyLong());
        assertEquals(10L, response.yesVotes());
        assertEquals(2L, response.noVotes());
        assertEquals(ResultAgenda.APPROVED, response.result());
    }

    @Test
    void shouldRefusedAgenda(){
        ResultAgendaRequest resultAgendaRequest = new ResultAgendaRequest(1L);
        AgendaEntity agendaEntity = AgendaEntity.createAgenda("Title", "Description");
        VotingSessionEntity votingSessionEntity = VotingSessionEntity.createVotingSession(agendaEntity, LocalDateTime.now().minusHours(1));

        when(votingSessionRepository.findByAgendaId(anyLong())).thenReturn(Optional.of(votingSessionEntity));
        when(voteRepository.countByVotingSessionIdAndVoteType(any(), any(VoteType.class))).thenReturn(2L).thenReturn(10L);

        var response = resultAgendaUseCase.execute(resultAgendaRequest);

        verify(votingSessionRepository, times(1)).findByAgendaId(anyLong());
        assertEquals(2L, response.yesVotes());
        assertEquals(10L, response.noVotes());
        assertEquals(ResultAgenda.REFUSED, response.result());
    }

    @Test
    void shouldDrawAgenda(){
        ResultAgendaRequest resultAgendaRequest = new ResultAgendaRequest(1L);
        AgendaEntity agendaEntity = AgendaEntity.createAgenda("Title", "Description");
        VotingSessionEntity votingSessionEntity = VotingSessionEntity.createVotingSession(agendaEntity, LocalDateTime.now().minusHours(1));

        when(votingSessionRepository.findByAgendaId(anyLong())).thenReturn(Optional.of(votingSessionEntity));
        when(voteRepository.countByVotingSessionIdAndVoteType(any(), any(VoteType.class))).thenReturn(10L).thenReturn(10L);

        var response = resultAgendaUseCase.execute(resultAgendaRequest);

        verify(votingSessionRepository, times(1)).findByAgendaId(anyLong());
        assertEquals(10L, response.yesVotes());
        assertEquals(10L, response.noVotes());
        assertEquals(ResultAgenda.DRAW, response.result());
    }

}
