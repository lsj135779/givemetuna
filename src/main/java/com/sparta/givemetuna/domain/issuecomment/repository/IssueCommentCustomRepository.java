package com.sparta.givemetuna.domain.issuecomment.repository;

import com.querydsl.jpa.impl.JPAQuery;
import com.sparta.givemetuna.domain.issuecomment.controller.read.IssueCommentReadResponseDto;
import com.sparta.givemetuna.domain.issuecomment.dto.read.IssueCommentSelectCondition;
import com.sparta.givemetuna.domain.issuecomment.entity.IssueComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IssueCommentCustomRepository {

	JPAQuery<IssueComment> queryAllBy(Long issueId, IssueCommentSelectCondition condition);

	Page<IssueCommentReadResponseDto> selectByConditionPaging(Long issueId, IssueCommentSelectCondition condition, Pageable pageable);
}
