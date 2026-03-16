package Sicredi.Teste.domain.entity;

import Sicredi.Teste.application.dto.CreateAgendaRequest;
import Sicredi.Teste.domain.exception.DomainException;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@Entity
@Table(name = "agenda")
public class AgendaEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    private AgendaEntity(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public AgendaEntity() {

    }

    public static AgendaEntity createAgenda(String title, String description) {
        if(title.isBlank()){
            throw new DomainException("Description is mandatory");
        }

        if(description.isBlank()){
            throw new DomainException("Title is mandatory");
        }

        return new AgendaEntity(title, description);
    }

}
