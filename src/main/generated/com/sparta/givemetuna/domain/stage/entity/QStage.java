package com.sparta.givemetuna.domain.stage.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStage is a Querydsl query type for Stage
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStage extends EntityPathBase<Stage> {

    private static final long serialVersionUID = -1265589374L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStage stage = new QStage("stage");

    public final com.sparta.givemetuna.domain.board.entity.QBoard board;

    public final StringPath category = createString("category");

    public final ListPath<com.sparta.givemetuna.domain.checklist.entity.Checklist, com.sparta.givemetuna.domain.checklist.entity.QChecklist> checklists = this.<com.sparta.givemetuna.domain.checklist.entity.Checklist, com.sparta.givemetuna.domain.checklist.entity.QChecklist>createList("checklists", com.sparta.givemetuna.domain.checklist.entity.Checklist.class, com.sparta.givemetuna.domain.checklist.entity.QChecklist.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.sparta.givemetuna.domain.user.entity.QUser user;

    public final ListPath<com.sparta.givemetuna.domain.card.entity.Card, com.sparta.givemetuna.domain.card.entity.QCard> userCards = this.<com.sparta.givemetuna.domain.card.entity.Card, com.sparta.givemetuna.domain.card.entity.QCard>createList("userCards", com.sparta.givemetuna.domain.card.entity.Card.class, com.sparta.givemetuna.domain.card.entity.QCard.class, PathInits.DIRECT2);

    public QStage(String variable) {
        this(Stage.class, forVariable(variable), INITS);
    }

    public QStage(Path<? extends Stage> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStage(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStage(PathMetadata metadata, PathInits inits) {
        this(Stage.class, metadata, inits);
    }

    public QStage(Class<? extends Stage> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.board = inits.isInitialized("board") ? new com.sparta.givemetuna.domain.board.entity.QBoard(forProperty("board"), inits.get("board")) : null;
        this.user = inits.isInitialized("user") ? new com.sparta.givemetuna.domain.user.entity.QUser(forProperty("user")) : null;
    }

}

