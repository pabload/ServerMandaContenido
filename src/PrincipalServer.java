
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class PrincipalServer {

    public static void main(String[] args) throws IOException {
        ServerSocket sockeServer = new ServerSocket(Integer.parseInt(args[0]));
        Socket socket = sockeServer.accept();
        BufferedReader lector = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String entrada;
        String salida;
        String contCompleto = "";
        PrintWriter escritor = new PrintWriter(socket.getOutputStream(), true);
        do {
            entrada = lector.readLine();
            String cadena;
            if (entrada.equalsIgnoreCase("fin")) {
                System.out.println("me voy");
                socket.close();
                sockeServer.close();
                System.exit(0);
            } else {
                File directorio = new File(entrada);
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
            }
            escritor.println(contCompleto);
            System.out.println(entrada);
            System.out.println(contCompleto);
            /*salida=scanner.nextLine();
            escritor.println(salida);*/
        } while (!entrada.equalsIgnoreCase("fin"));

    }

}
