package Sicredi.Teste.presentation.controller.v1;

import Sicredi.Teste.application.dto.CreateAgendaRequest;
import Sicredi.Teste.application.dto.CreateAgendaResponse;
import Sicredi.Teste.application.dto.ResultAgendaRequest;
import Sicredi.Teste.application.dto.ResultAgendaResponse;
import Sicredi.Teste.application.useCase.CreateAgendaUseCase;
import Sicredi.Teste.application.useCase.ResultAgendaUseCase;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/agendas")
public class AgendaController {

    private final CreateAgendaUseCase createAgendaUseCase;
    private final ResultAgendaUseCase resultAgendaUseCase;


    @PostMapping
    public ResponseEntity<CreateAgendaResponse> createAgenda(@Valid @RequestBody CreateAgendaRequest request) {
        log.info("Start process to create agenda with title {} and description {}", request.title(), request.description());

        CreateAgendaResponse response = createAgendaUseCase.execute(request);

        log.info("Finished process to create agenda with title {} and description {}", request.title(), request.description());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{agendaId}/result")
    public ResponseEntity<ResultAgendaResponse> getResult(@PathVariable Long agendaId) {
        log.info("Start process to get agenda with id {}", agendaId);

        var response = resultAgendaUseCase.execute(new ResultAgendaRequest(agendaId));

        log.info("Finished process to get agenda with id {}", agendaId);
        return ResponseEntity.ok(response);
    }
}
