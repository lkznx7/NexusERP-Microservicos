package com.lkznx7.nexusauth.application.port;

public interface MailService {
    // gerar codigo
    String getCodeVerification();
    boolean codeIsValid(String code);
}
