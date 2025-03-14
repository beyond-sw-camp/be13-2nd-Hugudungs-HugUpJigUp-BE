package com.hugudungs.hugupjigup.common.email;

import java.util.Map;

public interface EmailService {
    public void sendMimeMail(String to, String subject, Map<String, Object> parameters, String template);
}
