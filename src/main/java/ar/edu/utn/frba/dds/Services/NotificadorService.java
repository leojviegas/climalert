package ar.edu.utn.frba.dds.Services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificadorService {

  @Value("${mailDestinatario1}")
  private String destinatario1;
  @Value("${mailDestinatario2}")
  private String destinatario2;
  @Value("${mailDestinatario3}")
  private String destinatario3;

  private final JavaMailSender mailSender;

  @Value("${mailRemitente}")
  private String remitente;

  public NotificadorService(JavaMailSender mailSender) {
    this.mailSender = mailSender;
  }

  public void notificarAlerta(String mensaje) {
    // Armamos la lista acá para asegurarnos de que Spring ya haya inyectado los valores.
    List<String> destinatarios = List.of(destinatario1, destinatario2, destinatario3);

    if (destinatarios.isEmpty()) {
      System.out.println("No hay destinatarios configurados para enviar la alerta.");
      return;
    }

    for (String destinatario : destinatarios) {
      SimpleMailMessage mailMessage = new SimpleMailMessage();
      mailMessage.setFrom(remitente);
      mailMessage.setTo(destinatario);
      mailMessage.setSubject("ALERTA METEOROLÓGICA - Climalert");
      mailMessage.setText(mensaje);

      mailSender.send(mailMessage);
    }
    System.out.println("Alerta enviada correctamente a los destinatarios mediante Twilio SendGrid.");
  }
}
