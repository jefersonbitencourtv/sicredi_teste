package Sicredi.Teste.domain.entity;

import Sicredi.Teste.domain.valueObject.VoteType;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class VoteEntity extends BaseEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private Long sessionId;

    @Column(nullable = false)
    private String associateId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VoteType voteType;

}
