import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class servidor {

    private static final int puerto = 3333;
    public static void main(String[] p) throws Exception {

        try {
            ServerSocket server = new ServerSocket(puerto);
            while(true){
                Socket cliente = server.accept();
                new hilo(cliente).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
