/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.escuelaing.arep.Lab4.ws;
import edu.escuelaing.arep.Lab4.annotations.Web;


/**
 *
 * @author sarah.vieda
 */
//
public class WebServiceHello {
    @Web
    
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
    
}
