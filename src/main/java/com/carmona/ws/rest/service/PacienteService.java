package com.carmona.ws.rest.service;

import com.carmona.ws.rest.vo.Paciente;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/cliente")
public class PacienteService {
    @GET
    @Path("/{id}")
    public Response obtenerPaciente(@PathParam("id") int id)
    {
        Paciente paciente = new Paciente();
        paciente.setIdUsuario(id);
        if(paciente.buscar()) {
            return Response.ok(paciente, MediaType.APPLICATION_JSON).build();
        }
        return Response.ok("{\"mensaje\":\"No se encontro el cliente\"}").build();
    }
    @GET
    public Response obtenerPaciente()
    {
        Paciente paciente = new Paciente();
        List<Paciente> pacientes = paciente.todos();
        if(pacientes != null)
            return Response.ok(pacientes,MediaType.APPLICATION_JSON).build();
        return Response.status(Response.Status.NOT_FOUND).build();

    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response guardarCliente(Paciente paciente)
    {
        if(paciente.guardar())
            return Response.ok("{\"guardado\":true}",MediaType.APPLICATION_JSON).build();
        return Response.ok("{\"guardado\":false}",MediaType.APPLICATION_JSON).build();
    }
    @DELETE
    @Path("{id}")
    public Response eliminar(@PathParam("id") int id)
    {
        Paciente paciente = new Paciente();
        paciente.setIdUsuario(id);
        if(paciente.eliminar())
            return Response.ok("{\"eliminado\":true}",MediaType.APPLICATION_JSON).build();
        return Response.ok("{\"eliminado\":false}",MediaType.APPLICATION_JSON).build();
    }
}
