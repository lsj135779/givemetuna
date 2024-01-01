package com.sparta.givemetuna.domain.issue.repository;

import com.sparta.givemetuna.domain.issue.dto.read.IssueReadResponseDto;
import com.sparta.givemetuna.domain.issue.dto.read.IssueSelectCondition;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IssueRepositoryCustom {

	List<IssueReadResponseDto> selectByCondition(IssueSelectCondition condition);

	Page<IssueReadResponseDto> selectByConditionPaging(IssueSelectCondition condition, Pageable pageable);

}
