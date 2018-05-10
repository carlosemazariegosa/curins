package curins;

/**
 *
 * @author cema
 */
public class CurIns {

    public static void main(String[] args) {
        Boolean icon;
        ConectarFB fbc = new ConectarFB();
        icon = fbc.conectar();
        System.out.println(fbc.select());
        fbc.desconectar();
        
        ConectarMy myc = new ConectarMy();
        icon = myc.conectar();
        System.out.println(myc.select());
        myc.desconectar();        
    }
    
}
