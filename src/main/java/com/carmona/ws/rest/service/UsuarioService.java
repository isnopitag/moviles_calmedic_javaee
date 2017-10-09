package com.carmona.ws.rest.service;

import com.carmona.ws.rest.vo.Usuario;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/usuarios")
public class UsuarioService {
    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)

    public Usuario loginUser(Usuario objU){
        objU.validaUsuario();
        return objU;
    }

}
