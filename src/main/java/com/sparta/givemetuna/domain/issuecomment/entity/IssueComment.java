package com.sparta.givemetuna.domain.issuecomment.entity;

import com.sparta.givemetuna.domain.common.BaseEntity;
import com.sparta.givemetuna.domain.issue.entity.Issue;
import com.sparta.givemetuna.domain.issuecomment.dto.cud.IssueCommentCreateRequestDto;
import com.sparta.givemetuna.domain.user.entity.User;
import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "issueComment")
@NoArgsConstructor
@Getter
public class IssueComment extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String contents;

	@Nonnull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@Nonnull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "issue_id")
//	@Fetch(FetchMode.JOIN)
	private Issue issue;

	public IssueComment(String contents, User user, Issue issue) {
		this.contents = contents;
		this.user = user;
		this.issue = issue;
		this.issue.getIssueComments().add(this);
	}

	public static IssueComment of(User user, Issue issue, IssueCommentCreateRequestDto requestDto) {
		return new IssueComment(requestDto.getContents(), user, issue);
	}
}
