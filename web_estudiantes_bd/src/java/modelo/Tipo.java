/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.

 */
package modelo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class Tipo {
    private int id_tipo_sangre;
    private String tipo_sangre;
    private Conexion cn;
    public Tipo(int id_tipo_sangre, String tipo_sangre) {
        this.id_tipo_sangre = id_tipo_sangre;
        this.tipo_sangre = tipo_sangre;
    }

    public int getId_tipo_sangre() {
        return id_tipo_sangre;
    }

    public void setId_tipo_sangre(int id_tipo_sangre) {
        this.id_tipo_sangre = id_tipo_sangre;
    }

    public String getTipo_sangre() {
        return tipo_sangre;
    }

    public void setTipo_sangre(String tipo_sangre) {
        this.tipo_sangre = tipo_sangre;
    }
    public HashMap drop_sangre(){
        HashMap<String,String> drop = new HashMap();
        try{
            cn = new Conexion();
            String query= "SELECT id_tipo_sangre as id,tipos_sangre FROM tipos_sangre;";
            cn.abrir_conexion();
            ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
            while (consulta.next()){
                drop.put(consulta.getString("id"), consulta.getString("tipo_sangre"));
            }
            cn.cerrar_conexion();
            
        }catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return drop;
    }
    
}
