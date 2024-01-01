package com.sparta.givemetuna.domain.user.service;

import com.sparta.givemetuna.domain.user.dto.SignUpRequestDTO;
import com.sparta.givemetuna.domain.user.entity.User;
import com.sparta.givemetuna.domain.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	private final PasswordEncoder passwordEncoder;

	private final UserRepository userRepository;

	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	//회원가입
	@Transactional
	public void signup(SignUpRequestDTO signUpRequestDTO) {
		String account = signUpRequestDTO.getAccount();
		String password = passwordEncoder.encode(signUpRequestDTO.getPassword());
		String email = signUpRequestDTO.getEmail();
		String nickname = signUpRequestDTO.getNickname();
		String github = signUpRequestDTO.getGithub();
		String description = signUpRequestDTO.getDescription();

		if (userRepository.findByAccount(account).isPresent()) {
			throw new IllegalArgumentException("이미 가입된 아이디 입니다.");
		}

		if (userRepository.findByEmail(email).isPresent()) {
			throw new IllegalArgumentException("이미 가입된 이메일 입니다.");
		}

		if (userRepository.findByNickname(nickname).isPresent()) {
			throw new IllegalArgumentException("이미 가입된 닉네임 입니다.");
		}

		User user = new User(account, password, email, nickname, github, description);
		userRepository.save(user);
	}

	//로그인
	public void login(SignUpRequestDTO signUpRequestDTO) {
		String account = signUpRequestDTO.getAccount();
		String password = signUpRequestDTO.getPassword();

		User user = userRepository.findByAccount(account)
			.orElseThrow(() -> new IllegalArgumentException("등록된 유저가 없습니다."));

		if (!passwordEncoder.matches(password, user.getPassword())) {
			throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
		}
	}

	public User checkUser(Long boardId, User user) {
		return null;
	}

	public User findbyAccount(String assignorAccount) {
		return null;
	}
}
