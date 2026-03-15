package Sicredi.Teste.presentation.controller.v1;

import Sicredi.Teste.application.dto.OpenVotingSessionRequest;
import Sicredi.Teste.application.dto.OpenVotingSessionResponse;
import Sicredi.Teste.application.useCase.OpenVotingSessionUseCase;
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
@RequestMapping("/api/v1/voting-sessions")
public class VotingSessionController {

    private final OpenVotingSessionUseCase openVotingSessionUseCase;

    @PostMapping
    public ResponseEntity<OpenVotingSessionResponse> openSession(@Valid @RequestBody OpenVotingSessionRequest request) {

        OpenVotingSessionResponse response = openVotingSessionUseCase.execute(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
