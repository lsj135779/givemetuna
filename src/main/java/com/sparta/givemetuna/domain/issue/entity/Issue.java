package com.sparta.givemetuna.domain.issue.entity;

import com.sparta.givemetuna.domain.card.entity.Card;
import com.sparta.givemetuna.domain.common.BaseEntity;
import com.sparta.givemetuna.domain.issue.dto.IssueCreateRequestDto;
import com.sparta.givemetuna.domain.user.entity.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "issue")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Issue extends BaseEntity {

	@OneToMany(mappedBy = "issue", cascade = CascadeType.ALL, orphanRemoval = true)
	private final List<IssueComment> issueComments = new ArrayList<>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "card_id")
	private Card card;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String title;

	@Column
	private String contents;

	@Enumerated(EnumType.STRING)
	private Status status;

//	@Column
//	private Boolean isClosed;

	public Issue(String title, String contents, Status status, Card card, User user) {
		this.title = title;
		this.contents = contents;
		this.status = status;
		this.card = card;
		this.user = user;
	}

	public Issue(String title, String contents, Status status) {
		this.title = title;
		this.contents = contents;
		this.status = status;
	}

	public static Issue of(IssueCreateRequestDto requestDto, Card card, User user) {
		return new Issue(
			requestDto.getTitle(),
			requestDto.getContents(),
			Status.OPEN,
			card,
			user
		);
	}
}
