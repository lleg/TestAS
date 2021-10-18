package ru.digitalspirit.asaka.applicationlist.entity;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.PathInits;
import com.querydsl.core.types.dsl.StringPath;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QApplicationStatusUserEntity is a Querydsl query type for ApplicationStatusUserEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QApplicationStatusUserEntity extends EntityPathBase<ApplicationStatusUserEntity> {

    private static final long serialVersionUID = 740773633L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QApplicationStatusUserEntity applicationStatusUserEntity = new QApplicationStatusUserEntity("applicationStatusUserEntity");

    public final QApplicationStatusEntity appInfo;

    public final StringPath bpmRole = createString("bpmRole");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath nfoRole = createString("nfoRole");

    public final StringPath userLogin = createString("userLogin");

    public final StringPath userName = createString("userName");

    public QApplicationStatusUserEntity(String variable) {
        this(ApplicationStatusUserEntity.class, forVariable(variable), INITS);
    }

    public QApplicationStatusUserEntity(Path<? extends ApplicationStatusUserEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QApplicationStatusUserEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QApplicationStatusUserEntity(PathMetadata metadata, PathInits inits) {
        this(ApplicationStatusUserEntity.class, metadata, inits);
    }

    public QApplicationStatusUserEntity(Class<? extends ApplicationStatusUserEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.appInfo = inits.isInitialized("appInfo") ? new QApplicationStatusEntity(forProperty("appInfo")) : null;
    }

}

