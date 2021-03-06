package com.zl.iolearn.insanecoder.client;

import com.zl.iolearn.insanecoder.utils.PrintRunnable;
import com.zl.iolearn.insanecoder.utils.SendRunnable;

import java.io.*;
import java.net.Socket;

public class NormalTcpClient {

    private static final int PORT = 5555;
    private static final String IP_ADDRESS = "localhost";

    public static void main(String[] args) throws IOException, InterruptedException {

        Socket socket = new Socket(IP_ADDRESS, PORT);
        System.out.println("Connecting to server ip: " + IP_ADDRESS + ", port: " + PORT);
        DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
        DataInputStream dis = new DataInputStream(socket.getInputStream());

        Thread sendThread = new Thread(new SendRunnable(dout));
        Thread printThread = new Thread(new PrintRunnable(dis, "server"));

        sendThread.start();
        printThread.start();

        sendThread.join();
        printThread.join();

        socket.close();
    }
}
