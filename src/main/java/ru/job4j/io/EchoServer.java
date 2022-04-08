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
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    for (String str = in.readLine(); str != null && !str.isEmpty(); str = in.readLine()) {
                        Matcher matcher = msgPattern.matcher(str);
                        if (matcher.find()) {
                            msg = matcher.group(1);
                        }
                        System.out.println(str);
                    }
                    out.flush();
                    if ("Bye".equalsIgnoreCase(msg)) {
                        server.close();
                        System.out.println("Server closed.");
                    }
                }
            }
        }
    }
}