package com.sparta.givemetuna.domain.card.dto.request;

import jakarta.validation.constraints.Size;
import java.sql.Timestamp;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
public class CreateCardRequestDto {

    @Size(max = 500)
    private String title;

    private String account;

    private Integer priority;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp startedAt;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp closedAt;
}
