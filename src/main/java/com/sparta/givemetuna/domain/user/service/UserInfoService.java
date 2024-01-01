package com.sparta.givemetuna.domain.user.service;

import com.sparta.givemetuna.domain.user.dto.UserInfoRequestDTO;
import com.sparta.givemetuna.domain.user.dto.UserInfoResponseDTO;
import com.sparta.givemetuna.domain.user.entity.User;
import com.sparta.givemetuna.domain.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserInfoService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserInfoService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserInfoResponseDTO getUserInfo(String account) {
        User user = getUser(account);
        return new UserInfoResponseDTO(user);
    }

    public User getUser(String account) {
        return userRepository.findByAccount(account)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));
    }

    public UserInfoResponseDTO updateUser(String account, UserInfoRequestDTO userInfoRequestDTO, User user) {
        User users = getUserInfos(account, user);

        if(userInfoRequestDTO.getPassword() != null) {
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

    private User getUserInfos(String account, User user) {
        User users = getUser(account);

        if(!user.getId().equals(users.getId())) {
            throw new IllegalArgumentException("본인만 접근 가능합니다.");
        }
        return users;
    }
}
