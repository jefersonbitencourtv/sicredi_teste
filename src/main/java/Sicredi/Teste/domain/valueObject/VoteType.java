package Sicredi.Teste.domain.valueObject;

import Sicredi.Teste.domain.exception.VoteTypeInvalidException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum VoteType {
    YES("Sim"),
    NO("Não");

    private final String value;

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static VoteType fromValue(String value) {
        for (VoteType vote : VoteType.values()) {
            if (vote.value.equalsIgnoreCase(value)) {
                return vote;
            }
        }
        throw new VoteTypeInvalidException(value);
    }
}
