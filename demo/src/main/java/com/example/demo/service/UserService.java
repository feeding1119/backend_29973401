package com.example.demo.service;

import com.example.demo.dto.LoginRequestDto;
import com.example.demo.dto.SignupRequestDto;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.uitl.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void signup(SignupRequestDto request){
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        if(userRepository.findByUserId(request.getUserId()).isPresent()) throw new RuntimeException("이미 사용중인 ID입니다.");

        User user = User.builder()
                .userId(request.getUserId())
                .password(encodedPassword)
                .build();

        userRepository.save(user);
    }

    public String authenticate(LoginRequestDto request) {
        User user = userRepository.findByUserId(request.getUserId())
                .orElseThrow(() -> new RuntimeException("존재하지 않는 ID 입니다."));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) throw new RuntimeException("비밀번호가 일치하지 않습니다");

        String token = jwtTokenProvider.createToken(user.getUserId());

        return token;
    }

}
