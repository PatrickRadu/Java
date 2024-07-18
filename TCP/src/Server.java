import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import static java.lang.Thread.sleep;

public class Server {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Utilizare: java Server <port>");
            System.exit(-1);
        }
        int port=Integer.valueOf(args[0]);
        try (ServerSocket serverSocket=new ServerSocket(port)){
            System.out.println("Asteapta conexiuni client la portul "+serverSocket.getLocalPort()+"...");
            while(true)
            {
                Socket client =serverSocket.accept();
                InputStream inputClient=client.getInputStream();
                DataInputStream in=new DataInputStream(inputClient);
                OutputStream outputClient=client.getOutputStream();
                DataOutputStream out=new DataOutputStream(outputClient);
                while(true){
                    String cerereaClient=in.readUTF();
                    System.out.println(cerereaClient);
                    sleep(500);
                    String raspunsServer="ECHO server: "+cerereaClient;
                    out.writeUTF(raspunsServer);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
