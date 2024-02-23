package com.example.shopping.Security.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.web.multipart.MultipartFile;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;    // name
    private String password;    // password

    private String nickname;    // 1. 닉네임
    private String name;        // 2. 이름
    private Integer age;        // 3. 나이
    private String email;       // 4. 이메일
    private Integer phone;      // 5. 전화번호
//    private MultipartFile profileImage; // 프로필 이미지 업로드용
}
