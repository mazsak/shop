package api.shop.shop.service;

public interface EmailService {
    void send(String to, String subject, String text);
}
