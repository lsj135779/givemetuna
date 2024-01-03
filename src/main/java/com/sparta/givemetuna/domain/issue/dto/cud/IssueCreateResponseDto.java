package com.sparta.givemetuna.domain.issue.dto.cud;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.sparta.givemetuna.domain.issue.entity.Issue;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
public class IssueCreateResponseDto {

	private long issueId;

	private String title;

	private String contents;

	private long cardId;

	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "E, dd MMM yyyy HH:mm:ss z", timezone = "GMT+2")
	private LocalDateTime createdAt;

	public static IssueCreateResponseDto of(Issue issue) {
		return new IssueCreateResponseDto(
			issue.getId(),
			issue.getTitle(),
			issue.getContents(),
			issue.getCard().getId(),
			issue.getCreatedAt()
		);
	}
}