package com.sparta.givemetuna.domain.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sparta.givemetuna.domain.card.entity.Card;
import com.sparta.givemetuna.domain.checklist.entity.Checklist;
import com.sparta.givemetuna.domain.issue.entity.Issue;
import com.sparta.givemetuna.domain.user.dto.UserInfoRequestDTO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "user")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id", callSuper = false)
public class User {

	@OneToMany(mappedBy = "user")
	@JsonIgnore
	private final List<Issue> issues = new ArrayList<>();

	@OneToMany(mappedBy = "assignee", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private final List<Checklist> checklists = new ArrayList<>();

	@OneToMany(mappedBy = "creator", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private final List<Card> cardsCreated = new ArrayList<>();

	@OneToMany(mappedBy = "assignor", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private final List<Card> cardsAssignedTo = new ArrayList<>();

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private final List<BoardUserRole> boardUserRoles = new ArrayList<>();

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private String account;

	@Column
	private String password;

	@Column
	@Email
	private String email;

	@Column
	private String nickname;

	@Column
	@Email
	private String github;

	@Column
	private String description;

	public User(String account, String password, String email, String nickname, String github, String description) {
		this.account = account;
		this.password = password;
		this.email = email;
		this.nickname = nickname;
		this.github = github;
		this.description = description;
	}

	public void updateEmail(UserInfoRequestDTO userInfoRequestDTO) {
		if (userInfoRequestDTO.getEmail() != null) {
			this.email = userInfoRequestDTO.getEmail();
		}
	}

	public void updateNickname(UserInfoRequestDTO userInfoRequestDTO) {
		if (userInfoRequestDTO.getNickname() != null) {
			this.nickname = userInfoRequestDTO.getNickname();
		}
	}

	public void updateGithub(UserInfoRequestDTO userInfoRequestDTO) {
		if (userInfoRequestDTO.getGithub() != null) {
			this.github = userInfoRequestDTO.getGithub();
		}
	}

	public void updatedescription(UserInfoRequestDTO userInfoRequestDTO) {
		if (userInfoRequestDTO.getDescription() != null) {
			this.description = userInfoRequestDTO.getDescription();
		}
	}

	public void updatePassword(String password) {
		if (password != null) {
			this.password = password;
		}
	}
}
