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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class ConectarFB {
    private Connection connection = null;
    private ResultSet resultSet = null;
    private Statement statement = null;
    private String db = "db_jee2";
    private String user = "SYSDBA";
    private String password = "20591";
    
    public boolean conectar() {
        Boolean iarch;
        try{
            
          Class.forName("org.firebirdsql.jdbc.FBDriver");
          connection = DriverManager.getConnection("jdbc:firebirdsql:10.1.0.15:" + db,this.user, this.password);
          CrearArchivo ilog = new CrearArchivo();
          iarch = ilog.creaArchivo(1,"Conexion exitosa a " + this.db);
          
       }catch(Exception e){
          System.out.println(e);
          CrearArchivo ilog = new CrearArchivo();
          iarch = ilog.creaArchivo(3,"Fallo conexión a " + this.db);
       }
       return true;
    }
    
    public String select()  {
        
        String res=" ID | CodMov | Nombre  \n ";
        Boolean iarch;
        try {
           statement = connection.createStatement();
           resultSet = statement.executeQuery("SELECT first 3 * FROM acad_tipo_mov order by codigo_mov");

           while (resultSet.next()) {
             res+=resultSet.getString("ID") + " | " +
                 resultSet.getString("Codigo_mov") + " | " + 
                 resultSet.getString("Nombre") + " \n ";
            }
            CrearArchivo ilog = new CrearArchivo();
            iarch = ilog.creaArchivo(1,"Consulta cursos de sistema Acad");
         }
        catch (SQLException ex) {
             System.out.println(ex);
             CrearArchivo ilog = new CrearArchivo();
             iarch = ilog.creaArchivo(3,"Fallo consulta a tabla de cursos");
         }
        return res;
    }
    
    
    public void desconectar() {
        Boolean iarch;
        try {
            resultSet.close();
            statement.close();
            connection.close();
            CrearArchivo ilog = new CrearArchivo();
            iarch = ilog.creaArchivo(1,"Desconexion exitosa a " + this.db);
        }        
        catch (SQLException ex) {
            System.out.println(ex);
            CrearArchivo ilog = new CrearArchivo();
            iarch = ilog.creaArchivo(3,"Fallo Desconexion a " + this.db);
        }
    }  
}    
