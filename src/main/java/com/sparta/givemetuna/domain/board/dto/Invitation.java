package com.sparta.givemetuna.domain.board.dto;


import com.sparta.givemetuna.domain.user.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@EqualsAndHashCode
public class Invitation {

	private String userAccount;

	private Role role;
}
