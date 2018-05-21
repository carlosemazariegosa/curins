/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package curins;

/**
 *
 * @author cema
 */

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class CrearArchivo {
    
    public Boolean creaArchivo(Integer itipo, String imensaje) {
        String logfile;
        String ifh;
        String itipoMensaje;
        
        switch (itipo) {
            case 1: itipoMensaje = "[Info.]";
                    break;
            case 2: itipoMensaje = "[Warn.]";
                    break;
            case 3: itipoMensaje = "[Error]";
                    break;    
            default: itipoMensaje = "[Info.]";
                    break;
        }
        try {
            
            NombreArchivo ilog = new NombreArchivo();
            logfile = ilog.nombreFechaHora();
            ifh = ilog.regFechaHora();

            //Specify the file name and path here
            File file = new File("./" + logfile);

            /* This logic is to create the file if the
             * file is not already present
             */
            if(!file.exists()){
               file.createNewFile();
            }

            //Here true is to append the content to file
            FileWriter fw = new FileWriter(file,true);
            //BufferedWriter writer give better performance
            BufferedWriter bw = new BufferedWriter(fw);
            //bw.write("[Info]" + ifh + " - Prueba de funcionamiento\n");
            bw.write(itipoMensaje + ifh + " - " + imensaje + "\n");
            //Closing BufferedWriter Stream
            bw.close();

        }catch(IOException ioe){
             System.out.println("Exception occurred:");
             ioe.printStackTrace();
        }
        return true;
    }
}
