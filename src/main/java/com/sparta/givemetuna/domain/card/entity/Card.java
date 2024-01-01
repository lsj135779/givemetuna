package com.sparta.givemetuna.domain.card.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sparta.givemetuna.domain.checklist.entity.Checklist;
import com.sparta.givemetuna.domain.common.BaseEntity;
import com.sparta.givemetuna.domain.stage.entity.Stage;
import com.sparta.givemetuna.domain.user.entity.User;
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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "card")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@EqualsAndHashCode(of = "id", callSuper = false)
public class Card extends BaseEntity {

	@JsonIgnore
	@OneToMany(mappedBy = "card", targetEntity = Checklist.class, cascade = CascadeType.ALL, orphanRemoval = true)
	private final List<Checklist> checklists = new ArrayList<>();

	@ManyToOne(fetch = FetchType.LAZY)
	private Stage stage;

	@ManyToOne(fetch = FetchType.LAZY)
	private User creator;

	@ManyToOne(fetch = FetchType.LAZY)
	private User assignor;

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
}
