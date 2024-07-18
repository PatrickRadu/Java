import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import static java.lang.Thread.sleep;

public class GenericTCPServer {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Utilizare: java GenericTCPMultiClientServer <port>");
            System.exit(-1);
        }

        int port = Integer.valueOf(args[0]);

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Aşteaptă conexiuni client la portul " +
                    serverSocket.getLocalPort() + "...");
            while (true)
            {
                //pas 1 acceptare conexiune client
                Socket client=serverSocket.accept();
                InputStream inputClient=client.getInputStream();
                DataInputStream in=new DataInputStream(inputClient);
                OutputStream outputClient=client.getOutputStream();
                DataOutputStream out=new DataOutputStream(outputClient);
                while(true) {
                    //pas 2 preluare cerere client
                    String cerereClient = in.readUTF();
                    //pas 3 prelucrare cerere client
                    System.out.println(cerereClient);
                    sleep(500);
                    //pas 4 pregatire raspuns
                    String raspunsServer = "ECHO server: " + cerereClient;
                    //pas 5 trimitre raspuns catre client
                    out.writeUTF(raspunsServer);
                }
            }

        } catch (IOException | InterruptedException e) {
            System.err.println("Conexiune eşuată pe portul " + port);
            System.exit(-2);
        }
    }
}
