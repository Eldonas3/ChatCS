import java.net.Socket;
import java.net.UnknownHostException;
import java.io.IOException;
import java.net.InetAddress;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class cliente {
    public static void main(String[] p) throws Exception{
        
    byte[] byteIp = new byte[] {(byte)127,(byte)0,(byte)0,(byte)1};
    InetAddress ip = null;
    Socket sock;
    BufferedReader sockInput;
    PrintWriter sockOutput;

    try {
        ip = InetAddress.getByAddress(byteIp);
    } catch (UnknownHostException e) {
        throw new RuntimeException(e);
    }

    try {
        sock = new Socket(ip,3333);
        sockInput = new BufferedReader(new InputStreamReader(sock.getInputStream()));
        sockOutput = new PrintWriter(sock.getOutputStream(),true);
    } catch (IOException e) {
        throw new RuntimeException(e);
    }

    Scanner scanner = new Scanner(System.in);     
    leerMensaje(sockInput);
    escribirMensajes(scanner,sockOutput);

    }

    public static void leerMensaje(BufferedReader entrada){
        new Thread(new Runnable() {

            @Override
            public void run() {
                while(true){
                    try {
                        System.out.println(entrada.readLine());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            
        }).start();
    }

    public static void escribirMensajes(Scanner consola,PrintWriter salida ){
        String lectura;
        while(true){
            lectura = consola.nextLine();
            salida.println(lectura);
        }
    }
}
