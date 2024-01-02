package com.sparta.givemetuna.domain.issue.controller.validation;

import com.sparta.givemetuna.domain.issue.service.cud.IssueCudService;
import com.sparta.givemetuna.domain.issue.service.read.IssueReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/issues")
@EnableWebMvc
public abstract class IssueValidator {

	//	@Qualifier("issueJpaCudService")
	private final IssueCudService issueCudService;

	private final IssueReadService issueReadService;

//	private final CardService cardService;

}

