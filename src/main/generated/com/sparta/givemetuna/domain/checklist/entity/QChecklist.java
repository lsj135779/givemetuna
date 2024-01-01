package com.sparta.givemetuna.domain.checklist.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QChecklist is a Querydsl query type for Checklist
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QChecklist extends EntityPathBase<Checklist> {

    private static final long serialVersionUID = -2004103422L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QChecklist checklist = new QChecklist("checklist");

    public final com.sparta.givemetuna.domain.user.entity.QUser assignee;

    public final com.sparta.givemetuna.domain.card.entity.QCard card;

    public final BooleanPath check = createBoolean("check");

    public final StringPath contents = createString("contents");

    public final BooleanPath deletable = createBoolean("deletable");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final EnumPath<Priority> priority = createEnum("priority", Priority.class);

    public QChecklist(String variable) {
        this(Checklist.class, forVariable(variable), INITS);
    }

    public QChecklist(Path<? extends Checklist> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QChecklist(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QChecklist(PathMetadata metadata, PathInits inits) {
        this(Checklist.class, metadata, inits);
    }

    public QChecklist(Class<? extends Checklist> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.assignee = inits.isInitialized("assignee") ? new com.sparta.givemetuna.domain.user.entity.QUser(forProperty("assignee")) : null;
        this.card = inits.isInitialized("card") ? new com.sparta.givemetuna.domain.card.entity.QCard(forProperty("card"), inits.get("card")) : null;
    }

}

