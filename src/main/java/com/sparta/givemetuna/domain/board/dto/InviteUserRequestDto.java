package com.sparta.givemetuna.domain.board.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class InviteUserRequestDto {

	private List<Invitation> invitations;
}
