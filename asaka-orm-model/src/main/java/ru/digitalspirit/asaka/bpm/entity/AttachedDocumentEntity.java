package ru.digitalspirit.asaka.bpm.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Date;

@Getter
@Setter
@Entity
@Table(name = "ATTACHED_DOCUMENT")
public class AttachedDocumentEntity
{
    @Id
    @GeneratedValue(generator="SEQ_ATTACHED_DOCUMENT")
    @SequenceGenerator(name="SEQ_ATTACHED_DOCUMENT",sequenceName="SEQ_ATTACHED_DOCUMENT", allocationSize=1)
    @Column(name = "ID")
    private BigInteger id;
    @Column(name = "GUID")
    private String guid;
    @Column(name = "DOCUMENT_TYPE")
    private String documentType;
    @Column(name = "DOCUMENT_NAME")
    private String documentName;
    @Column(name = "DOCUMENT_DATE")
    private Date documentDate;
    @Column(name = "OPERATION_TYPE")
    private String operationType;


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AttachedDocumentEntity that = (AttachedDocumentEntity) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}