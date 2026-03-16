package Sicredi.Teste.unit.domain.entities;

import Sicredi.Teste.domain.entity.AgendaEntity;
import Sicredi.Teste.domain.exception.DomainException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AgendaEntityTest {

    @Test
    void shouldCreateAgenda() {

        var agenda = AgendaEntity.createAgenda(
                "Nova pauta",
                "Descrição da pauta"
        );

        assertNotNull(agenda);
        assertEquals("Nova pauta", agenda.getTitle());
        assertEquals("Descrição da pauta", agenda.getDescription());
    }

    @Test
    void shouldThrowExceptionWhenTitleIsBlank() {

        assertThrows(
                DomainException.class,
                () -> AgendaEntity.createAgenda(
                        "",
                        "Descrição"
                )
        );
    }

    @Test
    void shouldThrowExceptionWhenDescriptionIsBlank() {

        assertThrows(
                DomainException.class,
                () -> AgendaEntity.createAgenda(
                        "Título",
                        ""
                )
        );
    }
}
