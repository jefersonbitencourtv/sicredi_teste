package Sicredi.Teste.unit.domain.valueObjects;

import Sicredi.Teste.domain.valueObject.ResultAgenda;
import org.junit.jupiter.api.Test;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResultAgendaTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void shouldSerializeApprovedCorrectly() throws Exception {

        String json = objectMapper.writeValueAsString(ResultAgenda.APPROVED);

        assertEquals("\"Aprovado\"", json);
    }

    @Test
    void shouldSerializeRefusedCorrectly() throws Exception {

        String json = objectMapper.writeValueAsString(ResultAgenda.REFUSED);

        assertEquals("\"Recusado\"", json);
    }

    @Test
    void shouldSerializeDrawCorrectly() throws Exception {

        String json = objectMapper.writeValueAsString(ResultAgenda.DRAW);

        assertEquals("\"Empatado\"", json);
    }
}
