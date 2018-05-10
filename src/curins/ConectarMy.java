/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package curins;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class ConectarMy {
    private Connection connection = null;
    private ResultSet resultSet = null;
    private Statement statement = null;
    private String db = "inscripciones_alumnos";
    private String user = "root";
    private String password = "";    
    
    public boolean conectar() {
        try{
            
          // 
          // 
          Class.forName("com.mysql.jdbc.Driver");
          connection = DriverManager.getConnection("jdbc:mysql://localhost/" + db, this.user, this.password);
          System.out.println("Conectado a la base de datos [ " + this.db + "]\n");
          
          
       }catch(Exception e){
           
          System.out.println(e);
          System.out.println("Fallo la conexión ...");
          
       }
       return true;
    }
    
    public String select()  {
        
        String res=" SemesAño | Fac. | Año | Carnet  \n ";
     
        try {
           statement = connection.createStatement();
           resultSet = statement.executeQuery("SELECT * FROM Inscripcion order by Referencia desc limit 3");

           while (resultSet.next()) {
             res+=resultSet.getString("Semes_anio") + "    | " +
                 resultSet.getString("Facultad") + " | " + 
                 resultSet.getString("Anio") + "  | " +
                 resultSet.getString("Carnet") + " \n ";
            }
         }
        catch (SQLException ex) {
             System.out.println(ex);
             System.out.println("Error 0003: Select ...");
         }
        return res;
    }
    
    
    public void desconectar() {
        try {
            resultSet.close();
            statement.close();
            connection.close();
            System.out.println("Desconectado de la base de datos [ " + this.db + "] \n");            
        }        
        catch (SQLException ex) {
            System.out.println(ex);
        }
    }  
}    

