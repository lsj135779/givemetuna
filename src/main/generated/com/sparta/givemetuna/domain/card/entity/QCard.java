package com.sparta.givemetuna.domain.card.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCard is a Querydsl query type for Card
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCard extends EntityPathBase<Card> {

    private static final long serialVersionUID = 971549116L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCard card = new QCard("card");

    public final com.sparta.givemetuna.domain.common.QBaseEntity _super = new com.sparta.givemetuna.domain.common.QBaseEntity(this);

    public final com.sparta.givemetuna.domain.user.entity.QUser assignor;

    public final EnumPath<com.sparta.givemetuna.domain.card.constant.CardPriority> cardPriority = createEnum("cardPriority", com.sparta.givemetuna.domain.card.constant.CardPriority.class);

    public final ListPath<com.sparta.givemetuna.domain.checklist.entity.Checklist, com.sparta.givemetuna.domain.checklist.entity.QChecklist> checklists = this.<com.sparta.givemetuna.domain.checklist.entity.Checklist, com.sparta.givemetuna.domain.checklist.entity.QChecklist>createList("checklists", com.sparta.givemetuna.domain.checklist.entity.Checklist.class, com.sparta.givemetuna.domain.checklist.entity.QChecklist.class, PathInits.DIRECT2);

    public final DateTimePath<java.sql.Timestamp> closedAt = createDateTime("closedAt", java.sql.Timestamp.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final com.sparta.givemetuna.domain.user.entity.QUser creator;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.sparta.givemetuna.domain.stage.entity.QStage stage;

    public final DateTimePath<java.sql.Timestamp> startedAt = createDateTime("startedAt", java.sql.Timestamp.class);

    public final StringPath title = createString("title");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QCard(String variable) {
        this(Card.class, forVariable(variable), INITS);
    }

    public QCard(Path<? extends Card> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCard(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCard(PathMetadata metadata, PathInits inits) {
        this(Card.class, metadata, inits);
    }

    public QCard(Class<? extends Card> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.assignor = inits.isInitialized("assignor") ? new com.sparta.givemetuna.domain.user.entity.QUser(forProperty("assignor")) : null;
        this.creator = inits.isInitialized("creator") ? new com.sparta.givemetuna.domain.user.entity.QUser(forProperty("creator")) : null;
        this.stage = inits.isInitialized("stage") ? new com.sparta.givemetuna.domain.stage.entity.QStage(forProperty("stage"), inits.get("stage")) : null;
    }

}

