package com.yorix.chat_server;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class NetClient extends JFrame implements KeyListener {

    final String serverIP = "127.0.0.1";
    final int serverPort = 12345;

    JTextArea textArea;
    JScrollPane scrollPane;
    InputStreamReader in;
    PrintWriter out;

    NetClient() {
        // ������� ����
        super("Simple Chat client");
        setSize(400, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // ��������� �� ���� ��������� ����
        textArea = new JTextArea();
        textArea.setBackground(Color.BLACK);
        textArea.setForeground(Color.WHITE);
        textArea.setEditable(false);
        textArea.setMargin(new Insets(10, 10, 10, 10));
        scrollPane = new JScrollPane(textArea);
        this.add(scrollPane);

        // �������������� � �������
        connect();

    }

    void connect() {
        try {
            Socket socket = new Socket(serverIP, serverPort);
            in = new InputStreamReader(socket.getInputStream());
            out = new PrintWriter(socket.getOutputStream());
            textArea.addKeyListener(this);
        } catch (IOException e) {
            textArea.setForeground(Color.RED);
            textArea.append("Server " + serverIP + " port " + serverPort + " " + "" + "NOT AVAILABLE");
            e.printStackTrace();
        }

        new Thread() {
            // � ��������� ������
            // ��������� ������� �� �������
            public void run() {
                while (true) {
                    try {
                        addCharToTextArea((char) (in.read()));
                    } catch (IOException e) {
                        textArea.setForeground(Color.RED);
                        textArea.append("\nCONNECTION ERROR");
                        e.printStackTrace();
                        return;
                    }
                }
            }
        }.start();
    }

    public static void main(String[] args) {
        new NetClient().setVisible(true);
    }

    @Override
    public void keyPressed(KeyEvent arg0) {
    }

    @Override
    public void keyReleased(KeyEvent arg0) {
    }

    @Override
    public void keyTyped(KeyEvent arg0) {
        // ���������� ������������ ������ � ���� � �� �����
        out.print(arg0.getKeyChar());
        out.flush();

        System.out.print((int) (arg0.getKeyChar()));
        addCharToTextArea(arg0.getKeyChar());
    }

    void addCharToTextArea(char c) {
        int lastCharIndex = textArea.getDocument().getLength() - 1;
        try {
            if (c == 8 && textArea.getDocument().getText(lastCharIndex, 1).charAt(0) != 10)
                textArea.getDocument().remove(lastCharIndex, 1);
            else
                textArea.append(c + "");
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }
}
