package com.sparta.givemetuna.domain.card.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sparta.givemetuna.domain.card.constant.CardPriority;
import com.sparta.givemetuna.domain.checklist.entity.Checklist;
import com.sparta.givemetuna.domain.common.BaseEntity;
import com.sparta.givemetuna.domain.stage.entity.Stage;
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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "card")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Card extends BaseEntity {

	@JsonIgnore
	@OneToMany(mappedBy = "card", targetEntity = Checklist.class, cascade = CascadeType.ALL, orphanRemoval = true)
	private final List<Checklist> checklists = new ArrayList<>();

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User creator; // 카드 만든 사람

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User assignor; // 카드 받은 사람 = 담당자

	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private CardPriority cardPriority;

	@Column
	private Timestamp startedAt;

	@Column
	private Timestamp closedAt;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "stage_id")
	private Stage stage;


	@Builder
	private Card(Long id, User creator, User assignor, Stage stage, String title,
		CardPriority cardPriority, Timestamp startedAt, Timestamp closedAt) {
		this.id = id;
		this.creator = creator;
		this.assignor = assignor;
		this.stage = stage;
		this.title = title;
		this.cardPriority = cardPriority;
		this.startedAt = startedAt;
		this.closedAt = closedAt;
	}

	public void updateStage(Stage stage) {
		this.stage = stage;
	}

	public void updateTitle(String title) {
		this.title = title;
	}

	public void updateAssignorAccount(User assignor) {
		this.assignor = assignor;
	}

	public void updatePriority(CardPriority cardPriority) {
		this.cardPriority = cardPriority;
	}

	public void updatePeriod(Timestamp startedAt, Timestamp closedAt) {
		this.startedAt = startedAt;
		this.closedAt = closedAt;
	}
}
