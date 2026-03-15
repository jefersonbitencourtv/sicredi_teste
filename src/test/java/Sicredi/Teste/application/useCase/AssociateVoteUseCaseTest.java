package Sicredi.Teste.application.useCase;

import Sicredi.Teste.application.dto.AssociateVoteRequest;
import Sicredi.Teste.domain.entity.AgendaEntity;
import Sicredi.Teste.domain.entity.VotingSessionEntity;
import Sicredi.Teste.domain.exception.VotingSessionIsClosedException;
import Sicredi.Teste.domain.exception.VotingSessionNotFoundException;
import Sicredi.Teste.domain.repository.VotingSessionRepository;
import Sicredi.Teste.domain.valueObject.VoteType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AssociateVoteUseCaseTest {

    @Mock
    private VotingSessionRepository votingSessionRepository;

    @InjectMocks
    private AssociateVoteUseCase associateVoteUseCase;

    @Test
    void shouldThrowVotingSessionNotFound(){
        AssociateVoteRequest request = new AssociateVoteRequest(1L, "12", VoteType.YES);

        when(votingSessionRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(VotingSessionNotFoundException.class, () -> associateVoteUseCase.execute(request));
        verify(votingSessionRepository, times(1)).findById(anyLong());
    }

    @Test
    void shouldThrowVotingSessionIsClosed(){
        AssociateVoteRequest request = new AssociateVoteRequest(1L, "12", VoteType.YES);
        AgendaEntity agendaEntity = AgendaEntity.createAgenda("title", "description");
        VotingSessionEntity votingSessionEntity = VotingSessionEntity.createVotingSession(agendaEntity, LocalDateTime.now().minusMinutes(1));

        when(votingSessionRepository.findById(anyLong())).thenReturn(Optional.of(votingSessionEntity));

        assertThrows(VotingSessionIsClosedException.class, () -> associateVoteUseCase.execute(request));
        verify(votingSessionRepository, times(1)).findById(anyLong());
    }



}
