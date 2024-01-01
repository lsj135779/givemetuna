package com.sparta.givemetuna.domain.board.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBoard is a Querydsl query type for Board
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBoard extends EntityPathBase<Board> {

    private static final long serialVersionUID = 221068418L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBoard board = new QBoard("board");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<com.sparta.givemetuna.domain.user.entity.BoardUserRole, com.sparta.givemetuna.domain.user.entity.QBoardUserRole> invitedUserRole = this.<com.sparta.givemetuna.domain.user.entity.BoardUserRole, com.sparta.givemetuna.domain.user.entity.QBoardUserRole>createList("invitedUserRole", com.sparta.givemetuna.domain.user.entity.BoardUserRole.class, com.sparta.givemetuna.domain.user.entity.QBoardUserRole.class, PathInits.DIRECT2);

    public final StringPath name = createString("name");

    public final ListPath<com.sparta.givemetuna.domain.stage.entity.Stage, com.sparta.givemetuna.domain.stage.entity.QStage> stages = this.<com.sparta.givemetuna.domain.stage.entity.Stage, com.sparta.givemetuna.domain.stage.entity.QStage>createList("stages", com.sparta.givemetuna.domain.stage.entity.Stage.class, com.sparta.givemetuna.domain.stage.entity.QStage.class, PathInits.DIRECT2);

    public final com.sparta.givemetuna.domain.user.entity.QUser user;

    public QBoard(String variable) {
        this(Board.class, forVariable(variable), INITS);
    }

    public QBoard(Path<? extends Board> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBoard(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBoard(PathMetadata metadata, PathInits inits) {
        this(Board.class, metadata, inits);
    }

    public QBoard(Class<? extends Board> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new com.sparta.givemetuna.domain.user.entity.QUser(forProperty("user")) : null;
    }

}

