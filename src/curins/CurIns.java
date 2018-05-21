package curins;

/**
 *
 * @author cema
 */

import java.util.Timer;
import java.util.TimerTask;
import java.io.File;

public class CurIns {
    
    public static Boolean proceso() {
        Boolean iconfb, iconmy, iarch;
        
        ConectarFB fbc = new ConectarFB();
        
        // FB
        iconfb = fbc.conectar();
        iconmy = fbc.conectarmy();

        System.out.println(fbc.migracion());
        
        fbc.desconectar();
        fbc.desconectarmy();  
        
        return true;
    }
    
    public static boolean salir() {
        File file = new File("./" + "SALIR");
        return file.exists();           
    }
    
    public static void main(String[] args) {

        Timer timer;
        timer = new Timer();
        CrearArchivo ilog = new CrearArchivo();

        TimerTask task = new TimerTask() {

            Integer icontador = 1;
            Integer ictrlSalir = 1;
            Boolean primerCiclo = true;
            @Override
            public void run() {
                
                Boolean iok, iarch, isalir;
                if (ictrlSalir >= 6 || primerCiclo) {
                    CrearArchivo ilog = new CrearArchivo();
                    iarch = ilog.creaArchivo(1,"INICIO");
                    iok = proceso();
                    iarch = ilog.creaArchivo(1,"Proceso realizado EXITOSAMENTE [" + icontador + "]");
                    iarch = ilog.creaArchivo(1,"FIN");
                    icontador++;
                    ictrlSalir = 0;  // debido a que al salir del IF lo incrementa en 1
                    primerCiclo = false;
                    //System.out.println(fbc.select());
                    //System.out.println(fbc.selectmy());
                } else {
                    if (isalir=salir()) {
                        iarch = ilog.creaArchivo(1,"TERMINADO - Por el administrador");
                        System.exit(0);
                    }
                    iarch = ilog.creaArchivo(1,"");
                    System.out.println("Control Salir: " + ictrlSalir);
                }
                ictrlSalir++;
                
            }
        };
        timer.schedule(task, 10, 60000);
    }           
    
}
