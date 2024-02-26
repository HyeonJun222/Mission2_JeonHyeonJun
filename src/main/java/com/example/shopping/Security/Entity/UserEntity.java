package com.example.shopping.Security.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "user table")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;    // name

    @Column(nullable = false)
    private String password;    // password

    private String nickname;    // 1. 닉네임
    private String name;        // 2. 이름
    private Integer age;        // 3. 나이
    private String email;       // 4. 이메일
    private String phone;      // 5. 전화번호

//    private MultipartFile profileImage; // 프로필 이미지 업로드용
}
