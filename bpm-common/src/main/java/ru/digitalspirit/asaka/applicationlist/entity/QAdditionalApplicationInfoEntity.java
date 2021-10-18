package ru.digitalspirit.asaka.applicationlist.entity;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.PathInits;
import com.querydsl.core.types.dsl.StringPath;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


@Generated("com.querydsl.codegen.EntitySerializer")
public class QAdditionalApplicationInfoEntity extends EntityPathBase<AdditionalApplicationInfoEntity> {

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAdditionalApplicationInfoEntity additionalApplicationInfoEntity = new QAdditionalApplicationInfoEntity("additionalApplicationInfoEntity");

    public final QApplicationStatusEntity application;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath protocolCountMember = createString("protocolCountMember");

    public final StringPath protocolTotalCountMember = createString("protocolTotalCountMember");

    public final StringPath protocolStatus = createString("protocolStatus");

    public final StringPath protocolNumber = createString("protocolNumber");

    public QAdditionalApplicationInfoEntity(String variable) {
        this(AdditionalApplicationInfoEntity.class, forVariable(variable), INITS);
    }

    public QAdditionalApplicationInfoEntity(Path<? extends AdditionalApplicationInfoEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAdditionalApplicationInfoEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAdditionalApplicationInfoEntity(PathMetadata metadata, PathInits inits) {
        this(AdditionalApplicationInfoEntity.class, metadata, inits);
    }

    public QAdditionalApplicationInfoEntity(Class<? extends AdditionalApplicationInfoEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.application = inits.isInitialized("application") ? new QApplicationStatusEntity(forProperty("application")) : null;
    }
}

