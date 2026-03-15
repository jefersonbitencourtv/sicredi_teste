package Sicredi.Teste.application.dto;

import Sicredi.Teste.domain.valueObject.VoteType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AssociateVoteRequest {

    private Long sessionId;

    private String associateId;

    private VoteType voteType;

}
