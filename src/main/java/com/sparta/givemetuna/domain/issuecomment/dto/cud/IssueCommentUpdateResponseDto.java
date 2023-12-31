package com.sparta.givemetuna.domain.issuecomment.dto.cud;


import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
public class IssueCommentUpdateResponseDto {

	private long userId;

	private long issueId;

	private String contents;

	private LocalDateTime updatedAt;
}
