package com.engidash.auth.services.email;

public interface EmailSender {
    void send(String to, String email);
}
