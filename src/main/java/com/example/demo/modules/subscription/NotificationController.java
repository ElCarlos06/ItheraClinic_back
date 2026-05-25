package com.example.demo.modules.subscription;

import com.example.demo.modules.subscription.dtos.SubscriptionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notifications")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class NotificationController {

    private final PushSubscriptionRepository repository;
    private final PushNotificationService pushNotificationService; // 👈 1. Inyectamos el servicio

    @PostMapping("/subscribe")
    public ResponseEntity<String> guardarSuscripcion(@RequestBody SubscriptionDTO dto) {
        if (!repository.existsByEndpoint(dto.getEndpoint())) {
            PushSubscription nuevaSuscripcion = new PushSubscription();
            nuevaSuscripcion.setEndpoint(dto.getEndpoint());
            nuevaSuscripcion.setP256dh(dto.getKeys().getP256dh());
            nuevaSuscripcion.setAuth(dto.getKeys().getAuth());

            repository.save(nuevaSuscripcion);
            return ResponseEntity.ok("¡Suscripción guardada con éxito en Spring Boot!");
        }
        return ResponseEntity.ok("El dispositivo ya estaba registrado.");
    }

    // 👈 2. Agregamos el método GET de prueba
    @GetMapping("/test-send")
    public ResponseEntity<String> enviarPrueba() {
        var suscripciones = repository.findAll();

        if (suscripciones.isEmpty()) {
            return ResponseEntity.ok("No hay suscripciones en la base de datos aún.");
        }

        String mensajeJson = """
        {
            "title": "¡Alerta desde Spring Boot! 🍃",
            "body": "El sistema de notificaciones está respondiendo en vivo."
        }
        """;

        for (PushSubscription sub : suscripciones) {
            pushNotificationService.enviarAlerta(
                    sub.getEndpoint(),
                    sub.getP256dh(),
                    sub.getAuth(),
                    mensajeJson
            );
        }

        return ResponseEntity.ok("Intento de envío procesado para " + suscripciones.size() + " dispositivos.");
    }
}