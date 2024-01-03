package com.sparta.givemetuna.domain.stage.dto;

import com.sparta.givemetuna.domain.stage.entity.Stage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateStageResponseDto {

	private Long id;

	private String category;

	public CreateStageResponseDto(Stage stage) {
		this.id = stage.getId();
		this.category = stage.getCategory();
	}
}
