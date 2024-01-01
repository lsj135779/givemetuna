package com.sparta.givemetuna.domain.security;

import com.sparta.givemetuna.domain.user.entity.User;
import com.sparta.givemetuna.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl {

    private final UserRepository userRepository;

    public UserDetailsImpl getUserDetails(String account) {
        User user = userRepository.findByAccount(account)
                .orElseThrow(() -> new UsernameNotFoundException("Not Found" + account));
        return new UserDetailsImpl(user);
    }
}