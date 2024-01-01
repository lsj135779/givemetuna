package com.sparta.givemetuna.domain.issue.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QIssueComment is a Querydsl query type for IssueComment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QIssueComment extends EntityPathBase<IssueComment> {

    private static final long serialVersionUID = -1724607331L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QIssueComment issueComment = new QIssueComment("issueComment");

    public final com.sparta.givemetuna.domain.common.QBaseEntity _super = new com.sparta.givemetuna.domain.common.QBaseEntity(this);

    public final StringPath contents = createString("contents");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QIssue issue;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final com.sparta.givemetuna.domain.user.entity.QUser user;

    public QIssueComment(String variable) {
        this(IssueComment.class, forVariable(variable), INITS);
    }

    public QIssueComment(Path<? extends IssueComment> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QIssueComment(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QIssueComment(PathMetadata metadata, PathInits inits) {
        this(IssueComment.class, metadata, inits);
    }

    public QIssueComment(Class<? extends IssueComment> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.issue = inits.isInitialized("issue") ? new QIssue(forProperty("issue"), inits.get("issue")) : null;
        this.user = inits.isInitialized("user") ? new com.sparta.givemetuna.domain.user.entity.QUser(forProperty("user")) : null;
    }

}

