import java.io.IOException;
import java.net.ServerSocket;

public class MulthiThreaClient {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Utilizare: java GenericTCPMultiClientServer <port>");
            System.exit(-1);
        }

        int port = Integer.valueOf(args[0]);
        try(ServerSocket serverSocket=new ServerSocket(port)) {
            System.out.println("Aşteaptă conexiuni client la portul " +
                    serverSocket.getLocalPort() + "...");
            while (true)
            {
                new ServerThread(serverSocket.accept()).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
