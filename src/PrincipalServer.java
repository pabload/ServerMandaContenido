
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PrincipalServer {

    public static void main(String[] args) throws IOException {
        
        iniciarServidor(args[0]);

    }
    public static void iniciarServidor(String puerto){
        try {
            crearServidor(puerto);
        } catch (Exception e) {
            System.out.println("error al iniciar servidor "+ e);
        }
    }
    public static void crearServidor(String puerto){
        
        try {
            ServerSocket sockeServer = new ServerSocket(Integer.parseInt(puerto));
            configurarServidor(sockeServer);
        } catch (IOException ex) {
            System.out.println("Error al crear socket servidor "+ ex);
        }
    }
    public static void configurarServidor(ServerSocket sockeServer){
        String entrada = null;
         do {
            Socket socket = null;
            try {
                socket = sockeServer.accept();
            } catch (IOException ex) {
                System.out.println("Error al crear socket"+ ex);
            }
            BufferedReader lector = null;
            try {
                lector = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            } catch (IOException ex) {
                System.out.println("Error al crear lector "+ ex);
            }
            PrintWriter escritor = null;
            try {
                escritor = new PrintWriter(socket.getOutputStream(), true);
            } catch (IOException ex) {
                System.out.println("Error al crear escritor "+ ex);
            }
            try {
                entrada = lector.readLine();
            } catch (IOException ex) {
                System.out.println("Error al leer mensaje "+ ex);
            }
            String cadena;
            String contCompleto = "";
            if (entrada.equalsIgnoreCase("fin")) {
                try {
                System.out.println("me voy");
                socket.close();
                sockeServer.close();
                System.exit(0);
                } catch (IOException ex) {
                   System.out.println("Error al cerrar sockets "+ ex);
                }
              
            } else {
                File directorio = new File(entrada);
                if (directorio.exists()) {
                     File[] contendio = directorio.listFiles();
                for (File object : contendio) {
                    if (object.isFile()) {
                        contCompleto = contCompleto + "Nombre archivo: " + object.getName()+System.lineSeparator();
                    } else {
                        if (object.isDirectory()) {
                            contCompleto = contCompleto + "Nombre del directorio: " + object.getName()+System.lineSeparator();
                        }
                    }
                }
                }else{
                    escritor.println("ruta no existente");
                }
               
            }
            escritor.println(contCompleto);
            /*salida=scanner.nextLine();
            escritor.println(salida);*/
        } while (!entrada.equalsIgnoreCase("fin"));
    }
 

}
