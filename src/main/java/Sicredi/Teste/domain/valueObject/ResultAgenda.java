package Sicredi.Teste.domain.valueObject;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ResultAgenda {
    APPROVED("Aprovado"),
    REFUSED("Recusado"),
    DRAW("Empatado");

    private final String value;

    @JsonValue
    public String getValue() {
        return value;
    }
}
