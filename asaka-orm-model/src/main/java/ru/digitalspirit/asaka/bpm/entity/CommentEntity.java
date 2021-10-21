package ru.digitalspirit.asaka.bpm.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Objects;

@Setter
@Getter
@Builder
@Entity
@Table(name = "COMMENT")
public class CommentEntity {
    @Id
    @GeneratedValue(generator = "SEQ_COMMENT")
    @SequenceGenerator(name = "SEQ_COMMENT", sequenceName = "SEQ_COMMENT", allocationSize = 1)
    @Column(name = "ID")
    private BigInteger id;
    @Column(name = "CRITICAL_FLAG")
    private Boolean criticalFlag;
    @Column(name = "COMMENT")
    private String comment;
    @Column(name = "COMMENT_PERSON")
    private String commentPerson;
    @Column(name = "COMMENT_DATE")
    private Timestamp commentDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentEntity that = (CommentEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
