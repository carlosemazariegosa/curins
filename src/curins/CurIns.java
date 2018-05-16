package curins;

/**
 *
 * @author cema
 */
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
    
    public static void main(String[] args) {
       
        Boolean iok, iarch;
        CrearArchivo ilog = new CrearArchivo();
        iarch = ilog.creaArchivo(1,"INICIO");
        iok = proceso();
        iarch = ilog.creaArchivo(1,"Proceso realizado EXITOSAMENTE");
        iarch = ilog.creaArchivo(1,"FIN");
        //System.out.println(fbc.select());
        //System.out.println(fbc.selectmy());

    }
    
}
