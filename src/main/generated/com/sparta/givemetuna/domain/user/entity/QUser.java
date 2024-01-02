package com.sparta.givemetuna.domain.user.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = -91796814L;

    public static final QUser user = new QUser("user");

    public final StringPath account = createString("account");

    public final ListPath<BoardUserRole, QBoardUserRole> boardUserRoles = this.<BoardUserRole, QBoardUserRole>createList("boardUserRoles", BoardUserRole.class, QBoardUserRole.class, PathInits.DIRECT2);

    public final ListPath<com.sparta.givemetuna.domain.card.entity.Card, com.sparta.givemetuna.domain.card.entity.QCard> cardsAssignedTo = this.<com.sparta.givemetuna.domain.card.entity.Card, com.sparta.givemetuna.domain.card.entity.QCard>createList("cardsAssignedTo", com.sparta.givemetuna.domain.card.entity.Card.class, com.sparta.givemetuna.domain.card.entity.QCard.class, PathInits.DIRECT2);

    public final ListPath<com.sparta.givemetuna.domain.card.entity.Card, com.sparta.givemetuna.domain.card.entity.QCard> cardsCreated = this.<com.sparta.givemetuna.domain.card.entity.Card, com.sparta.givemetuna.domain.card.entity.QCard>createList("cardsCreated", com.sparta.givemetuna.domain.card.entity.Card.class, com.sparta.givemetuna.domain.card.entity.QCard.class, PathInits.DIRECT2);

    public final ListPath<com.sparta.givemetuna.domain.checklist.entity.Checklist, com.sparta.givemetuna.domain.checklist.entity.QChecklist> checklists = this.<com.sparta.givemetuna.domain.checklist.entity.Checklist, com.sparta.givemetuna.domain.checklist.entity.QChecklist>createList("checklists", com.sparta.givemetuna.domain.checklist.entity.Checklist.class, com.sparta.givemetuna.domain.checklist.entity.QChecklist.class, PathInits.DIRECT2);

    public final StringPath description = createString("description");

    public final StringPath email = createString("email");

    public final StringPath github = createString("github");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<com.sparta.givemetuna.domain.issue.entity.Issue, com.sparta.givemetuna.domain.issue.entity.QIssue> issues = this.<com.sparta.givemetuna.domain.issue.entity.Issue, com.sparta.givemetuna.domain.issue.entity.QIssue>createList("issues", com.sparta.givemetuna.domain.issue.entity.Issue.class, com.sparta.givemetuna.domain.issue.entity.QIssue.class, PathInits.DIRECT2);

    public final NumberPath<Long> kakaoId = createNumber("kakaoId", Long.class);

    public final StringPath nickname = createString("nickname");

    public final StringPath password = createString("password");

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

