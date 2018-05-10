/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package curins;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Date;
/**
 *
 * @author cema
 */
public class NombreArchivo {
    
    String fnombre;
    
    public String nombreFechaHora() {
        
        String inom = null;
        try {
            // SimpleDateFormat MiFormato = new SimpleDateFormat("dd/MM/yyyy HM:mm:ss", Locale.getDefault()); 
            SimpleDateFormat MiFormato = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()); 
            Date hoy = new Date();
            inom = MiFormato.format(hoy);
        } catch(Exception e){
           
          System.out.println(e);
          System.out.println("Fallo la conexión ...");
          
       }
       return inom+"_Log.txt";
    }
}
