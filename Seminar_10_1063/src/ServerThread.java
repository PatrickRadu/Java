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
    public void run()
    {


        System.out.println("Conexiune acceptată de la client "
                + client.getRemoteSocketAddress()+"; "+this.getName());

        try (InputStream inFromClient = client.getInputStream();
            var in = new DataInputStream(inFromClient);
            OutputStream outToClient = client.getOutputStream();
            var out = new DataOutputStream(outToClient);) {
                //pas 1 acceptare conexiune client
            while(true) {
                    //pas 2 preluare cerere client
                String cerereClient = in.readUTF();
                //pas 3 prelucrare cerere client
                System.out.println(this.getName()+": "+cerereClient);
                sleep(500);
                //pas 4 pregatire raspuns
                String raspunsServer = "ECHO server: " + cerereClient;
                //pas 5 trimitre raspuns catre client
                out.writeUTF(raspunsServer);
                }

        }
        catch(IOException | InterruptedException eof)
        {
            System.out.println("S-a închis conexiunea cu "+this.getName());
        }

    }
}

