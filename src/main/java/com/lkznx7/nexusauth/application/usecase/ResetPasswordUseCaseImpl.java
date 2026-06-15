package com.lkznx7.nexusauth.application.usecase;

import com.lkznx7.nexusauth.application.port.CacheService;
import com.lkznx7.nexusauth.application.port.MailService;
import com.lkznx7.nexusauth.application.port.UserRepository;
import com.lkznx7.nexusauth.domain.entity.User;
import com.lkznx7.nexusauth.domain.valueobject.Email;
import com.lkznx7.nexusauth.shared.exception.BusinessException;
import org.springframework.stereotype.Service;

@Service
public class ResetPasswordUseCaseImpl implements ResetPasswordUseCase {

    private final UserRepository userRepository;
    private final MailService mailService;
    private final CacheService cacheService;

    public ResetPasswordUseCaseImpl(UserRepository userRepository, MailService mailService, CacheService cacheService) {
        this.userRepository = userRepository;
        this.mailService = mailService;
        this.cacheService = cacheService;
    }

    @Override
    public void execute(String email) {
        User user = userRepository.findByEmail(new Email(email))
                .orElseThrow(() -> new BusinessException("User not found"));

        String code = mailService.generateVerificationCode();
        
        cacheService.put("reset_code:" + user.getEmail().value(), code, 900); // 15 minutes expiration
        
        mailService.sendMail(user.getEmail().value(), "Reset Password", "Your verification code is: " + code);
    }
}
