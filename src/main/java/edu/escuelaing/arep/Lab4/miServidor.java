package edu.escuelaing.arep.Lab4;

//import edu.escuelaing.arep.Lab4.
import edu.escuelaing.arep.Lab4.annotations.Web;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.lang.reflect.InvocationTargetException;

/**
 * @author sarah.vieda
 */
public class miServidor {

    static public void main(String[] args) {
        String className = "edu.escuelaing.arep.Lab4.ws.WebServiceHello";
        try {
            // Busca las clases y las trae a la maquina
            Class c = Class.forName(className);
            Method [] methods=c.getMethods();
            for (Method m:methods){
                if(m.isAnnotationPresent(Web.class)){
                     System.out.println(c.getName());
                     System.out.println(m.getName());
                try {
                    System.out.println(m.invoke(null));
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(miServidor.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalArgumentException ex) {
                    Logger.getLogger(miServidor.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InvocationTargetException ex) {
                    Logger.getLogger(miServidor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(miServidor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
