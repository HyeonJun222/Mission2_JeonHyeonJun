package com.example.shopping.Security.Service;

import com.example.shopping.Security.Repository.UserRepository;
import com.example.shopping.Security.Entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Slf4j
//@RequiredArgsConstructor
@Service
public class JpaUserDetailsManager implements UserDetailsManager {
    private final UserRepository userRepository;

    public JpaUserDetailsManager(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        createUser(User.withUsername("user")
                .password(passwordEncoder.encode("password"))
                .build());
    }


    @Override
    public void createUser(UserDetails user) {
        if (this.userExists(user.getUsername())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        UserEntity userEntity = UserEntity.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .build();
        userRepository.save(userEntity);
    }

    @Override
    public void updateUser(UserDetails user) {
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public void deleteUser(String username) {
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public boolean userExists(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    // Spring Security 내부에서
    // 인증을 처리할 때 사용하는 메서드임. 반드시 구현되어있어야함.
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isEmpty()) throw new UsernameNotFoundException(username);
        return User.withUsername(username)
                .password(optionalUser.get().getPassword())
                .build();
    }
}
