import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import static java.lang.System.exit;


public class GenericTCPClient {
    public static void main(String[] args) {
        // "localhost" este numele asociat adresei de IP 127.0.0.1

        if (args.length != 2) {
            System.err.println("Utilizare: java GenericTCPClient <port> <ip address>");
            exit(-1);
        }

        // TODO
        int port= Integer.parseInt(args[0]);
        String ip=args[1];
        ///pas 1 stabilire conexiune cu server-ul
        try(Socket client=new Socket(InetAddress.getByName(ip),port);
            OutputStream outServer=client.getOutputStream();
            DataOutputStream out=new DataOutputStream(outServer);
            InputStream inServer=client.getInputStream();
            DataInputStream in=new DataInputStream(inServer);
        ) {

            BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Introduceti un text sau EXIT pentru iesire: ");
            while (true)
            {
                //pas 2 pregatire cerere server
                String mesaj=reader.readLine();
                if(mesaj.equalsIgnoreCase("exit"))
                {
                    //la try cu resurse nu este necesar!!
                    in.close();
                    out.close();
                    client.close();
                    exit(0);
                }
                //pas 3 trimitere cerere catre server
                out.writeUTF(mesaj);
                //pas 4 asteptare raspuns server
                //pas 5 preluare raspuns
                String raspunsServer=in.readUTF();
                //pas 6 procesare raspuns primit de la server
                System.out.println(raspunsServer);
            }
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
