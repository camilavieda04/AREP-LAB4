/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.escuelaing.arep.Lab4.ws;
import edu.escuelaing.arep.Lab4.annotations.Web;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import javax.imageio.ImageIO;


/**
 *
 * @author sarah.vieda
 */


/**
 * A traves de las etiquetas @Web se pueden ejecutar las siguientes funcionalidades 
 * @author sarah.vieda
 */
public class WebServiceHello {
    /*
    public static String hello(){
        return "<!DOCTYPE html>"
                +"<html>"
                +"<head>"
                +"<meta charset=UTF-8>"
                +"<title>blah</title>"
                +"</head>"
                +"<body>"
                +"<p>Hello</p>"
                +"</body>"
                +"</html>";
    }
    */
    
    /**
     * Metodo que retorna una imagen 
     * @param tipo Tipo de archivo de entrada
     * @param clienteOutput Quien solicita el recurso
     */
    @Web 
    public static void jpg(String tipo, OutputStream clienteOutput){
        try {
            BufferedImage image = ImageIO.read(new File(System.getProperty("user.dir") + tipo));
            ByteArrayOutputStream ArrBytes = new ByteArrayOutputStream();
            DataOutputStream writeImg = new DataOutputStream(clienteOutput);
            //String img = "HTTP /1.1 404 NOT FOUND \r\n"
               //     + "Content-Type: text/html; charset=\"UTF-8\" \r\n"
                 //   + "\r\n";
            ImageIO.write(image, "PNG", ArrBytes);
            writeImg.writeBytes("HTTP/1.1 200 OK \r\n");
            writeImg.writeBytes("Content-Type: image/png \r\n");
            writeImg.writeBytes("\r\n");
            writeImg.write(ArrBytes.toByteArray());
            //System.out.println(System.getProperty("user.dir") + tipo);
        } catch (IOException e) {
            System.out.println("r" + e.getMessage());
        }
    }
    /**
     * Metodo que retorna un archivo HTML 
     * @param ruta ruta donde se encuentra el archivo html
     * @param outputStream Quien solicita el recurso 
     * @throws IOException 
     */
    @Web
    private static void html(String ruta, OutputStream outputStream) {
        /*
         String temp = "";*/
        try {
            String text = "";
            String temp;
            BufferedReader t = new BufferedReader(new FileReader(System.getProperty("user.dir") + ruta));
            while ((temp = t.readLine()) != null) {
                //System.out.println(temp);
                text = text + temp;
            }
            outputStream.write(("HTTP/1.1 201 Found  \r\n"
                    + "Content-Type: text/html; charset=\"utf-8\" \r\n"
                    + "\r\n"
                    + text).getBytes());
        } catch (IOException e) {
            //System.out.println("entre pero no hice nada");
            //e.printStackTrace();
        }

    }
    
    /**
     * Metodo que retorna un archivo JSON
     * @param ruta ruta donde se encuentra el archivo html
     * @param outputStream Quien solicita el recurso
     * @throws IOException 
     */
    @Web
     private static void js(String ruta, OutputStream outputStream) {
        /*
         String temp = "";*/
        try {
            String text = "";
            String temp;
            BufferedReader t = new BufferedReader(new FileReader(System.getProperty("user.dir") + ruta));
            while ((temp = t.readLine()) != null) {
                //System.out.println(temp);
                text = text + temp;
            }
            outputStream.write(("HTTP/1.1 201 FOUND  \r\n"
                    + "Content-Type: application/json; charset=\"UTF-8\" \r\n"
                    + "\r\n"
                    + text).getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
     
     
     
    
    
    
    
}
