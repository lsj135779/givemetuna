package com.sparta.givemetuna.domain.stage.dto;

import com.sparta.givemetuna.domain.stage.entity.Stage;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateStageResponseDto {
    private String category;

    public UpdateStageResponseDto(Stage stage) {
        this.category = stage.getCategory();
    }
}
