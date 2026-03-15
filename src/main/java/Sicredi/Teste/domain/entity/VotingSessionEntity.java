package Sicredi.Teste.domain.entity;

import Sicredi.Teste.domain.exception.DomainException;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
public class VotingSessionEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(nullable = false)
    private AgendaEntity agenda;

    @Column(nullable = false)
    private LocalDateTime endTime;

    public VotingSessionEntity() {

    }

    private VotingSessionEntity(LocalDateTime endTime, AgendaEntity agenda) {
        this.endTime = endTime;
        this.agenda = agenda;
    }

    public static VotingSessionEntity createVotingSession(AgendaEntity agenda, LocalDateTime endTime) {

        if (agenda == null) {
            throw new DomainException("Agenda is mandatory");
        }

        if (endTime == null) {
            throw new DomainException("EndTime is mandatory");
        }

        return new VotingSessionEntity(endTime, agenda);
    }


    public Long getAgendaId(){
        return this.agenda.getId();
    }

}
