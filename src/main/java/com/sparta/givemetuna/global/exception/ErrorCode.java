package com.sparta.givemetuna.global.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@JsonFormat(shape = Shape.OBJECT)
public enum ErrorCode {
	/* ARGS ERROR */
	INVALID_PARAM(HttpStatus.BAD_REQUEST, "잘못된 형식의 입력값입니다."),
	/* AUTHORIZATION */
	ACCESS_DENIED(HttpStatus.FORBIDDEN, "권한이 필요한 요청입니다."),
	/* USER */
	SIGNUP_DUPLICATED_USER_ACCOUNT(HttpStatus.CONFLICT, "이미 존재하는 아이디입니다."),
	SIGNUP_DUPLICATED_USER_NICKNAME(HttpStatus.CONFLICT, "이미 존재하는 닉네임입니다."),
	SIGNUP_DUPLICATED_USER_EMAIL(HttpStatus.CONFLICT, "이미 존재하는 이메일입니다."),
	LOGIN_INVALID_ACCOUNT(HttpStatus.UNAUTHORIZED, "올바르지 않은 아이디로 로그인을 시도하셨습니다."),
	LOGIN_INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "올바르지 않은 비밀번호로 로그인을 시도하셨습니다."),
	UPDATE_IDENTICAL_ACCOUNT(HttpStatus.CONFLICT, "이전과 동일한 아이디를 입력하셨습니다."),
	UPDATE_IDENTICAL_PASSWORD(HttpStatus.CONFLICT, "이전과 동일한 비밀번호를 입력하셨습니다."),
	UPDATE_IDENTICAL_EMAIL(HttpStatus.CONFLICT, "이전과 동일한 이메일을 입력하셨습니다."),
	UPDATE_IDENTICAL_INTRODUCTION(HttpStatus.CONFLICT, "이전과 동일한 자기소개을 입력하셨습니다."),
	SELECT_USER_NOT_FOUND(HttpStatus.NOT_FOUND, "찾으시는 회원은 존재하지 않습니다."),
	DELETE_USER_NOT_FOUND(HttpStatus.NOT_FOUND, "삭제하시려는 회원은 존재하지 않습니다."),
	UPDATE_USER_INVALID_AUTHORIZATION(HttpStatus.FORBIDDEN, "해당 회원의 수정 권한이 없습니다."),
	DELETE_USER_INVALID_AUTHORIZATION(HttpStatus.FORBIDDEN, "해당 회원의 삭제 권한이 없습니다."),
	/* ISSUE */
	SELECT_ISSUE_INVALID_ORDER_CRITERIA(HttpStatus.BAD_REQUEST, "이슈에 대한 올바른 정렬기준값을 입력해주세요."),
	SELECT_ISSUE_NOT_FOUND(HttpStatus.NOT_FOUND, "찾으시는 이슈는 존재하지 않습니다."),
	UPDATE_ISSUE_INVALID_AUTHORIZATION(HttpStatus.NOT_FOUND, "해당 이슈의 수정 권한이 없습니다."),
	CLOSE_ISSUE_INVALID_AUTHORIZATION(HttpStatus.NOT_FOUND, "해당 이슈의 종료 권한이 없습니다."),
	DELETE_ISSUE_INVALID_AUTHORIZATION(HttpStatus.NOT_FOUND, "해당 이슈의 삭제 권한이 없습니다."),
	SELECT_ISSUE_COMMENT_INVALID_ORDER_CRITERIA(HttpStatus.BAD_REQUEST, "댓글에 대한 올바른 정렬기준값을 입력해주세요."),
	UPDATE_ISSUE_COMMENT_INVALID_AUTHORIZATION(HttpStatus.FORBIDDEN, "해당 이슈 댓글의 수정 권한이 없습니다."),
	DELETE_ISSUE_COMMENT_INVALID_AUTHORIZATION(HttpStatus.FORBIDDEN, "해당 이슈 댓글의 삭제 권한이 없습니다."),
	/* CHECKLIST */
	UPDATE_CHECKLIST_INVALID_AUTHORIZATION(HttpStatus.NOT_FOUND, "해당 체크리스트의 수정 권한이 없습니다."),
	DELETE_CHECKLIST_INVALID_AUTHORIZATION(HttpStatus.NOT_FOUND, "해당 체크리스트의 삭제 권한이 없습니다."),
	/* STAGE */
	SELECT_STAGE_NOT_FOUND(HttpStatus.NOT_FOUND, "찾으시는 스테이지는 존재하지 않습니다."),
	UPDATE_STAGE_NOT_FOUND(HttpStatus.NOT_FOUND, "수정하시려는 스테이지는 존재하지 않습니다."),
	DELETE_STAGE_NOT_FOUND(HttpStatus.NOT_FOUND, "삭제하시려는 스테이지는 존재하지 않습니다."),
	CREATE_STAGE_INVALID_AUTHORIZATION(HttpStatus.FORBIDDEN, "스테이지의 생성 권한이 없습니다."),
	SELECT_STAGE_INVALID_AUTHORIZATION(HttpStatus.FORBIDDEN, "해당 스테이지의 조회 권한이 없습니다."),
	UPDATE_STAGE_INVALID_AUTHORIZATION(HttpStatus.FORBIDDEN, "해당 스테이지의 수정 권한이 없습니다."),
	DELETE_STAGE_INVALID_AUTHORIZATION(HttpStatus.FORBIDDEN, "해당 스테이지의 삭제 권한이 없습니다."),
	/* CARD */
	CARD_ASSIGNOR_INVALID_AUTHORIZATION(HttpStatus.FORBIDDEN, "카드에 대한 Assignor 권한이 없습니다."),
	CARD_ASSIGNEE_INVALID_AUTHORIZATION(HttpStatus.FORBIDDEN, "카드에 대한 Assignee 권한이 없습니다."),
	SELECT_CARD_INVALID_ORDER_CRITERIA(HttpStatus.BAD_REQUEST, "카드에 대한 올바른 형식의 정렬기준값을 입력해주세요."),
	CARD_INVALID_AUTHORIZATION(HttpStatus.BAD_REQUEST, "Card 요청 처리 시, 총 관리자 혹은 팀 매니저만 접근할 수 있습니다."),
	/* BOARD */
	SELECT_BOARD_NOT_FOUND(HttpStatus.NOT_FOUND, "찾으시는 보드는 존재하지 않습니다."),
	UPDATE_BOARD_NOT_FOUND(HttpStatus.NOT_FOUND, "수정하시려는 보드는 존재하지 않습니다."),
	DELETE_BOARD_NOT_FOUND(HttpStatus.NOT_FOUND, "삭제하시려는 보드는 존재하지 않습니다."),
	BOARD_INVALID_AUTHORIZATION(HttpStatus.FORBIDDEN, "보드의 권한이 없습니다."),
	CREATE_BOARD_INVALID_AUTHORIZATION(HttpStatus.FORBIDDEN, "보드의 생성 권한이 없습니다."),
	SELECT_BOARD_INVALID_AUTHORIZATION(HttpStatus.FORBIDDEN, "해당 보드의 조회 권한이 없습니다."),
	UPDATE_BOARD_INVALID_AUTHORIZATION(HttpStatus.FORBIDDEN, "해당 보드의 수정 권한이 없습니다."),
	DELETE_BOARD_INVALID_AUTHORIZATION(HttpStatus.FORBIDDEN, "해당 보드의 삭제 권한이 없습니다.");

	private final HttpStatus code;

	private final String message;

	ErrorCode(HttpStatus code, String message) {
		this.code = code;
		this.message = message;
	}
}
