package com.example.demo.modules.subscription;

import com.example.demo.kernel.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Table(name = "push_subscriptions")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PushSubscription extends BaseEntity {
    @Column(nullable = false, unique = true, length = 500)
    private String endpoint; // La URL única del servicio de mensajería (FCM/Mozilla)

    @Column(nullable = false)
    private String p256dh; // Llave pública del navegador para encriptar

    @Column(nullable = false)
    private String auth; // Llave de autenticación secreta del navegador

    // Opcional: Vincular la suscripción al médico que inició sesión
    // @ManyToOne
    // private Doctor doctor;
}