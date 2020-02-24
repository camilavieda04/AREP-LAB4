package edu.escuelaing.arep.Lab4.myServer;

//import edu.escuelaing.arep.Lab4.
import edu.escuelaing.arep.Lab4.annotations.Web;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.lang.reflect.InvocationTargetException;

/**
 * @author sarah.vieda
 */
public class miServidor {

    /**
     * Método donde se crea el servidor
     *
     * @param args Argumentos de entrada
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException, IllegalAccessException, IllegalArgumentException {
        //System.out.println("hola");
        ServerSocket serverSocket = null;
        while (true) {
            try {
                serverSocket = new ServerSocket(4567);
            } catch (IOException e) {
                System.err.println("No se pudo escuchar el puerto: " + getPort());
                System.exit(1);
            }
            Socket clienteSocket = null;
            try {
                clienteSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Acepto fallo");
                System.exit(1);
            }
            PrintWriter out = new PrintWriter(clienteSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            clienteSocket.getInputStream()
                    )
            );
            String inputLine;
            inputLine = in.readLine();
            //file = "/";
            //while ((inputLine = in.readLine()) != null) {
            // if (inputLine.contains("GET")) {
            try {
                String[] url1 = inputLine.split(" ");
                String[] url2 = url1[1].split("/");
                //file  = inputLine.substring(inputLine.indexOf("/")+1,inputLine.indexOf(" ", inputLine.indexOf(" ")+1));
                //break;}
                //System.out.println(url2[0]);
                if (url1[1].contains("/ws")) {
                    Class<?> clase = Class.forName("edu.escuelaing.arep.Lab4.ws.WebServiceHello");
                    for (Method m : clase.getMethods()) {
                        //System.out.println("Soy m: " + m);
                        if (m.isAnnotationPresent(Web.class)) {
                            String[] resp = url2[2].split("[, ?.@]+");
                            //System.out.println("resp" + resp);
                            if (m.getName().equals(resp[1])) {
                                try {
                                    m.invoke(clase, "/src/main/resources/" + url2[2], clienteSocket.getOutputStream());
                                } catch (InvocationTargetException ex) {
                                    Logger.getLogger(miServidor.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }

                            if (!in.ready()) {
                                break;
                            }
                        }

                    }
                }
            } catch (NullPointerException e) {
                //System.out.println("ERROR");
            } catch (ClassNotFoundException ex) {
                //System.out.println("ERROR");
            }
            out.close();
            in.close();
            clienteSocket.close();
            serverSocket.close();
        }
    }

    /**
     * Método que retorna el puerto por el cual correra mi aplicación
     *
     * @return puerto
     */
    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }

}
