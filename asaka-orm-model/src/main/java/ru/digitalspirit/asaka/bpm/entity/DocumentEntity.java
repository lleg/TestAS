package ru.digitalspirit.asaka.bpm.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Date;

@Getter
@Setter
@Entity
@Table(name = "DOCUMENT")
public class DocumentEntity {

    @Id
    @GeneratedValue(generator = "SEQ_DOCUMENT")
    @SequenceGenerator(name = "SEQ_DOCUMENT", sequenceName = "SEQ_DOCUMENT", allocationSize = 1)
    @Column(name = "ID")
    private BigInteger id;
    @Column(name = "DOC_TYPE")
    private String type;
    @Column(name = "IDENTITY_FLAG")
    private Boolean identificationFlag;
    @Column(name = "DOC_NAME")
    private String documentName;
    @Column(name = "SERIES")
    private String series;
    @Column(name = "DOC_NUM")
    private String number;

    @Column(name = "ISSUED")
    private String issued;
    @Column(name = "ISSUE_DATE")
    private Date issueDate;
    @Column(name = "DIVISION_CODE")
    private String divisionCode;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DocumentEntity that = (DocumentEntity) o;

        return id != null ? id.equals(that) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}

