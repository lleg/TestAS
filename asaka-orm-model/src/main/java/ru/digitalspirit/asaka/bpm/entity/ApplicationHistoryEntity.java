package ru.digitalspirit.asaka.bpm.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Objects;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "APPLICATION_HISTORY")
public class ApplicationHistoryEntity {
    @Id
    @GeneratedValue(generator = "SEQ_APPLICATION_HISTORY")
    @SequenceGenerator(name = "SEQ_APPLICATION_HISTORY", sequenceName = "SEQ_APPLICATION_HISTORY", allocationSize = 1)
    @Column(name = "ID")
    private BigInteger id;
    @Column(name = "CURRENT_STATUS")
    private String currentStatus;
    @Column(name = "CHANGE_USER")
    private String changeUser;
    @Column(name = "DATE_TIME_CHANGE")
    private Timestamp dateTimeChange;
    @Column(name = "TASK_NAME")
    private String taskName;
    @Column(name = "CREATE_USER")
    private String createUser;
    @Column(name = "DATE_TIME_CREATE")
    private Timestamp dateTimeCreate;
    @Column(name = "DELETE_USER")
    private String deleteUser;
    @Column(name = "DATE_TIME_DELETE")
    private Timestamp dateTimeDelete;
    @Column(name = "COMMENT_TEXT")
    private String comment;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApplicationHistoryEntity that = (ApplicationHistoryEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
