package com.hugudungs.hugupjigup.common.email;

import java.util.Map;

public interface EmailService {
    void sendMimeMail(String to, String subject, Map<String, Object> parameters, String template);
}
