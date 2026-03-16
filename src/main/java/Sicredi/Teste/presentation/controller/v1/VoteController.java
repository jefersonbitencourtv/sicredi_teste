package Sicredi.Teste.presentation.controller.v1;

import Sicredi.Teste.application.dto.AssociateVoteRequest;
import Sicredi.Teste.application.useCase.AssociateVoteUseCase;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/votes")
public class VoteController {

    private final AssociateVoteUseCase associateVoteUseCase;

    @PostMapping
    public ResponseEntity<Void> vote(@Valid @RequestBody AssociateVoteRequest request) {
        String lastDigits = request.associateId().substring(request.associateId().length() - 4);
        log.info("Start process to vote in voting session {}, associate last digits {} and vote type {} "
                , request.votingSessionId(), lastDigits, request.voteType().getValue());

        associateVoteUseCase.execute(request);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
