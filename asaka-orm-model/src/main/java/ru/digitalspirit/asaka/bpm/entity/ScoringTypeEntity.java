package ru.digitalspirit.asaka.bpm.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Objects;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name  = "SCORING_TYPE")
public class ScoringTypeEntity {
    @Id
    @GeneratedValue(generator = "SEQ_SCORING_TYPE")
    @SequenceGenerator(name = "SEQ_SCORING_TYPE", sequenceName = "SEQ_SCORING_TYPE", allocationSize = 1)
    @Column(name = "ID")
    private BigInteger id;
    @Column(name = "DM0_RESULT")
    private Integer dm0Result;
    @Column(name = "DM1_RESULT")
    private Integer dm1Result;
    @Column(name = "DM2_RESULT")
    private Integer dm2Result;
    @Column(name = "DM3_RESULT")
    private Integer dm3Result;
    @Column(name = "DM4_RESULT")
    private Integer dm4Result;
    @Column(name = "REJECT_REASON")
    private String rejectReason;
    @Column(name = "REJECT_REASON_NIKI")
    private String rejectReasonNiki;
    @Column(name = "CREDIT_HISTORY_DATA")
    private String creditHistoryData;
    @Column(name = "INCOME_TAX_DATA")
    private String incomeTaxData;
    @Column(name = "SCORE_DATA")
    private String scoreData;
    @OneToOne(fetch = FetchType.LAZY, targetEntity = SolvencyTypeEntity.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "SOLVENCY_ID", unique = true)
    private SolvencyTypeEntity solvency;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScoringTypeEntity that = (ScoringTypeEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
