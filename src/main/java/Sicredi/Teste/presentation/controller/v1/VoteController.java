package Sicredi.Teste.presentation.controller.v1;

import Sicredi.Teste.application.dto.AssociateVoteRequest;
import Sicredi.Teste.application.useCase.AssociateVoteUseCase;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/votes")
public class VoteController {

    private final AssociateVoteUseCase associateVoteUseCase;

    @PostMapping
    public ResponseEntity<Void> vote(@Valid @RequestBody AssociateVoteRequest request) {
        associateVoteUseCase.execute(request);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
