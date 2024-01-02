package com.sparta.givemetuna.domain.issue.dto.read;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.sparta.givemetuna.domain.issue.dto.read.QIssueReadResponseDto is a Querydsl Projection type for IssueReadResponseDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QIssueReadResponseDto extends ConstructorExpression<IssueReadResponseDto> {

    private static final long serialVersionUID = -647625596L;

    public QIssueReadResponseDto(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<String> title, com.querydsl.core.types.Expression<String> contents, com.querydsl.core.types.Expression<com.sparta.givemetuna.domain.issue.entity.IssueStatus> issueStatus, com.querydsl.core.types.Expression<Long> cardId, com.querydsl.core.types.Expression<? extends java.util.List<com.sparta.givemetuna.domain.issuecomment.entity.IssueComment>> issueComments) {
        super(IssueReadResponseDto.class, new Class<?>[]{long.class, String.class, String.class, com.sparta.givemetuna.domain.issue.entity.IssueStatus.class, long.class, java.util.List.class}, id, title, contents, issueStatus, cardId, issueComments);
    }

}

