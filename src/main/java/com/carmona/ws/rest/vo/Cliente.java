package com.carmona.ws.rest.vo;

import javax.xml.bind.annotation.XmlElement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Cliente 
{
	private int id;
	private String nombre;
	private String email;
	private String foto;
	private int idUsuario;
	private Usuario usr;


	@XmlElement(required = true)
	public Usuario getUsr() {
		return usr;
	}

	public void setUsr(Usuario usr) {
		this.usr = usr;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
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
				String query = "select * from cliente where idcliente ="+id+";";
				ResultSet rs = conexion.getConexion().createStatement().executeQuery(query);
				while(rs.next())
				{
					id= rs.getInt("idcliente");
					nombre=	rs.getString("nomcliente");
					email=rs.getString("emailcliente");
					foto=rs.getString("fotocte");
					idUsuario=rs.getInt("idUsr");

					obtuvo = true;
				}
				conexion.getConexion().close();
			}catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
		return obtuvo;
	}
	
	public List<Cliente> todos()
	{
		List<Cliente> clientes = null;
		Conexion conexion = new Conexion();
		if(conexion.getConexion() != null)
		{
			clientes = new ArrayList<Cliente>();
			try {
				String query = "select * from cliente;";
				ResultSet rs = conexion.getConexion().createStatement().executeQuery(query);
				while(rs.next())
				{
					Usuario usuario = new Usuario();
					usuario.setIdusuario(rs.getInt("idUsr"));
					usuario.viewUsuario();

					Cliente cliente = new Cliente(
							rs.getInt("idcliente"),
							rs.getString("nomcliente"),
							rs.getString("emailcliente"),
							rs.getString("fotocte"),
							rs.getInt("idUsr")
					);
					cliente.setUsr(usuario);
					clientes.add(cliente);

				}
				conexion.getConexion().close();
			}catch (Exception e) {
				e.getMessage();
			}
		}
		
		return clientes;
	}
	
	public boolean guardar()
	{
		Conexion conexion = new Conexion();
		if(conexion.getConexion() != null)
		{
			try {
				String query = "insert into cliente(nomcliente,emailcliente,fotocte) values ('"+nombre+"','"+email+"','"+foto+"');";
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
				String query = "delete from cliente where idcliente = "+id+";";
				conexion.getConexion().createStatement().execute(query);
				conexion.getConexion().close();
			}catch(Exception ex) {
				System.err.println(ex.getMessage());
			}
			return true;
		}
		return false;
	}
	public Cliente() {
		
	}
	public Cliente(int id, String nombre, String email, String foto, int idUsuario) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.email = email;
		this.foto = foto;
		this.idUsuario = idUsuario;
	}
	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nombre=" + nombre + ", email=" + email + ", foto=" + foto + ", idUsuario="
				+ idUsuario + "]";
	}
	
	
}
