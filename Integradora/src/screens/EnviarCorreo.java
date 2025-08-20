package screens;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;

public class EnviarCorreo {

    public static boolean enviarCorreo(String destinatario, String asunto, String mensaje) {
        final String remitente = "TU_CORREO_DE_GMAIL"; // Reemplaza con tu dirección de correo de Gmail
        final String contraseña = "TU_CONTRASEÑA_DE_APLICACIÓN"; // Reemplaza con la contraseña de aplicación generada por Google

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(remitente, contraseña);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(remitente));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            message.setSubject(asunto);
            message.setText(mensaje);

            System.out.println("Preparando envío...");
            Transport.send(message);
            System.out.println("Correo enviado con éxito.");
            return true;

        } catch (MessagingException e) {
            System.out.println("Error al enviar el correo:");
            e.printStackTrace(); // Esto mostrara el error completo
            return false;
        }
    }
}
