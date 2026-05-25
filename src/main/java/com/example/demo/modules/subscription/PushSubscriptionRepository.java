package com.example.demo.modules.subscription;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PushSubscriptionRepository extends JpaRepository<PushSubscription, Long> {
    boolean existsByEndpoint(String endpoint);
}
