package com.sparta.givemetuna.domain.board.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sparta.givemetuna.domain.board.dto.CreateBoardRequestDto;
import com.sparta.givemetuna.domain.stage.entity.Stage;
import com.sparta.givemetuna.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "board")
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String name;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@OneToMany(mappedBy = "board", targetEntity = Stage.class, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Stage> stages = new ArrayList<>();

	public Board(CreateBoardRequestDto requestDto) {
		this.name = requestDto.getName();
	}

	public void setUser(User user) { this.user = user; }


	public void setName(String name) {
		this.name = name;
	}
}
