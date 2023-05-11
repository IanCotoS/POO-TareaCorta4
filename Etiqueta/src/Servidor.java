import java.net.*;
import java.io.*;

public class Servidor extends Thread{
    
    Socket client;
    ServerSocket server;
    ObjectInputStream input;
    boolean activo;

    public Servidor(){
        activo = false;
    }

    public boolean getActivo(){
        return activo;
    }

    public void abrirConexion(){
        try {
            server = new ServerSocket(5555);
            client = server.accept();
            input = new ObjectInputStream(client.getInputStream());
            activo = (boolean) input.readObject();
            input.close();
            client.close();
            server.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void run() {
        while(true){
            abrirConexion();
        }
    }
}
