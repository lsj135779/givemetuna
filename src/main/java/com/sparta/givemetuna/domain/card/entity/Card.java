package com.sparta.givemetuna.domain.card.entity;

import com.sparta.givemetuna.domain.checklist.entity.Checklist;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "card")
public class Card {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String title;

	@Column
	private Integer priority;

	@Column
	private Boolean isDone;

	@Column
	private Timestamp startedAt;

	@Column
	private Timestamp closedAt;

	@OneToMany(mappedBy = "card", targetEntity = UserCard.class, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<UserCard> userCards = new ArrayList<>();

	@OneToMany(mappedBy = "card", targetEntity = Checklist.class, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Checklist> checklists = new ArrayList<>();
}
