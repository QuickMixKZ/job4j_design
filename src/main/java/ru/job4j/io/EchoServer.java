package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        Pattern msgPattern = Pattern.compile("[\\?|\\&]msg\\=([^&|\\s]+)");
        String msg = null;
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    for (String str = in.readLine(); str != null && !str.isEmpty(); str = in.readLine()) {
                        Matcher matcher = msgPattern.matcher(str);
                        if (matcher.find()) {
                            msg = matcher.group(1);
                            break;
                        }
                        System.out.println(str);
                    }
                    String answer = "";
                    if ("Hello".equalsIgnoreCase(msg)) {
                        answer = "HTTP/1.1 200 Hello\r\n\r\n";
                    } else if ("Exit".equalsIgnoreCase(msg)) {
                        server.close();
                    } else {
                        answer = "HTTP/1.1 200 What\r\n\r\n";
                    }
                    out.write(answer.getBytes());
                    out.flush();
                }
            }
        }
    }
}