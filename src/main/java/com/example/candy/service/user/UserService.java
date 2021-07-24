package com.example.candy.service.user;

import com.example.candy.domain.user.Authority;
import com.example.candy.domain.user.User;
import com.example.candy.repository.user.UserRepository;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.google.common.base.Preconditions.checkArgument;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Transactional
    public User login(String email, String password) throws NotFoundException {
        checkArgument(password != null, "password must be provided.");

        User user = findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User Not Found"));

//        if (passwordEncoder.matches(password, user.getPassword())) {
//            user.setAuthority(Authority.STUDENT);
//        } else if (passwordEncoder.matches(password, user.getParentPassword())) {
//            user.setAuthority(Authority.PARENT);
//        } else {
//            throw new IllegalArgumentException("Bad credential");

        user.afterLoginSuccess();




        return user;
    }

    @Transactional(readOnly = true)
    public Optional<User> findByEmail(String email) {
        checkArgument(email != null, "email must be provided.");

        return userRepository.findByEmail(email);
    }
}
