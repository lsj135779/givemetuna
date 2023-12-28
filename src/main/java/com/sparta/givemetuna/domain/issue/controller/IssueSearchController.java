package com.sparta.givemetuna.domain.issue.controller;

import com.sparta.givemetuna.domain.issue.service.IssueSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/issues")
@EnableWebMvc
public class IssueSearchController {

	@Qualifier("issueJpaSearchService")
	private final IssueSearchService issueSearchService;
}

