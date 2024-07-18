import java.io.*;
import java.net.Socket;

public class ServerThread extends Thread {
    private Socket client = null;
    private static int nrClient = 0;

    public ServerThread(Socket client) {
        super("Client_"+(++nrClient));
        this.client = client;
    }

    @Override
    public void run() {
        System.out.println("Conexiune acceptată de la client "
                + client.getRemoteSocketAddress()+"; "+this.getName());
        try(InputStream inFromClient =client.getInputStream()) {
            DataInputStream in=new DataInputStream(inFromClient);
            OutputStream outToClient=client.getOutputStream();
            DataOutputStream out=new DataOutputStream(outToClient);
            while(true) {
                String cerereClient=in.readUTF();
                System.out.println(this.getName()+": "+cerereClient);
                sleep(500);
                String raspunsServer="ECHO server: "+cerereClient;
                out.writeUTF(raspunsServer);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
