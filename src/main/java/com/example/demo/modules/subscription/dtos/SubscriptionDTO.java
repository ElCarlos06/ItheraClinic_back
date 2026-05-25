package com.example.demo.modules.subscription.dtos;

import lombok.Data;

@Data
public class SubscriptionDTO {
    private String endpoint;
    private Keys keys;

    @Data
    public static class Keys {
        private String p256dh;
        private String auth;
    }
}