package Sicredi.Teste.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
@AllArgsConstructor
public class VotingSessionEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(nullable = false)
    private AgendaEntity agenda;

    @Column(nullable = false)
    private LocalDateTime endTime;

    public Long getAgendaId(){
        return this.agenda.getId();
    }

}
