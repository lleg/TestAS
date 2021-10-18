package ru.digitalspirit.asaka.applicationlist.entity;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.*;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QApplicationStatusEntity is a Querydsl query type for ApplicationStatusEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QApplicationStatusEntity extends EntityPathBase<ApplicationStatusEntity> {

    private static final long serialVersionUID = 1010461910L;

    public static final QApplicationStatusEntity applicationStatusEntity = new QApplicationStatusEntity("applicationStatusEntity");

    public final StringPath applicationId = createString("applicationId");

    public final StringPath applicationNumber = createString("applicationNumber");

    public final StringPath applicationStatus = createString("applicationStatus");

    public final StringPath branch = createString("branch");

    public final StringPath businessProcessCode = createString("businessProcessCode");

    public final StringPath centralSquare = createString("centralSquare");

    public final StringPath clientInn = createString("clientInn");

    public final StringPath clientName = createString("clientName");

    public final StringPath clientSegment = createString("clientSegment");

    public final StringPath department = createString("department");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.sql.Timestamp> modifiedDate = createDateTime("modifiedDate", java.sql.Timestamp.class);

    public final ListPath<ApplicationStatusUserEntity, QApplicationStatusUserEntity> users = this.<ApplicationStatusUserEntity, QApplicationStatusUserEntity>createList("users", ApplicationStatusUserEntity.class, QApplicationStatusUserEntity.class, PathInits.DIRECT2);

    public QApplicationStatusEntity(String variable) {
        super(ApplicationStatusEntity.class, forVariable(variable));
    }

    public QApplicationStatusEntity(Path<? extends ApplicationStatusEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QApplicationStatusEntity(PathMetadata metadata) {
        super(ApplicationStatusEntity.class, metadata);
    }

}

