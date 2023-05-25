package com.yorix.chat_server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

public class Client implements Runnable {
    private final ChatServer chatServer;
    private String username;
    private final Socket socket;
    private final List<Client> clients;
    private PrintStream out;

    public Client(ChatServer chatServer, Socket socket, List<Client> clients) {
        this.chatServer = chatServer;
        this.socket = socket;
        this.clients = clients;
        new Thread(this).start();
    }

    void receive(String message) {
        out.println(message);
    }

    @Override
    public void run() {
        try {
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();

            Scanner in = new Scanner(is);
            out = new PrintStream(os);

            out.println("Enter your username:");
            String input = in.nextLine();
            username = input;

            while (!input.equals("exit")) {
                chatServer.sendAll(this.username + ": " + input);
                input = in.nextLine();
            }

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
