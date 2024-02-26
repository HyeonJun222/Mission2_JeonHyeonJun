package com.example.shopping.Security.Controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {
    // HOME index
    @GetMapping("/home")
    public String home() {
        // 홈 화면에서 누가 로그인했는지 볼 수 있음.
        // 로그인 안했으면 annoymousUser 로 표기됨
        // 로그인 했으면 유저 이름 표기
        log.info(SecurityContextHolder.getContext().getAuthentication().getName());
        return "index";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login-form";
    }
    
    // 로그인 화면
    @GetMapping("/my-profile")
    public String myProfile(Authentication authentication) {
        // 누가 접속했는지 알기가 어려워서 추가하는 로그
        log.info(authentication.getName());
        log.info(((User) authentication.getPrincipal()).getUsername());
        return "my-profile";
    }
    
    // 회원가입 페이지 
    @GetMapping("/register")
    public String signUpForm(){
        return "register-form";
    }

    // 유저 회원가입 만들 준비
    private final UserDetailsManager manager;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public String signUpRequest(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("password-check") String passwordCheck
    ) {
        // 회원가입
        if (password.equals(passwordCheck))
            manager.createUser(User.withUsername(username)
                    .password(passwordEncoder.encode(password))
                    .build());

        // 회원가입 성공 후 로그인 페이지로
        return "redirect:/users/login";
    }


}