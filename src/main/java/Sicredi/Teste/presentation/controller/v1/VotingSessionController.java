package Sicredi.Teste.presentation.controller.v1;

import Sicredi.Teste.application.dto.OpenVotingSessionRequest;
import Sicredi.Teste.application.dto.OpenVotingSessionResponse;
import Sicredi.Teste.application.useCase.OpenVotingSessionUseCase;
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
@RequestMapping("/api/v1/voting-sessions")
public class VotingSessionController {

    private final OpenVotingSessionUseCase openVotingSessionUseCase;

    @PostMapping
    public ResponseEntity<OpenVotingSessionResponse> openSession(@Valid @RequestBody OpenVotingSessionRequest request) {
        log.info("Start process to open session with agenda id {} and endTime {}", request.agendaId(), request.endTime());

        OpenVotingSessionResponse response = openVotingSessionUseCase.execute(request);

        log.info("Finished process to open session with agenda id {} and endTime {}", request.agendaId(), request.endTime());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
