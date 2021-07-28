package com.example.candy.service.user;

import com.example.candy.domain.user.User;
import com.example.candy.repository.user.UserRepository;
import javassist.NotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Transactional
    public User join(String email, String password, String parentPassword, String name, String phone, String birth) {
        checkArgument(isNotEmpty(password), "password must be provided.");
        checkArgument(
                password.length() >= 4 && password.length() <= 15,
                "password length must be between 4 and 15 characters."
        );

        if (findByEmail(email).isPresent()) {
            throw new IllegalArgumentException("Email is already exists.");
        }

        User user = User.builder()
                .email(email)
                .password(password)
                .parentPassword(parentPassword)
                .name(name)
                .phone(phone)
                .birth(birth)
                .build();
        return save(user);
    }

    @Transactional
    public User login(String email, String password) throws NotFoundException {
        checkArgument(password != null, "password must be provided.");

        User user = findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User Not Found"));
        user.login(passwordEncoder, password);
        user.afterLoginSuccess();
        return user;
    }

    @Transactional(readOnly = true)
    public Optional<User> findByEmail(String email) {
        checkArgument(email != null, "email must be provided.");

        return userRepository.findByEmail(email);
    }

    private User save(User user) { return userRepository.save(user); }
}
