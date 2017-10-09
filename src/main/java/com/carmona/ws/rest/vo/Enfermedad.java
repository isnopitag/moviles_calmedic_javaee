package com.carmona.ws.rest.vo;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Enfermedad {

    private int idEnfermedad;
    private String enefermedad;


    public int getIdEnfermedad() {
        return idEnfermedad;
    }

    public void setIdEnfermedad(int idEnfermedad) {
        this.idEnfermedad = idEnfermedad;
    }

    public String getEnefermedad() {
        return enefermedad;
    }

    public void setEnefermedad(String enefermedad) {
        this.enefermedad = enefermedad;
    }

    public boolean buscar()
    {

        Conexion conexion = new Conexion();
        boolean obtuvo = false;
        if(conexion.getConexion() != null)
        {
            try {
                String query = "select * from enfermedad where idenfermedad ="+idEnfermedad+";";
                ResultSet rs = conexion.getConexion().createStatement().executeQuery(query);
                while(rs.next())
                {
                    idEnfermedad = rs.getInt("idenfermedad");
                    enefermedad = rs.getString("enfermedad");
                    obtuvo = true;
                }
                conexion.getConexion().close();
            }catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return obtuvo;
    }

    public List<Enfermedad> todos()
    {
        List<Enfermedad> enfermedades = null;
        Conexion conexion = new Conexion();
        if(conexion.getConexion() != null)
        {
            enfermedades = new ArrayList<Enfermedad>();
            try {
                String query = "select * from enfermedad;";
                ResultSet rs = conexion.getConexion().createStatement().executeQuery(query);
                while(rs.next())
                {
                    Enfermedad enfermedad = new Enfermedad(
                            rs.getInt("idenfermedad"),
                            rs.getString("enfermedad")
                    );
                    enfermedades.add(enfermedad);
                }
                conexion.getConexion().close();
            }catch (Exception e) {
                e.getMessage();
            }
        }

        return enfermedades;
    }

    public boolean guardar()
    {
        Conexion conexion = new Conexion();
        if(conexion.getConexion() != null)
        {
            try {
                String query = "insert into enfermedad(enfermedad) values ('"+enefermedad+"');";
                conexion.getConexion().createStatement().execute(query);
                conexion.getConexion().close();
            }catch(Exception ex) {
                System.err.println(ex.getMessage());
            }
            return true;
        }

        return false;
    }

    public boolean eliminar()
    {
        Conexion conexion = new Conexion();
        if(conexion.getConexion() != null)
        {
            try {
                String query = "delete from enfermedad where idenfermedad = "+idEnfermedad+";";
                conexion.getConexion().createStatement().execute(query);
                conexion.getConexion().close();
            }catch(Exception ex) {
                System.err.println(ex.getMessage());
            }
            return true;
        }
        return false;
    }

    public Enfermedad(){}

    public Enfermedad (int idEnfermedad, String enefermedad) {
        super();
        this.idEnfermedad = idEnfermedad;
        this.enefermedad = enefermedad;
    }
    @Override
    public String toString() {
        return "Enfermedad [id=" + idEnfermedad + ", enfermedad=" + enefermedad  + "]";
    }
}
