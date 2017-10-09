package com.carmona.ws.rest.vo;


import java.sql.ResultSet;

public class Usuario {

	private int idusuario;
	private String usuario;
	private String password;
	private boolean userValido;
	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getIdusuario() {
		return idusuario;
	}

	public void setIdusuario(int idusuario) {
		this.idusuario = idusuario;
	}

	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isUserValido() {
		return userValido;
	}
	public void setUserValido(boolean userValido) {
		this.userValido = userValido;
	}


	public void viewUsuario(){
		try {
			Conexion conexion = new Conexion();

			String query = "SELECT * FROM usuario where idusuario = "+ idusuario;
			ResultSet res = conexion.getConexion().createStatement().executeQuery(query);
			if(res.next()){

				usuario = res.getString("usuario");
				password = res.getString("password");
			}
		}catch (Exception e){}
	}

	public void validaUsuario(){
		try {
			Conexion conexion = new Conexion();
			String query = "SELECT * FROM usuario Where usuario = "+ usuario +"and password = "+password;
			ResultSet res = conexion.getConexion().createStatement().executeQuery(query);
			if(res.next()){
				token = ""; //Token?
			}else{
				token = "Sesion no valida";
			}

		}catch (Exception e){}
	}
}
