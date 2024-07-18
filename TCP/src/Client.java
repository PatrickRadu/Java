import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public static void main(String[] args) {
        if(args.length != 2) {
            System.err.println("Utilizare: java Client <adresa_server> <port>");
            System.exit(-1);
        }
        int port=Integer.parseInt(args[0]);
        String ip=args[1];
        try(Socket client=new Socket(ip,port)){
            OutputStream outServer=client.getOutputStream();
            DataOutputStream out=new DataOutputStream(outServer);
            InputStream inServer=client.getInputStream();
            DataInputStream in=new DataInputStream(inServer);
            BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Introduceti un text sau EXIT pentru iesire: ");
            while(true){
                String mesaj=reader.readLine();
                if(mesaj.equals("EXIT")){
                    in.close();
                    out.close();
                    client.close();
                    System.exit(0);
                }
                out.writeUTF(mesaj);
                String raspunsServer=in.readUTF();
                System.out.println(raspunsServer);
            }
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
