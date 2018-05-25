package curins;

/**
 *
 * @author cema
 * 
 * Comandos:
 * 
 * Los comandos se ingresaran en forma de archivos simples (sin extensión)
 * y se deberan de crear en la carpeta por default donde corre el programa.
 * Los camandos son:
 * 
 * Comando- Descripción------------------------------------------------
 * SALIR    Causa la terminarción de la ejecución del programa, al 
 *          momento de crear el archivo, tardará de 1 a 60 segundos.
 * RUNNOW   Causa que se realice la acutalización de cursos en el 
 *          momento de la creación del archivo, tardará de 1 a 60 
 *          segundos.
 * CICLOS   En este comando le indicaremos al sistema que debe ciclos
 *          debe de procesar para la actualización de cursos. Ejemplo:
 *          - ('12018') 
 *          - ('12018','22018')
 *          - ('12018','22018','32018')
 *          - ('12018','22018','32018','42018')
 *          El archivo solo puede tener una linea de comandos.  Si tiene
 *          mas de una, tomara la ultima linea de comando.
 */

import java.util.Timer;
import java.util.TimerTask;
import java.io.File;

public class CurIns {
    
    public static Boolean proceso(String icmd) {
        Boolean iconfb, iconmy, iarch;
        
        ConectarFB fbc = new ConectarFB();
        
        // FB
        iconfb = fbc.conectar();
        iconmy = fbc.conectarmy();

        System.out.println(fbc.migracion(icmd));
        
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
                String icmd="";
                if (ictrlSalir >= 6 || primerCiclo) {
                    
                    //CrearArchivo ilog = new CrearArchivo();
                    icmd = ilog.leerArchivo();
                    iarch = ilog.creaArchivo(1,"INICIO - Con ciclos:" + icmd );
                    
                    // Lee el comando (ciclos 12018, 22018 ... etc)
                    icmd = ilog.leerArchivo();
                    iok = proceso(icmd);
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
