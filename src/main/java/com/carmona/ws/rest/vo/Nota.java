package com.carmona.ws.rest.vo;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Nota {
    private int idNota;
    private String titulo;
    private String detalle;

    public int getIdNota() {
        return idNota;
    }

    public void setIdNota(int idNota) {
        this.idNota = idNota;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public boolean buscar()
    {

        Conexion conexion = new Conexion();
        boolean obtuvo = false;
        if(conexion.getConexion() != null)
        {
            try {
                String query = "select * from nota where idnota ="+idNota+";";
                ResultSet rs = conexion.getConexion().createStatement().executeQuery(query);
                while(rs.next())
                {
                    idNota= rs.getInt("idnota");
                    titulo=	rs.getString("titulo");
                    detalle = rs.getString("detalle");
                    obtuvo = true;
                }
                conexion.getConexion().close();
            }catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return obtuvo;
    }

    public List<Nota> todos()
    {
        List<Nota> notas = null;
        Conexion conexion = new Conexion();
        if(conexion.getConexion() != null)
        {
            notas = new ArrayList<Nota>();
            try {
                String query = "select * from nota;";
                ResultSet rs = conexion.getConexion().createStatement().executeQuery(query);
                while(rs.next())
                {
                    /*Usuario usuario = new Usuario();
                    usuario.setIdusuario(rs.getInt("idUsuario"));
                    usuario.viewUsuario();*/

                    Nota nota = new Nota(
                            rs.getInt("idnota"),
                            rs.getString("titulo"),
                            rs.getString("detalle")
                    );
                    //paciente.setUsuario(usuario);
                    notas.add(nota);
                }
                conexion.getConexion().close();
            }catch (Exception e) {
                e.getMessage();
            }
        }

        return notas;
    }

    public boolean guardar()
    {
        Conexion conexion = new Conexion();
        if(conexion.getConexion() != null)
        {
            try {
                String query = "insert into nota(titulo,detalle) values ('"+titulo+"','"+detalle+"');";
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
                String query = "delete from nota where idnota = "+idNota+";";
                conexion.getConexion().createStatement().execute(query);
                conexion.getConexion().close();
            }catch(Exception ex) {
                System.err.println(ex.getMessage());
            }
            return true;
        }
        return false;
    }


    public Nota(){

    }

    public Nota(int idNota, String titulo, String detalle) {
        this.idNota = idNota;
        this.titulo = titulo;
        this.detalle = detalle;
    }

    @Override
    public String toString() {
        return "Nota{" +
                "idNota=" + idNota +
                ", titulo='" + titulo + '\'' +
                ", detalle='" + detalle + '\'' +
                '}';
    }
}
