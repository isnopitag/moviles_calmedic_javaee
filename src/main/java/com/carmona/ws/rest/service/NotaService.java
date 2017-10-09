package com.carmona.ws.rest.service;

import com.carmona.ws.rest.vo.Nota;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/nota")
public class NotaService {

    @GET
    @Path("/{id}")
    public Response obtenerNota(@PathParam("id") int id)
    {
        Nota nota = new Nota();
        nota.setIdNota(id);
        if(nota.buscar()) {
            return Response.ok(nota, MediaType.APPLICATION_JSON).build();
        }
        return Response.ok("{\"mensaje\":\"No se encontro el cliente\"}").build();
    }
    @GET
    public Response obtenerPaciente()
    {
        Nota nota = new Nota();
        List<Nota> notas = nota.todos();
        if(notas != null)
            return Response.ok(notas,MediaType.APPLICATION_JSON).build();
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response guardarCliente(Nota nota)
    {
        if(nota.guardar())
            return Response.ok("{\"guardado\":true}",MediaType.APPLICATION_JSON).build();
        return Response.ok("{\"guardado\":false}",MediaType.APPLICATION_JSON).build();
    }
    @DELETE
    @Path("{id}")
    public Response eliminar(@PathParam("id") int id)
    {
        Nota nota = new Nota();
        nota.setIdNota(id);
        if(nota.eliminar())
            return Response.ok("{\"eliminado\":true}",MediaType.APPLICATION_JSON).build();
        return Response.ok("{\"eliminado\":false}",MediaType.APPLICATION_JSON).build();
    }

}
