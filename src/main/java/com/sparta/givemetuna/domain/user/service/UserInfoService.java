package com.sparta.givemetuna.domain.user.service;

import com.sparta.givemetuna.domain.board.entity.Board;
import com.sparta.givemetuna.domain.board.repository.BoardRepository;
import com.sparta.givemetuna.domain.user.dto.UserInfoRequestDTO;
import com.sparta.givemetuna.domain.user.dto.UserInfoResponseDTO;
import com.sparta.givemetuna.domain.user.entity.User;
import com.sparta.givemetuna.domain.user.repository.UserRepository;
import java.util.concurrent.RejectedExecutionException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserInfoService {

	private final UserRepository userRepository;

	private final BoardRepository boardRepository;

	private final PasswordEncoder passwordEncoder;

	public UserInfoService(UserRepository userRepository, BoardRepository boardRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.boardRepository = boardRepository;
		this.passwordEncoder = passwordEncoder;
	}

	//사용자 조회
	public UserInfoResponseDTO getUserInfo(String account) {
		User user = getUser(account);
		return new UserInfoResponseDTO(user);
	}

	//사용자 수정
	public UserInfoResponseDTO updateUser(String account, UserInfoRequestDTO userInfoRequestDTO, User user) {
		User users = getUserInfos(account, user);

		if (userInfoRequestDTO.getPassword() != null) {
			String password = passwordEncoder.encode(userInfoRequestDTO.getPassword());
			users.updatePassword(password);
		}

		users.updateEmail(userInfoRequestDTO);
		users.updateNickname(userInfoRequestDTO);
		users.updateGithub(userInfoRequestDTO);
		users.updatedescription(userInfoRequestDTO);

		userRepository.save(users);

		return new UserInfoResponseDTO(users);
	}

	//사용자 삭제
	public void deleteUser(String account, User user) {
		User users = getUserInfos(account, user);

		userRepository.delete(users);
	}

	//사용자 존재여부 확인
	public User getUser(String account) {
		return userRepository.findByAccount(account)
			.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));
	}

	//사용자 인증 확인
	private User getUserInfos(String account, User user) {
		User users = getUser(account);

		if (!user.getId().equals(users.getId())) {
			throw new IllegalArgumentException("본인만 접근 가능합니다.");
		}
		return users;
	}

	//Board 사용자 검증
	public Board getUserBoard(Long id, User user) {
		//Board 존재여부 확인
		Board board = boardRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 보드입니다."));

		//Board작성자와 현재 사용자 검증
		if (!user.getId().equals(board.getUser().getId())) {
			throw new RejectedExecutionException("Board 작성자만 수정할 수 있습니다.");
		}
		return board;
	}
}
