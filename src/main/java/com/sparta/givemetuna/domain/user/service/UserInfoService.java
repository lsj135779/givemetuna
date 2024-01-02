package com.sparta.givemetuna.domain.user.service;

import com.sparta.givemetuna.domain.board.entity.Board;
import com.sparta.givemetuna.domain.board.exception.BoardInvalidAuthorizationException;
import com.sparta.givemetuna.domain.board.exception.SelectBoardNotFoundException;
import com.sparta.givemetuna.domain.board.repository.BoardRepository;
import com.sparta.givemetuna.domain.user.dto.UserInfoRequestDTO;
import com.sparta.givemetuna.domain.user.dto.UserInfoResponseDTO;
import com.sparta.givemetuna.domain.user.entity.User;
import com.sparta.givemetuna.domain.user.exception.*;
import com.sparta.givemetuna.domain.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

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

        // Password Valid
        String password = passwordEncoder.encode(userInfoRequestDTO.getPassword());

        if(!passwordEncoder.matches(userInfoRequestDTO.getPassword(), user.getPassword())){
            users.updatePassword(password);
        } else{
            throw new UpdateIdenticalPasswordException();
        }

        //Email Valid
        if(!Objects.equals(user.getEmail(), userInfoRequestDTO.getEmail())){
            users.updateEmail(userInfoRequestDTO);
        } else{
            throw new UpdateIdenticalEmailException();
        }

        //Nickname Valid
        if(!Objects.equals(user.getNickname(), userInfoRequestDTO.getNickname())){
            users.updateNickname(userInfoRequestDTO);
        } else{
            throw new UpdateIdenticalAccountException();
        }

        //Github Valid
        if(!Objects.equals(user.getGithub(), userInfoRequestDTO.getGithub())){
            users.updateGithub(userInfoRequestDTO);
        } else{
            throw new UpdateIdenticalEmailException();
        }

        //Intro Valid
        if(!Objects.equals(user.getDescription(), userInfoRequestDTO.getDescription())){
            users.updatedescription(userInfoRequestDTO);
        } else{
            throw new UpdateIdenticalIntroductionException();
        }

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
                .orElseThrow(SelectUserNotFoundException::new);
    }

    //사용자 인증 확인
    private User getUserInfos(String account, User user) {
        User users = getUser(account);

        if(!user.getId().equals(users.getId())) {
            throw new UpdateUserInvalidAuthorizationException();
        }
        return users;
    }

    //Board 사용자 검증
    public Board getUserBoard(Long id, User user) {
        //Board 존재여부 확인
        Board board = boardRepository.findById(id)
                .orElseThrow(SelectBoardNotFoundException::new);

        //Board작성자와 현재 사용자 검증
        if(!user.getId().equals(board.getUser().getId())) {
            throw new BoardInvalidAuthorizationException();
        }
        return board;
    }
}
