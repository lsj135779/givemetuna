package com.sparta.givemetuna.domain.card.entity;

import com.sparta.givemetuna.domain.checklist.entity.Checklist;
import com.sparta.givemetuna.domain.stage.entity.Stage;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "card")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Card {

	@OneToMany(mappedBy = "card", targetEntity = UserCard.class, cascade = CascadeType.ALL, orphanRemoval = true)
	private final List<UserCard> userCards = new ArrayList<>();

	@OneToMany(mappedBy = "card", targetEntity = Checklist.class, cascade = CascadeType.ALL, orphanRemoval = true)
	private final List<Checklist> checklists = new ArrayList<>();

	@ManyToOne(fetch = FetchType.LAZY)
	private Stage stage;

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

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Card card)) {
			return false;
		}
		return Objects.equals(getId(), card.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId());
	}
}
