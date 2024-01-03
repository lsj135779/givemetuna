package com.sparta.givemetuna.domain.user.service;

import com.sparta.givemetuna.domain.board.dto.Invitation;
import com.sparta.givemetuna.domain.security.UserDetailsImpl;
import com.sparta.givemetuna.domain.user.dto.SignUpRequestDTO;
import com.sparta.givemetuna.domain.user.entity.Role;
import com.sparta.givemetuna.domain.user.entity.User;
import com.sparta.givemetuna.domain.user.exception.LoginInvalidAccountException;
import com.sparta.givemetuna.domain.user.exception.LoginInvalidPasswordException;
import com.sparta.givemetuna.domain.user.exception.SelectUserNotFoundException;
import com.sparta.givemetuna.domain.user.exception.SignUpDuplicatedUserAccountException;
import com.sparta.givemetuna.domain.user.exception.SignUpDuplicatedUserEmailException;
import com.sparta.givemetuna.domain.user.exception.SignUpDuplicatedUserNicknameException;
import com.sparta.givemetuna.domain.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
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
			throw new SignUpDuplicatedUserAccountException();
		}

		if (userRepository.findByEmail(email).isPresent()) {
			throw new SignUpDuplicatedUserEmailException();
		}

		if (userRepository.findByNickname(nickname).isPresent()) {
			throw new SignUpDuplicatedUserNicknameException();
		}

		User user = new User(account, password, email, nickname, github, description);
		userRepository.save(user);
	}

	//로그인
	public void login(SignUpRequestDTO signUpRequestDTO) {
		String account = signUpRequestDTO.getAccount();
		String password = signUpRequestDTO.getPassword();

		User user = userRepository.findByAccount(account)
			.orElseThrow(LoginInvalidAccountException::new);

		if (!passwordEncoder.matches(password, user.getPassword())) {
			throw new LoginInvalidPasswordException();
		}
	}

	public User findByUserDetails(UserDetailsImpl userDetails) {
		return userRepository.findById(userDetails.getUser().getId())
			.orElseThrow(SelectUserNotFoundException::new);
	}

	public User findByAccount(String account) {
		return userRepository.findByAccount(account)
			.orElseThrow(SelectUserNotFoundException::new);
	}

	public Map<User, Role> findbyAccountsWithRole(List<Invitation> invitations) {
		Map<User, Role> users = new HashMap<>();
		invitations.forEach(invitation ->
			users.put(
				findByAccount(invitation.getUserAccount()),
				invitation.getRole()
			));
		return users;
	}
}
