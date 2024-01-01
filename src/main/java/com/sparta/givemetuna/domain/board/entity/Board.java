package com.sparta.givemetuna.domain.board.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sparta.givemetuna.domain.board.dto.CreateBoardRequestDto;
import com.sparta.givemetuna.domain.stage.entity.Stage;
import com.sparta.givemetuna.domain.user.entity.BoardUserRole;
import com.sparta.givemetuna.domain.user.entity.Role;
import com.sparta.givemetuna.domain.user.entity.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "board")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Board {

	@OneToMany(mappedBy = "board", targetEntity = Stage.class, cascade = CascadeType.ALL, orphanRemoval = true)
	private final List<Stage> stages = new ArrayList<>();

	@OneToMany(mappedBy = "board", targetEntity = BoardUserRole.class, cascade = CascadeType.ALL, orphanRemoval = true)
	private final List<BoardUserRole> invitedUserRole = new ArrayList<>();

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String name;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	public Board(CreateBoardRequestDto requestDto) {
		this.name = requestDto.getName();
	}

	public void setUser(User user) {
		this.user = user;
	}


	public void setName(String name) {
		this.name = name;
	}

	public void addUsersWithRole(Map<User, Role> usersWithRole) {
		for (User user : usersWithRole.keySet()) {
			addUserWithRole(user, usersWithRole.get(user));
		}
	}

	private void addUserWithRole(User user, Role role) {
		BoardUserRole boardUserRole = new BoardUserRole(user, role, this);
		invitedUserRole.add(boardUserRole);
		user.getBoardUserRoles().add(boardUserRole);
	}
}
