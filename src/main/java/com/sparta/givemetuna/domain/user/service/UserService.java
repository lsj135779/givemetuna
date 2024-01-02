package com.sparta.givemetuna.domain.user.service;

import com.sparta.givemetuna.domain.user.entity.User;
import com.sparta.givemetuna.domain.user.exception.*;
import com.sparta.givemetuna.domain.user.repository.UserRepository;
import com.sparta.givemetuna.domain.user.dto.SignUpRequestDTO;
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
    public void signup(SignUpRequestDTO signUpRequestDTO){
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

        if(!passwordEncoder.matches(password, user.getPassword())) {
            throw new LoginInvalidPasswordException();
        }
    }
}
