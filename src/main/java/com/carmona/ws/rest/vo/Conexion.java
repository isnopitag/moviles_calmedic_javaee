package com.carmona.ws.rest.vo;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
	private Connection conexion;
	
	public Conexion()
	{
		try {
			Class.forName("org.postgresql.Driver");
			conexion = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/calmedic","carmona","1337");
				if(conexion == null)
			{
				System.out.println("La conexion fallo");
			}
		}catch (Exception e) {
			e.getMessage();
		}
	}
	
	public Connection getConexion() {
		
		return conexion;
	}
	
	
}
	