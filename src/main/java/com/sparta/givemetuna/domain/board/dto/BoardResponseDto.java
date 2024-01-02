package com.sparta.givemetuna.domain.board.dto;

import com.sparta.givemetuna.domain.board.entity.Board;
import com.sparta.givemetuna.domain.stage.entity.Stage;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class BoardResponseDto {

	private long boardId;

	private String name;

	private List<String> stages;

	public BoardResponseDto(Board board) {
		this.name = board.getName();
	}

	public static BoardResponseDto of(Board board) {
		return new BoardResponseDto(
			board.getId(),
			board.getName(),
			board.getStages().stream()
				.map(Stage::getCategory)
				.toList()
		);
	}
}
