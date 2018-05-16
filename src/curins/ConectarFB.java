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
    private Connection connectionmy = null;
    private ResultSet resultSetmy = null;
    private Statement statementmy = null;
    private String dbmy = "inscripciones_alumnos";
    private String usermy = "root";
    private String passwordmy = "";

    //
    // FB
    //
    public boolean conectar() {
        Boolean iarch;
        try {

            Class.forName("org.firebirdsql.jdbc.FBDriver");
            connection = DriverManager.getConnection("jdbc:firebirdsql:10.1.0.15:" + db, this.user, this.password);
            CrearArchivo ilog = new CrearArchivo();
            iarch = ilog.creaArchivo(1, "Conexion exitosa a FB - " + this.db);

        } catch (Exception e) {
            System.out.println(e);
            CrearArchivo ilog = new CrearArchivo();
            iarch = ilog.creaArchivo(3, "Fallo conexión a FB - " + this.db);
        }
        return true;
    }

    public String select() {

        String res = " ID | CodMov | Nombre  \n ";
        Boolean iarch;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT first 3 * FROM acad_tipo_mov order by codigo_mov");

            while (resultSet.next()) {
                res += resultSet.getString("ID") + " | "
                        + resultSet.getString("Codigo_mov") + " | "
                        + resultSet.getString("Nombre") + " \n ";
            }
            CrearArchivo ilog = new CrearArchivo();
            iarch = ilog.creaArchivo(1, "Consulta cursos de sistema Acad");
        } catch (SQLException ex) {
            System.out.println(ex);
            CrearArchivo ilog = new CrearArchivo();
            iarch = ilog.creaArchivo(3, "Fallo consulta a tabla de cursos FB");
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
            iarch = ilog.creaArchivo(1, "Desconexion exitosa a FB - " + this.db);
        } catch (SQLException ex) {
            System.out.println(ex);
            CrearArchivo ilog = new CrearArchivo();
            iarch = ilog.creaArchivo(3, "Fallo Desconexion a FB - " + this.db);
        }
    }

    //
    // MySQL
    //
    public boolean conectarmy() {
        Boolean iarch;
        try {

            // 
            // 
            Class.forName("com.mysql.jdbc.Driver");
            connectionmy = DriverManager.getConnection("jdbc:mysql://localhost/" + dbmy, this.usermy, this.passwordmy);
            CrearArchivo ilog = new CrearArchivo();
            iarch = ilog.creaArchivo(1, "Conexion exitosa a MySQL - " + this.dbmy);

        } catch (Exception e) {

            System.out.println(e);
            CrearArchivo ilog = new CrearArchivo();
            iarch = ilog.creaArchivo(3, "Fallo conexión a MySQL - " + this.dbmy);

        }
        return true;
    }

    public String selectmy() {

        String res = " SemesAño | Fac. | Año | Carnet  \n ";
        Boolean iarch;

        try {
            statementmy = connectionmy.createStatement();
            resultSetmy = statementmy.executeQuery("SELECT * FROM Inscripcion order by Referencia desc limit 3");

            while (resultSetmy.next()) {
                res += resultSetmy.getString("Semes_anio") + "    | "
                        + resultSetmy.getString("Facultad") + " | "
                        + resultSetmy.getString("Anio") + "  | "
                        + resultSetmy.getString("Carnet") + " \n ";
            }
            CrearArchivo ilog = new CrearArchivo();
            iarch = ilog.creaArchivo(1, "Consulta cursos de sistema MySQL");

        } catch (SQLException ex) {
            System.out.println(ex);
            CrearArchivo ilog = new CrearArchivo();
            iarch = ilog.creaArchivo(3, "Fallo consulta a tabla de cursos MySql");
        }
        return res;
    }

    public void desconectarmy() {
        Boolean iarch;
        try {
            resultSetmy.close();
            statementmy.close();
            connectionmy.close();
            CrearArchivo ilog = new CrearArchivo();
            iarch = ilog.creaArchivo(1, "Desconexion exitosa a MySQL - " + this.dbmy);
            // System.out.println("Desconectado de la base de datos [ " + this.dbmy + "] \n");            
        } catch (SQLException ex) {
            System.out.println(ex);
            CrearArchivo ilog = new CrearArchivo();
            iarch = ilog.creaArchivo(3, "Fallo Desconexion a MySQL - " + this.dbmy);
        }
    }

    //
    // Procesos
    //
    public String migracion() {
        Integer itotal = 0;
        Integer itotno = 0;
        String currentdia;
        Boolean iarch, icontrol, ienc;
        
        NombreArchivo icurdia = new NombreArchivo();
        currentdia = icurdia.currentFechaHora();
        
        try {

            CrearArchivo ilog = new CrearArchivo();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(""
                    + "Select semes_ano, codcarr, codcur, seccion, nombre "
                    + "FROM acad_cursos_det "
                    + "where semes_ano = '12018'");

            while (resultSet.next()) {
                statementmy = connectionmy.createStatement();
                resultSetmy = statementmy.executeQuery(
                        "select * "
                        + "  from cursos_det "
                        + " where semes_anio = '" + resultSet.getString("semes_ano") + "' and "
                        + "       cod_carr = '" + resultSet.getString("codcarr") + "' and "
                        + "       cod_curso = '" + resultSet.getString("codcur") + "' and "
                        + "       seccion = '" + resultSet.getString("seccion") + "' ");
                
                ienc = resultSetmy.next();

                if (ienc) {
                    System.out.println("Registro encontrado:    " + resultSetmy.getString("semes_anio") + "-" +
                       resultSetmy.getString("cod_carr") + "-" +
                       resultSetmy.getString("cod_curso") + "-" +
                       resultSetmy.getString("seccion"));
                    
                    itotal++;
                    
                } else {
                    String q=" INSERT INTO cursos_det " +
                                     "(semes_anio,cod_carr,cod_curso,seccion,nombre,fechasys)" +
                                     "VALUES ( " + "'" + resultSet.getString("semes_ano") + "'" + "," +
                                                 "'" + resultSet.getString("codcarr") + "'"  + "," + 
                                                 "'" + resultSet.getString("codcur") + "'" + "," +
                                                 "'" + resultSet.getString("seccion") + "'" + "," +
                                                 "'" + resultSet.getString("nombre") + "'" + "," +
                                                 "'" + currentdia + "'" + ")";
                                                 
                    try {
                        PreparedStatement pstm = connectionmy.prepareStatement(q);
                        pstm.execute();
                        pstm.close();
                    }catch(Exception e){
                        System.out.println(e);
                        System.out.println("Fallo el Insert ...");
                    }                    
                    
                    System.out.println("Registro no encontrado: " + resultSet.getString("semes_ano") + "-" +
                       resultSet.getString("codcarr") + "-" +
                       resultSet.getString("codcur") + "-" +
                       resultSet.getString("seccion"));

                    itotno++;
                }

            }
            iarch = ilog.creaArchivo(1, "Total de cursos encontrados:    " + itotal);
            iarch = ilog.creaArchivo(1, "Total de cursos no encontrados: " + itotno);

        } catch (SQLException ex) {
            System.out.println(ex);
            CrearArchivo ilog = new CrearArchivo();
            iarch = ilog.creaArchivo(3, "Fallo consulta a tabla de cursos MySql");
        }

        return "Fin Migracion";
    }
}
