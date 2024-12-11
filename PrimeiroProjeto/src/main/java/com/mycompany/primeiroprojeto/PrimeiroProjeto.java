/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.primeiroprojeto;

import java.io.*;
import java.net.*;
import java.util.Base64;

/**
 *
 * @author Marcos Paulo
 */
public class PrimeiroProjeto {

    public static void main(String[] args) {
        String smtpServer = "smtp.mailtrap.io";
        int smtpPort = 2525;

        try (Socket socket = new Socket(smtpServer, smtpPort); 
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream())); 
                PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)) {

            System.out.println("Resposta do servidor: " + reader.readLine());
            sendCommand(writer, reader, "HELO smtp.mailtrap.io");
            sendCommand(writer, reader, "AUTH LOGIN");
            sendCommand(writer, reader, Base64.getEncoder().encodeToString("7765b4251f7892".getBytes()));
            sendCommand(writer, reader, Base64.getEncoder().encodeToString("a30a7a051b13de".getBytes()));
            sendCommand(writer, reader, "MAIL FROM:<seu-email.@mail.com>\r");
            sendCommand(writer, reader, "RCPT TO:<destinatario@mail.com>\r");
            sendCommand(writer, reader, "DATA\r");
            writer.println("Subject: Teste de email\r");
            writer.println("From: seu-email.@mail.com\r");
            writer.println("To: destinatario@mail.com\r");
            writer.println();
            writer.println("Este é o corpo do email.\r");
            writer.println(".\r");
            System.out.println("Resposta: " + reader.readLine());
            sendCommand(writer, reader, "QUIT\r");
            socket.close();
            
            
            
            
            
            
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao conectar ao servidor SMTP: " + e.getMessage());
        }
    }
    
    private static void sendCommand(PrintWriter writer, BufferedReader reader, String command) throws IOException {
        writer.println(command);
        System.out.println("Enviado: " + command);
        System.out.println("Resposta do servidor: " + reader.readLine());
    }
}

