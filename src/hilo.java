import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;

public class hilo extends Thread{
    // private String usuario;
    private Socket socket;
    private BufferedReader entrada;
    private BufferedWriter salida;
    private String lectura;
    private static ArrayList<hilo> hilos = new ArrayList<hilo>();

    public hilo(Socket socket){
        this.socket = socket;
        // this.usuario = usuario;
        try {
            entrada = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            salida = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        hilos.add(this);
    }

    public void enviar(String mensaje){
        for(hilo h: hilos){
            if(!h.equals(this)){
                try {
                    h.salida.write(this.getName() + ": " + socket.toString() + " :" + mensaje);
                    h.salida.newLine();
                    h.salida.flush();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public void recibir(){
        while(true){
            try {
                lectura = entrada.readLine();
                System.out.println(this.getName()+": "+lectura);
                enviar(lectura);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

    }
    }

    public void run(){
        recibir();
    }

    // public String getUsuario(){
    //     return usuario;
    // }

}
