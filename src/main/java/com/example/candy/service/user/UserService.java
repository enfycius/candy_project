package com.example.candy.service.user;

import com.example.candy.domain.user.User;
import com.example.candy.repository.user.UserRepository;
import com.example.candy.service.candyHistory.CandyHistoryService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

@Service
public class UserService {

    @Autowired private UserRepository userRepository;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private CandyHistoryService candyHistoryService;

    @Transactional
    public User join(String email, boolean emailCheck, String password, String parentPassword, String name, String phone, String birth) {
        checkArgument(isNotEmpty(password), "password must be provided.");
        checkArgument(
                password.length() >= 4 && password.length() <= 15,
                "password length must be between 4 and 15 characters."
        );
        checkArgument(emailCheck, "have to verify email");

        if (findByEmail(email).isPresent()) {
            throw new IllegalArgumentException("Email is already exists.");
        }

        User user = User.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .parentPassword(passwordEncoder.encode(parentPassword))
                .name(name)
                .phone(phone)
                .birth(birth)
                .enabled(true)
                .createDate(LocalDateTime.now())
                .build();

        User savedUser = save(user);
        candyHistoryService.initCandy(savedUser);
        return savedUser;
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

    public Optional<User> findById(Long userId) {
        return userRepository.findById(userId);
    }

    private User save(User user) { return userRepository.save(user); }
}
