package com.lkznx7.nexusauth.application.usecase;

import com.lkznx7.nexusauth.application.port.EventPublisher;
import com.lkznx7.nexusauth.application.port.PasswordEncoderService;
import com.lkznx7.nexusauth.application.port.UserRepository;
import com.lkznx7.nexusauth.domain.entity.User;
import com.lkznx7.nexusauth.domain.enums.UserStatus;
import com.lkznx7.nexusauth.domain.valueobject.Email;
import com.lkznx7.nexusauth.domain.valueobject.Password;
import com.lkznx7.nexusauth.domain.valueobject.UserId;
import com.lkznx7.nexusauth.shared.exception.BusinessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegisterUserUseCaseImpl implements RegisterUserUseCase {

    private final UserRepository userRepository;
    private final PasswordEncoderService passwordEncoderService;
    private final EventPublisher eventPublisher;

    public RegisterUserUseCaseImpl(UserRepository userRepository,
                                   PasswordEncoderService passwordEncoderService,
                                   EventPublisher eventPublisher) {
        this.userRepository = userRepository;
        this.passwordEncoderService = passwordEncoderService;
        this.eventPublisher = eventPublisher;
    }

    @Override
    @Transactional
    public User execute(String username, Email email, Password password) {
        if (userRepository.existsByUsername(username)) {
            throw new BusinessException("Username already exists");
        }
        if (userRepository.existsByEmail(email)) {
            throw new BusinessException("Email already exists");
        }

        String encodedPassword = passwordEncoderService.encode(password.value());
        User user = new User(
                UserId.random(),
                username,
                email,
                new Password(encodedPassword),
                UserStatus.ACTIVE
        );

        User savedUser = userRepository.save(user);

        eventPublisher.publish(new com.lkznx7.nexusauth.domain.event.UserRegistered(savedUser.getId().value(), savedUser.getEmail().value()));

        return savedUser;
    }
}
