package ru.digitalspirit.asaka.tasklist.entity;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QTaskEntity is a Querydsl query type for TaskEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTaskEntity extends EntityPathBase<TaskEntity> {

    private static final long serialVersionUID = 1607144292L;

    public static final QTaskEntity taskEntity = new QTaskEntity("taskEntity");

    public final StringPath applicationId = createString("applicationId");

    public final StringPath applicationNumber = createString("applicationNumber");

    public final StringPath assignedTo = createString("assignedTo");

    public final StringPath assignedType = createString("assignedType");

    public final StringPath bpCode = createString("bpCode");

    public final StringPath businessStatus = createString("businessStatus");

    public final StringPath client = createString("client");

    public final StringPath clientType = createString("clientType");

    public final StringPath department = createString("department");

    public final StringPath filial = createString("filial");

    public final StringPath filialName = createString("filialName");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath kmFIO = createString("kmFIO");

    public final DateTimePath<java.sql.Timestamp> taskCreationDate = createDateTime("taskCreationDate", java.sql.Timestamp.class);

    public final DateTimePath<java.sql.Timestamp> taskDueDate = createDateTime("taskDueDate", java.sql.Timestamp.class);

    public final StringPath taskId = createString("taskId");

    public final StringPath taskName = createString("taskName");

    public final StringPath taskStatus = createString("taskStatus");

    public QTaskEntity(String variable) {
        super(TaskEntity.class, forVariable(variable));
    }

    public QTaskEntity(Path<? extends TaskEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTaskEntity(PathMetadata metadata) {
        super(TaskEntity.class, metadata);
    }

}

