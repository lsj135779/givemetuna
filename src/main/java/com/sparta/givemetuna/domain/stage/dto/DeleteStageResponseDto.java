package com.sparta.givemetuna.domain.stage.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL) //parsing할때 null값은 알아서 제외
public class DeleteStageResponseDto {
    private String msg;
}
