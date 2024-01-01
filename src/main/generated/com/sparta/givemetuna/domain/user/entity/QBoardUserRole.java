package com.sparta.givemetuna.domain.user.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBoardUserRole is a Querydsl query type for BoardUserRole
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBoardUserRole extends EntityPathBase<BoardUserRole> {

    private static final long serialVersionUID = -1145273120L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBoardUserRole boardUserRole = new QBoardUserRole("boardUserRole");

    public final com.sparta.givemetuna.domain.board.entity.QBoard board;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final EnumPath<Role> role = createEnum("role", Role.class);

    public final QUser user;

    public QBoardUserRole(String variable) {
        this(BoardUserRole.class, forVariable(variable), INITS);
    }

    public QBoardUserRole(Path<? extends BoardUserRole> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBoardUserRole(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBoardUserRole(PathMetadata metadata, PathInits inits) {
        this(BoardUserRole.class, metadata, inits);
    }

    public QBoardUserRole(Class<? extends BoardUserRole> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.board = inits.isInitialized("board") ? new com.sparta.givemetuna.domain.board.entity.QBoard(forProperty("board"), inits.get("board")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

