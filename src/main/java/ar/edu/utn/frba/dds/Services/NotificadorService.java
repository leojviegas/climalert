package ar.edu.utn.frba.dds.Services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificadorService {

    private final JavaMailSender mailSender;

    @Value("${mails.destinatarios}")
    private List<String> destinatarios;
    
    @Value("${mails.remitente}")
    private String remitente;

    public NotificadorService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void notificarAlerta(String mensaje) {
        if (destinatarios == null || destinatarios.isEmpty()) {
            System.out.println("No hay destinatarios configurados para enviar la alerta.");
            return;
        }

        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(remitente);
            mailMessage.setTo(destinatarios.toArray(new String[0]));
            mailMessage.setSubject("ALERTA METEOROLÓGICA - Climalert");
            mailMessage.setText(mensaje);

            mailSender.send(mailMessage);
            System.out.println("Alerta enviada correctamente a los destinatarios mediante Twilio SendGrid.");
        } catch (Exception e) {
            System.err.println("Error al intentar enviar los correos. Verificá la configuración de Twilio SendGrid: " + e.getMessage());
        }
    }
}
