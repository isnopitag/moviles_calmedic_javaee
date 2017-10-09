package com.carmona.ws.rest.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.carmona.ws.rest.vo.Usuario;

@Path("/servicio")
public class ServiceLogin {

	@POST
	@Path("/validar")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Usuario validar(Usuario usuario)
	{
		usuario.setUserValido(false);
		if(usuario.getUsuario().equals("Luisito") && usuario.getPassword().equals("12345"))
		{
			usuario.setUserValido(true);
		}
		return usuario;
	}
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/saludo")
	public Usuario saludo ()
	{
		Usuario usuario = new Usuario();
		usuario.setUsuario("Luisito");
		return usuario;
	}
}
