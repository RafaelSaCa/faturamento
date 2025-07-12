package com.rafaelsaca.faturamento.service;

import java.io.File;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.rafaelsaca.faturamento.model.Cliente;

import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    
    public void enviarCobrancaPorEmail (Cliente cliente, String pdfPath){

        try {
            MimeMessage mensagem = mailSender.createMimeMessage();
            MimeMessageHelper helper =  new MimeMessageHelper(mensagem, true);

            helper.setTo(cliente.getEmail());
            helper.setSubject("AVISO DE COBRANÇA");
            helper.setText("Olá "+ cliente.getNome() + ",\n\nSegue em anexo a fatura referente a sua compra.");

            FileSystemResource file = new FileSystemResource(new File(pdfPath));
            helper.addAttachment("fatura.pdf", file);

            mailSender.send(mensagem);
                
        } catch (Exception e) {
            throw new RuntimeException("Erro ao enviar o e-mail: " + e.getMessage());
        }
    }


}
