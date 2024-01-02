package com.sparta.givemetuna.domain.user.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.givemetuna.domain.jwt.JwtUtil;
import com.sparta.givemetuna.domain.user.dto.KakaoUserInfoDTO;
import com.sparta.givemetuna.domain.user.dto.UserInfoRequestDTO;
import com.sparta.givemetuna.domain.user.dto.UserInfoResponseDTO;
import com.sparta.givemetuna.domain.user.entity.User;
import com.sparta.givemetuna.domain.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.HttpHeaders;

import java.net.URI;
import java.util.UUID;


@Slf4j(topic = "Kakao Login")
@Service
public class UserInfoService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final RestTemplate restTemplate;

    public UserInfoService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil, RestTemplate restTemplate) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.restTemplate = restTemplate;
    }

    //사용자 조회
    public UserInfoResponseDTO getUserInfo(String account) {
        User user = getUser(account);
        return new UserInfoResponseDTO(user);
    }

    //사용자 수정
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

        if(!user.getId().equals(users.getId())) {
            throw new IllegalArgumentException("본인만 접근 가능합니다.");
        }
        return users;
    }

    //카카오 로그인
    public String kakaoLogin(String code) throws JsonProcessingException {

        // 1. "인가 코드"로 "액세스 토큰" 요청
        String accessToken = getToken(code);

        // 2. 토큰으로 카카오 API 호출 : "액세스 토큰"으로 "카카오 사용자 정보" 가져오기
        KakaoUserInfoDTO kakaoUserInfoDTO = getKakaoUserInfo(accessToken);

        // 3. 필요시 회원가입
        User kakaoUser = registerKakaoUserIfNeeded(kakaoUserInfoDTO);

        // 4. JWT 토큰 생성
        String createToken = jwtUtil.createToken(kakaoUser.getAccount());

        // 생성 토큰 반환
        return createToken;
    }

    private String getToken(String code) throws JsonProcessingException {

        System.out.println("code: "+ code);
        // 요청 URL 만들기
        URI uri = UriComponentsBuilder
                .fromUriString("https://kauth.kakao.com")
                .path("/oauth/token")
                .encode()
                .build()
                .toUri();

        // HTTP Header 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // HTTP Body 생성
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("client_id", "fabb093db7a3847a20ff1dd7de7bc20e");
        body.add("redirect_uri", "http://localhost:8080/api/users/kakao/callback");
        body.add("code", code);

        RequestEntity<MultiValueMap<String, String>> requestEntity = RequestEntity
                .post(uri)
                .headers(headers)
                .body(body);

        // HTTP 요청 보내기
        ResponseEntity<String> response = restTemplate.exchange(
                requestEntity,
                String.class
        );

        // HTTP 응답 (JSON) -> 액세스 토큰 파싱
        JsonNode jsonNode = new ObjectMapper().readTree(response.getBody());
        return jsonNode.get("access_token").asText();
    }

    private KakaoUserInfoDTO getKakaoUserInfo(String accessToken) throws JsonProcessingException {
        log.info("accessToken : " + accessToken);
        // 요청 URL 만들기
        URI uri = UriComponentsBuilder
                .fromUriString("https://kapi.kakao.com")
                .path("/v2/user/me")
                .encode()
                .build()
                .toUri();

        // HTTP Header 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        RequestEntity<MultiValueMap<String, String>> requestEntity = RequestEntity
                .post(uri)
                .headers(headers)
                .body(new LinkedMultiValueMap<>()); //body에 따로 보내줄 필요가 없음

        // HTTP 요청 보내기
        ResponseEntity<String> response = restTemplate.exchange(
                requestEntity,
                String.class
        );

        JsonNode jsonNode = new ObjectMapper().readTree(response.getBody());
        Long id = jsonNode.get("id").asLong(); //Long타입으로 해당하는 id값 받아옴
        String nickname = jsonNode.get("properties")
                .get("nickname").asText();//닉네임 값 가져옴
        String email = jsonNode.get("kakao_account")
                .get("email").asText();//이메일 값 가져옴
        return new KakaoUserInfoDTO(id, nickname, email);
    }

    //회원가입 조건
    private User registerKakaoUserIfNeeded(KakaoUserInfoDTO kakaoUserInfo) {
        // DB 에 중복된 Kakao Id 가 있는지 확인
        Long kakaoId = kakaoUserInfo.getId();
        //kakaoId가 없다면 null 반환
        User kakaoUser = userRepository.findByKakaoId(kakaoId).orElse(null);
        //null일시 회원가입 시작
        if (kakaoUser == null) {
            // 카카오 사용자 email 동일한 email 가진 회원이 있는지 확인
            String kakaoEmail = kakaoUserInfo.getEmail();
            User sameEmailUser = userRepository.findByEmail(kakaoEmail).orElse(null);
            if (sameEmailUser != null) {
                kakaoUser = sameEmailUser;
                // 기존 회원정보에 카카오 Id 추가
                kakaoUser = kakaoUser.kakaoIdUpdate(kakaoId);
            } else {
                // 신규 회원가입
                // password: random UUID
                String password = UUID.randomUUID().toString();
                String encodedPassword = passwordEncoder.encode(password);

                // email: kakao email
                String email = kakaoUserInfo.getEmail();

                kakaoUser = new User(kakaoUserInfo.getId(), encodedPassword, email, kakaoUserInfo.getNickname());
            }

            userRepository.save(kakaoUser);
        }
        return kakaoUser;
    }
}
