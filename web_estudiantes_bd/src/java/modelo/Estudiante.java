package modelo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.sql.PreparedStatement;
import javax.swing.table.DefaultTableModel;

public class Estudiante extends Persona {
    private String carne;
    private int id_tipo_sangre;
    private Conexion cn;
    public Estudiante() {
        
    }

    public Estudiante(String carne, int id_tipo_sangre, int id, String nombres, String apellidos, String direccion, String telefono, String correo_electronico, String fecha_nacimiento) {
        super(id, carne, nombres, apellidos, direccion, telefono, correo_electronico, fecha_nacimiento);
        this.carne = carne;
        this.id_tipo_sangre = id_tipo_sangre;
    }

    public String getCarne() {
        return carne;
    }

    public void setCarne(String carne) {
        this.carne = carne;
    }

    public int getId_tipo_sangre() {
        return id_tipo_sangre;
    }

    public void setId_tipo_sangre(int id_tipo_sangre) {
        this.id_tipo_sangre = id_tipo_sangre;
    }
 public DefaultTableModel leer(){
 DefaultTableModel tabla = new DefaultTableModel();
 try{
      cn = new Conexion();
      cn.abrir_conexion();
      String query = "select e.id_estudiantes as id,e.carne,e.nombres,e.apeillidos,e.direccion,e.telefono,e.correo_electronico,t.sangre,e.fecha_nacimiento,e.id_tipo_sangre FROM estudiantes as e inner join tipos_sangre as t on e.id_tipo_sangre = t.id_tipo_sangre;\"";
      ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
      String encabezado[] = {"id","carne","nombres","apellidos","direccion","telefono","correo_electronico","tipo","nacimiento"};
      tabla.setColumnIdentifiers(encabezado);
      String datos[] = new String[10];
      while (consulta.next()){
          datos[0] = consulta.getString("id");
          datos[1] = consulta.getString("codigo");
          datos[2] = consulta.getString("nombres");
          datos[3] = consulta.getString("apellidos");
          datos[4] = consulta.getString("direccion");
          datos[5] = consulta.getString("telefono");
          datos[6] = consulta.getString("correo_electronico");
          datos[7] = consulta.getString("tipo_sangre");
          datos[8] = consulta.getString("fecha_nacimiento");
          tabla.addRow(datos);
      
      }
      
      cn.cerrar_conexion();
 }catch(Exception ex){
     System.out.println(ex.getMessage());
 }
 return tabla;
 }
    @Override
public int agregar (){
    int retorno = 0;
    try{
        PreparedStatement parametro;
        cn = new Conexion();
        String query="INSERT INTO estudiantes(carne,nombres,apellidos,direccion,telefono,correo_electronico,id_tipo_sangre,fecha_nacimiento) VALUES(?,?,?,?,?,?,?,?,);";
        cn.abrir_conexion();
        parametro = (PreparedStatement) cn.conexionBD.reparedStatement (query);
        parametro.setString(1, getCarne());
        parametro.setString(2, getNombres());
        parametro.setString(3, getApellidos());
        parametro.setString(4, getDireccion());
        parametro.setString(5, getTelefono());
        parametro.setString(6, getCorreo_Electronico());
        parametro.setInt(7, getId_tipo_sangre());
        parametro.setString(8, getFecha_Nacimiento());
        
        retorno =parametro.executeUpdate();
        cn.cerrar_conexion();
    }catch(Exception ex){
       System.out.println(ex.getMessage());
       retorno = 0;
    }
    
    return retorno;
}
    @Override
    public int modificar(){
    int retorno = 0;
    try{
        PreparedStatement parametro;
        cn = new Conexion();
        String query="update estudiantes set carne=?,nombres=?,apellidos=?,direccion=?,telefono=?,correo_electronico=?,id_tipo_sangre=?,fecha_nacimiento=? where id_estudiante = ?;";
        cn.abrir_conexion();
        parametro = (PreparedStatement) cn.conexionBD.reparedStatement (query);
        parametro.setString(1, getCarne());
        parametro.setString(2, getNombres());
        parametro.setString(3, getApellidos());
        parametro.setString(4, getDireccion());
        parametro.setString(5, getTelefono());
        parametro.setString(6, getCorreo_Electronico());
        parametro.setInt(7, getId_tipo_sangre());
        parametro.setString(8, getFecha_Nacimiento());
        parametro.setInt(9, getId());
        retorno =parametro.executeUpdate();
        cn.cerrar_conexion();
    }catch(Exception ex){
       System.out.println(ex.getMessage());
       retorno = 0;
    }
    
    return retorno;
}
    @Override
    public int eliminar(){
    int retorno = 0;
    try{
        PreparedStatement parametro;
        cn = new Conexion();
        String query="delete from estudiantes where id_estudiante = ?;";
        cn.abrir_conexion();
        parametro = (PreparedStatement) cn.conexionBD.reparedStatement (query);        
        parametro.setInt(1, getId());
        retorno =parametro.executeUpdate();
        cn.cerrar_conexion();
    }catch(Exception ex){
       System.out.println(ex.getMessage());
       retorno = 0;
    }
    
    return retorno;
}

    private String getCorreo_Electronico() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private String getFecha_Nacimiento() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
