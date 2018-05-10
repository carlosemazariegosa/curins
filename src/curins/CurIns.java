package curins;

import java.sql.Date;

/**
 *
 * @author cema
 */
public class CurIns {

    public static void main(String[] args) {
        Boolean icon;
        String logfile;
        
        ConectarFB fbc = new ConectarFB();
        icon = fbc.conectar();
        System.out.println(fbc.select());
        fbc.desconectar();
        
        ConectarMy myc = new ConectarMy();
        icon = myc.conectar();
        System.out.println(myc.select());
        myc.desconectar();  
        
        NombreArchivo ilog = new NombreArchivo();
        
        logfile = ilog.nombreFechaHora();
        System.out.println("Archivo main:  " + logfile);

    }

    
    
}
