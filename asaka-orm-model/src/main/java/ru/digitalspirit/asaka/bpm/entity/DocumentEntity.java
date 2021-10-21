package ru.digitalspirit.asaka.bpm.entity;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Date;
import java.util.Objects;

@Getter
@Setter
@Builder
@Entity
@Table(name = "DOCUMENT")
public class DocumentEntity {
    @Id
    @GeneratedValue(generator = "SEQ_DOCUMENT")
    @SequenceGenerator(name = "SEQ_DOCUMENT", sequenceName = "SEQ_DOCUMENT", allocationSize = 1)
    @Column(name = "ID")
    private BigInteger id;
    @Column(name = "TYPE")
    private String type;
    @Column(name = "IDENTIFICATION_FLAG")
    private Boolean identificationFlag;
    @Column(name = "DOCUMENT_NAME")
    private String documentName;
    @Column(name = "SERIES")
    private String series;
    @Column(name = "DOCUMENT_NUMBER")
    private String number;
    @Column(name = "ISSUED")
    private String issued;
    @Column(name = "ISSUE_DATE")
    private Date issueDate;
    @Column(name = "DIVISION_CODE")
    private String divisionCode;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DocumentEntity that = (DocumentEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

