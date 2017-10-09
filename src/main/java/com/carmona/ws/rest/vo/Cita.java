package com.carmona.ws.rest.vo;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Cita {
    private int idCita;
    private String cita;
    private Date fecha;
    private int idPaciente;
    private int idMedico;

    public int getIdCita() {
        return idCita;
    }

    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }

    public String getCita() {
        return cita;
    }

    public void setCita(String cita) {
        this.cita = cita;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public int getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(int idMedico) {
        this.idMedico = idMedico;
    }

    public boolean buscar()
    {

        Conexion conexion = new Conexion();
        boolean obtuvo = false;
        if(conexion.getConexion() != null)
        {
            try {
                String query = "select * from cita where idcita ="+idCita+";";
                ResultSet rs = conexion.getConexion().createStatement().executeQuery(query);
                while(rs.next())
                {
                    idPaciente= rs.getInt("idpaciente");
                    cita=	rs.getString("cita");
                    fecha= rs.getDate("fecha");
                    idPaciente = rs.getInt("idpaciente");
                    idMedico = rs.getInt("idmedico");
                    obtuvo = true;
                }
                conexion.getConexion().close();
            }catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return obtuvo;
    }

    public List<Cita> todos()
    {
        List<Cita> citas = null;
        Conexion conexion = new Conexion();
        if(conexion.getConexion() != null)
        {
            citas = new ArrayList<Cita>();
            try {
                String query = "select * from cita;";
                ResultSet rs = conexion.getConexion().createStatement().executeQuery(query);
                while(rs.next())
                {
                    Paciente paciente = new Paciente();
                    paciente.setIdPaciente(rs.getInt("idPaciente"));
                    //paciente.viewUsuario();

                    Cita cita = new Cita(
                            rs.getInt("idcita"),
                            rs.getString("cita"),
                            rs.getDate("fecha"),
                            rs.getInt("idpaciente"),
                            rs.getInt("idmedico")
                    );
                    //paciente.setUsuario(usuario);
                    citas.add(cita);

                }
                conexion.getConexion().close();
            }catch (Exception e) {
                e.getMessage();
            }
        }

        return citas;
    }

    public boolean guardar()
    {
        Conexion conexion = new Conexion();
        if(conexion.getConexion() != null)
        {
            try {
                String query = "insert into cita(cita,fecha,idpaciente,idmedico) values ('"+cita+"',"+fecha+","+idPaciente+","+idMedico+");";
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
                String query = "delete from cita where idcita = "+idCita+";";
                conexion.getConexion().createStatement().execute(query);
                conexion.getConexion().close();
            }catch(Exception ex) {
                System.err.println(ex.getMessage());
            }
            return true;
        }
        return false;
    }

    public  Cita(){

    }

    @Override
    public String toString() {
        return "Cita{" +
                "idCita=" + idCita +
                ", cita='" + cita + '\'' +
                ", fecha=" + fecha +
                ", idPaciente=" + idPaciente +
                ", idMedico=" + idMedico +
                '}';
    }

    public Cita(int idCita, String cita, Date fecha, int idPaciente, int idMedico) {
        this.idCita = idCita;
        this.cita = cita;
        this.fecha = fecha;
        this.idPaciente = idPaciente;
        this.idMedico = idMedico;
    }
}
