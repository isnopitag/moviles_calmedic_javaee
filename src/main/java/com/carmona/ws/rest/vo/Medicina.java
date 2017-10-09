package com.carmona.ws.rest.vo;


import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Medicina {
    private int idMedicina;
    private String medicina;

    public int getIdMedicina() {
        return idMedicina;
    }

    public void setIdMedicina(int idMedicina) {
        this.idMedicina = idMedicina;
    }

    public String getMedicina() {
        return medicina;
    }

    public void setMedicina(String medicina) {
        this.medicina = medicina;
    }

    public boolean buscar()
    {

        Conexion conexion = new Conexion();
        boolean obtuvo = false;
        if(conexion.getConexion() != null)
        {
            try {
                String query = "select * from medicinas where id ="+idMedicina+";";
                ResultSet rs = conexion.getConexion().createStatement().executeQuery(query);
                while(rs.next())
                {
                    idMedicina = rs.getInt("id");
                    medicina = rs.getString("medicina");
                    obtuvo = true;
                }
                conexion.getConexion().close();
            }catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return obtuvo;
    }

    public List<Medicina> todos() {
        List<Medicina> medicinas = null;
        Conexion conexion = new Conexion();
        if (conexion.getConexion() != null) {
            medicinas = new ArrayList<Medicina>();
            try {
                String query = "select * from medicinas;";
                ResultSet rs = conexion.getConexion().createStatement().executeQuery(query);
                while (rs.next()) {
                    Medicina medicina = new Medicina(
                            rs.getInt("id"),
                            rs.getString("medicina")
                    );
                    medicinas.add(medicina);
                }
                conexion.getConexion().close();
            } catch (Exception e) {
                e.getMessage();
            }
        }
        return medicinas;
    }

    public boolean guardar()
    {
        Conexion conexion = new Conexion();
        if(conexion.getConexion() != null)
        {
            try {
                String query = "insert into medicinas(medicina) values ('"+medicina+"');";
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
                String query = "delete from medicina where id = "+idMedicina+";";
                conexion.getConexion().createStatement().execute(query);
                conexion.getConexion().close();
            }catch(Exception ex) {
                System.err.println(ex.getMessage());
            }
            return true;
        }
        return false;
    }

    public Medicina(){}

    public Medicina (int idMedicina, String medicina) {
        super();
        this.idMedicina = idMedicina;
        this.medicina = medicina;
    }

    @Override
    public String toString() {
        return "Enfermedad [id=" + idMedicina + ", enfermedad=" + medicina  + "]";
    }

}
