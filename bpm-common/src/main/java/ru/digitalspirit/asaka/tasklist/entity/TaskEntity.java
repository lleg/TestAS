package ru.digitalspirit.asaka.tasklist.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;


@Entity
@SequenceGenerator(name = "SEQ_TASKLIST", sequenceName = "SEQ_TASKLIST", allocationSize = 1)
@Table(name = "TASK_LIST")
@Getter
@Setter
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="SEQ_TASKLIST")
    @Column(name = "ID")
    private Long id;

    @Column(name = "APPLICATIONNUMBER")
    private String applicationNumber;

    @Column(name = "APPLICATIONID")
    private String applicationId;

    @Column(name = "CLIENT")
    private String client;

    @Column(name = "TASKID")
    private String taskId;

    @Column(name = "TASKSTATUS")
    private String taskStatus;

    @Column(name = "TASKNAME")
    private String taskName;

    @Column(name = "FILIAL")
    private String filial;

    @Column(name = "FILIAL_NAME")
    private String filialName;

    @Column(name = "DEPARTMENT")
    private String department;

    @Column(name = "ASSIGNEDTO")
    private String assignedTo;

    @Column(name = "ASSIGNEDTYPE")
    private String assignedType;

    @Column(name = "BUSINESSSTATUS")
    private String businessStatus;

    @Column(name = "KMFIO")
    private String kmFIO;

    @Column(name = "TASKCREATIONDATE")
    private Timestamp taskCreationDate;

    @Column(name = "TASKDUEDATE")
    private Timestamp taskDueDate;

    @Column(name = "CLIENTTYPE")
    private String clientType;

    @Column(name = "BPCODE")
    private String bpCode;
}
