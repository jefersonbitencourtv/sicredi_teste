package Sicredi.Teste.domain.entity;

import Sicredi.Teste.domain.exception.DomainException;
import Sicredi.Teste.domain.valueObject.VoteType;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "vote")
public class VoteEntity extends BaseEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(nullable = false)
    private VotingSessionEntity votingSession;

    @Column(nullable = false)
    private String associateId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VoteType voteType;

    private VoteEntity(VotingSessionEntity votingSession, String associateId, VoteType voteType) {
        this.votingSession = votingSession;
        this.associateId = associateId;
        this.voteType = voteType;
    }

    public VoteEntity() {

    }

    public static VoteEntity createVote(VotingSessionEntity votingSession, String associateId, VoteType voteType) {
        if(votingSession == null) {
            throw new DomainException("Voting session is mandatory");
        }

        if(StringUtils.isBlank(associateId)) {
            throw new DomainException("Associate id is mandatory");
        }

        if(voteType == null) {
            throw new DomainException("Vote type is mandatory");
        }

        return new VoteEntity(votingSession, associateId, voteType);
    }

}
