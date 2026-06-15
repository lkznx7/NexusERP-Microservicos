package com.lkznx7.nexusauth.application.usecase;

import com.lkznx7.nexusauth.application.port.PasswordEncoderService;
import com.lkznx7.nexusauth.application.port.SessionRepository;
import com.lkznx7.nexusauth.application.port.TokenService;
import com.lkznx7.nexusauth.application.port.UserRepository;
import com.lkznx7.nexusauth.domain.entity.User;
import com.lkznx7.nexusauth.domain.valueobject.Email;
import com.lkznx7.nexusauth.shared.exception.BusinessException;
import org.springframework.stereotype.Service;

@Service
public class LoginUseCaseImpl implements LoginUseCase {

    private final UserRepository userRepository;
    private final PasswordEncoderService passwordEncoderService;
    private final TokenService tokenService;
    private final SessionRepository sessionRepository;

    public LoginUseCaseImpl(UserRepository userRepository,
                            PasswordEncoderService passwordEncoderService,
                            TokenService tokenService,
                            SessionRepository sessionRepository) {
        this.userRepository = userRepository;
        this.passwordEncoderService = passwordEncoderService;
        this.tokenService = tokenService;
        this.sessionRepository = sessionRepository;
    }

    @Override
    public Result execute(String email, String password) {
        User user = userRepository.findByEmail(new Email(email))
                .orElseThrow(() -> new BusinessException("Invalid credentials"));

        if (!passwordEncoderService.matches(password, user.getPassword().value())) {
            throw new BusinessException("Invalid credentials");
        }

        if (!user.isActive()) {
            throw new BusinessException("User is not active");
        }

        String accessToken = tokenService.generateAccessToken(user);
        String refreshToken = tokenService.generateRefreshToken(user);

        com.lkznx7.nexusauth.domain.entity.Session session = new com.lkznx7.nexusauth.domain.entity.Session();
        session.setId(java.util.UUID.randomUUID().toString());
        session.setUserId(user.getId());
        session.setStatus(com.lkznx7.nexusauth.domain.enums.SessionStatus.ACTIVE);
        session.setCreatedAt(java.time.LocalDateTime.now());
        session.setExpiresAt(java.time.LocalDateTime.now().plusHours(24));
        session.setIpAddress("");
        session.setUserAgent("");
        sessionRepository.save(session);

        return new Result(accessToken, refreshToken);
    }
}
