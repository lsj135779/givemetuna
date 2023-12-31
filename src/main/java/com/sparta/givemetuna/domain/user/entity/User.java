package com.sparta.givemetuna.domain.user.entity;

import com.sparta.givemetuna.domain.card.entity.UserCard;
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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {

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

	@OneToMany(mappedBy = "user")
	private List<Issue> issues = new ArrayList<>();

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Checklist> checklists = new ArrayList<>();

	@OneToMany(mappedBy = "user")
	private List<UserCard> userCards = new ArrayList<>();

	@OneToMany(mappedBy = "user")
	private List<BoardUserRole> boardUserRoles = new ArrayList<>();

	public User(String account, String password, String email, String nickname, String github, String description){
		this.account = account;
		this.password = password;
		this.email = email;
		this.nickname = nickname;
		this.github = github;
		this.description = description;
	}

    public void updateEmail(UserInfoRequestDTO userInfoRequestDTO) {
		if(userInfoRequestDTO.getEmail() !=null) {
			this.email = userInfoRequestDTO.getEmail();
		}
    }

	public void updateNickname(UserInfoRequestDTO userInfoRequestDTO) {
		if(userInfoRequestDTO.getNickname() !=null) {
			this.nickname = userInfoRequestDTO.getNickname();
		}
	}

	public void updateGithub(UserInfoRequestDTO userInfoRequestDTO) {
		if(userInfoRequestDTO.getGithub() !=null) {
			this.github = userInfoRequestDTO.getGithub();
		}
	}

	public void updatedescription(UserInfoRequestDTO userInfoRequestDTO) {
		if(userInfoRequestDTO.getDescription() !=null) {
			this.description = userInfoRequestDTO.getDescription();
		}
	}

	public void updatePassword(String password) {
		if(password !=null) {
			this.password = password;
		}
	}
}
