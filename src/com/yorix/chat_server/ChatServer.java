package com.yorix.chat_server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ChatServer {
    ArrayList<Client> clients;
    ServerSocket serverSocket;

    public ChatServer() throws IOException {
        this.clients = new ArrayList<>();
        this.serverSocket = new ServerSocket(12345);
    }

    void sendAll(String message) {
        for (Client client : clients) {
            client.receive(message);
        }
    }

    public void run() {
        while (true) {
            System.out.println("Waiting...");

            try {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected!");

                clients.add(new Client(this, socket, clients));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new ChatServer().run();
    }
}
