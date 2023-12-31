package com.sparta.givemetuna.domain.issuecomment.service.read;

import com.sparta.givemetuna.domain.issuecomment.repository.IssueCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IssueCommentReadServiceImpl implements IssueCommentReadService {

	private final IssueCommentRepository issueCommentRepository;
}
