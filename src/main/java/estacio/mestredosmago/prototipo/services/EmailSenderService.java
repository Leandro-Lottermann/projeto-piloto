package estacio.mestredosmago.prototipo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {
    @Autowired
    private final JavaMailSender envioEmailJava;

    public EmailSenderService(final JavaMailSender javaMailSender) {
        this.envioEmailJava = javaMailSender;
    }

    public void enviar(String para, String titulo, String conteudo) {
        var mensagem = new SimpleMailMessage();
        mensagem.setTo(para);
        mensagem.setSubject(titulo);
        mensagem.setText(conteudo);
        envioEmailJava.send(mensagem);
    }



}
