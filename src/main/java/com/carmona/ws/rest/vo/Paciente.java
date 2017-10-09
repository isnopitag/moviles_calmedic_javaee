package com.carmona.ws.rest.vo;

import javax.xml.bind.annotation.XmlElement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Paciente {
    private int idPaciente;
    private String nombre;
    private int idUsuario;
    private Usuario usuario;

    @XmlElement(required = true)
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public boolean buscar()
    {

        Conexion conexion = new Conexion();
        boolean obtuvo = false;
        if(conexion.getConexion() != null)
        {
            try {
                String query = "select * from paciente where idpaciente ="+idPaciente+";";
                ResultSet rs = conexion.getConexion().createStatement().executeQuery(query);
                while(rs.next())
                {
                    idPaciente= rs.getInt("idpaciente");
                    nombre=	rs.getString("nombre");
                    idUsuario= rs.getInt("idpaciente");
                    obtuvo = true;
                }
                conexion.getConexion().close();
            }catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return obtuvo;
    }

    public List<Paciente> todos()
    {
        List<Paciente> pacientes = null;
        Conexion conexion = new Conexion();
        if(conexion.getConexion() != null)
        {
            pacientes = new ArrayList<Paciente>();
            try {
                String query = "select * from paciente;";
                ResultSet rs = conexion.getConexion().createStatement().executeQuery(query);
                while(rs.next())
                {
                    Usuario usuario = new Usuario();
                    usuario.setIdusuario(rs.getInt("idUsuario"));
                    usuario.viewUsuario();

                    Paciente paciente = new Paciente(
                            rs.getInt("idpaciente"),
                            rs.getString("nombre"),
                            rs.getInt("idusuario")
                    );
                    paciente.setUsuario(usuario);
                    pacientes.add(paciente);

                }
                conexion.getConexion().close();
            }catch (Exception e) {
                e.getMessage();
            }
        }

        return pacientes;
    }

    public boolean guardar()
    {
        Conexion conexion = new Conexion();
        if(conexion.getConexion() != null)
        {
            try {
                String query = "insert into paciente(nombre) values ('"+nombre+"');";
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
                String query = "delete from paciente where idpaciente = "+idPaciente+";";
                conexion.getConexion().createStatement().execute(query);
                conexion.getConexion().close();
            }catch(Exception ex) {
                System.err.println(ex.getMessage());
            }
            return true;
        }
        return false;
    }
    public Paciente() {

    }

    @Override
    public String toString() {
        return "Paciente{" +
                "idPaciente=" + idPaciente +
                ", nombre='" + nombre + '\'' +
                ", idUsuario=" + idUsuario +
                '}';
    }

    public Paciente(int idPaciente, String nombre, int idUsuario) {
        this.idPaciente = idPaciente;
        this.nombre = nombre;
        this.idUsuario = idUsuario;
    }
}
