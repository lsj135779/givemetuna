package com.sparta.givemetuna.domain.board.dto;

import com.sparta.givemetuna.domain.board.entity.Board;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
@AllArgsConstructor
@Getter
public class InviteUserResponseDto {

	private long boardId;

	private List<Invitation> invitations;

	public static InviteUserResponseDto of(Board board) {
		return new InviteUserResponseDto(
			board.getId(),
			board.getInvitedUserRole().stream()
				.map(
					boardUserRole -> new Invitation(
						boardUserRole.getUser().getAccount(),
						boardUserRole.getRole()
					))
				.toList()
		);
	}
}
