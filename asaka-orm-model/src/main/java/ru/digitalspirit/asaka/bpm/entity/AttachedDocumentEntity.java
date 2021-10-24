package ru.digitalspirit.asaka.bpm.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Date;
import java.util.Objects;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ATTACHED_DOCUMENT")
public class AttachedDocumentEntity {
    @Id
    @GeneratedValue(generator = "SEQ_ATTACHED_DOCUMENT")
    @SequenceGenerator(name = "SEQ_ATTACHED_DOCUMENT", sequenceName = "SEQ_ATTACHED_DOCUMENT", allocationSize = 1)
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AttachedDocumentEntity that = (AttachedDocumentEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
