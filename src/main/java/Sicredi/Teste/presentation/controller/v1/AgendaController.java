package Sicredi.Teste.presentation.controller.v1;

import Sicredi.Teste.application.dto.CreateAgendaRequest;
import Sicredi.Teste.application.dto.CreateAgendaResponse;
import Sicredi.Teste.application.dto.ResultAgendaRequest;
import Sicredi.Teste.application.dto.ResultAgendaResponse;
import Sicredi.Teste.application.useCase.CreateAgendaUseCase;
import Sicredi.Teste.application.useCase.ResultAgendaUseCase;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/agendas")
public class AgendaController {

    private final CreateAgendaUseCase createAgendaUseCase;
    private final ResultAgendaUseCase resultAgendaUseCase;


    @PostMapping
    public ResponseEntity<CreateAgendaResponse> createAgenda(@Valid @RequestBody CreateAgendaRequest request) {

        CreateAgendaResponse response = createAgendaUseCase.execute(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{agendaId}/result")
    public ResponseEntity<ResultAgendaResponse> getResult(@PathVariable Long agendaId) {
        var response = resultAgendaUseCase.execute(new ResultAgendaRequest(agendaId));
        return ResponseEntity.ok(response);
    }
}
