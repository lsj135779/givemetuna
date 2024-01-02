package com.sparta.givemetuna.domain.issuecomment.dto.cud;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.sparta.givemetuna.domain.issuecomment.entity.IssueComment;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
public class IssueCommentCreateResponseDto {

	private long userId;

	private long issueId;

	private String contents;

	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "E, dd MMM yyyy HH:mm:ss z", timezone = "GMT+2")
	private LocalDateTime createdAt;

	public static IssueCommentCreateResponseDto of(IssueComment issueComment) {
		return new IssueCommentCreateResponseDto(
			issueComment.getUser().getId(),
			issueComment.getIssue().getId(),
			issueComment.getContents(),
			issueComment.getCreatedAt());
	}
}
