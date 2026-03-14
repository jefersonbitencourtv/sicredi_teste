package Sicredi.Teste.application.dto;

import Sicredi.Teste.domain.entity.VotingSessionEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.annotation.JsonNaming;

import java.time.LocalDateTime;

@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class OpenVotingSessionResponse {

    private Long id;

    private Long agendaId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime endTime;

    public static OpenVotingSessionResponse fromEntity(VotingSessionEntity entity) {
        return new OpenVotingSessionResponse(
                entity.getId(),
                entity.getAgendaId(),
                entity.getEndTime()
        );
    }
}
