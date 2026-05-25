package com.example.demo.modules.subscription;

import nl.martijndwars.webpush.Notification;
import nl.martijndwars.webpush.PushService;
import nl.martijndwars.webpush.Subscription;
import org.bouncycastle.jce.provider.BouncyCastleProvider; // 👈 IMPORTANTE: Añade este import
import org.springframework.stereotype.Service;

import java.security.Security; // 👈 IMPORTANTE: Añade este import

@Service
public class PushNotificationService {

    private static final String PUBLIC_KEY = "BMxrfbCkcz232k6nvPMreX7DWIXFhmNwkiSR4oMq91_Lfk_EuipXYHUzvGi7UWj-22UWMQGG2nRKzzXDsD2LDa8";
    private static final String PRIVATE_KEY = "J02gIFPrOyMRp6ruk987IKbMjqFk0QWI2ZPG32FAzDw";

    private final PushService pushService;

    // Inicializamos el servicio con nuestras llaves firmadas
    public PushNotificationService() throws Exception {
        // 🔐 REGLA DE ORO: Agregamos dinámicamente el proveedor de seguridad al motor de Java
        if (Security.getProvider("BC") == null) {
            Security.addProvider(new BouncyCastleProvider());
        }

        this.pushService = new PushService(PUBLIC_KEY, PRIVATE_KEY, "mailto:soporte@ithera.com");
    }

    public void enviarAlerta(String endpoint, String p256dh, String auth, String mensajeJson) {
        try {
            Subscription subscription = new Subscription(
                    endpoint,
                    new Subscription.Keys(p256dh, auth)
            );

            Notification notification = new Notification(subscription, mensajeJson);

            pushService.send(notification);
            System.out.println("¡Notificación enviada con éxito desde Spring Boot!");

        } catch (Exception e) {
            System.err.println("Error al enviar la notificación: " + e.getMessage());
        }
    }
}