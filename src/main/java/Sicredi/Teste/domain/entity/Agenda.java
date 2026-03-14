package Sicredi.Teste.domain.entity;

import jakarta.persistence.*;

@Entity
public class Agenda extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

}
