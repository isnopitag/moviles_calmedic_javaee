package com.carmona.ws.rest.service;

import com.carmona.ws.rest.vo.Enfermedad;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/enfermedad")
public class EnfermedadService {
    @GET
    @Path("/{id}")
    public Response obtenerEnfermedad(@PathParam("id") int id)
    {
        Enfermedad enfermedad = new Enfermedad();
        enfermedad.setIdEnfermedad(id);
        if(enfermedad.buscar()) {
            return Response.ok(enfermedad, MediaType.APPLICATION_JSON).build();
        }
        return Response.ok("{\"mensaje\":\"No se encontro el enfermedad\"}").build();
    }
    @GET
    public Response obtenerEnfermedad()
    {
        Enfermedad enfermedad = new Enfermedad();
        List<Enfermedad> enfermedades = enfermedad.todos();
        if(enfermedades != null)
            return Response.ok(enfermedades,MediaType.APPLICATION_JSON).build();
        return Response.status(Response.Status.NOT_FOUND).build();

    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response guardarEnfermdad(Enfermedad enfermedad)
    {
        if(enfermedad.guardar())
            return Response.ok("{\"guardado\":true}",MediaType.APPLICATION_JSON).build();
        return Response.ok("{\"guardado\":false}",MediaType.APPLICATION_JSON).build();
    }

    @DELETE
    @Path("{id}")
    public Response eliminar(@PathParam("id") int id)
    {
        Enfermedad enfermedad = new Enfermedad();

        enfermedad.setIdEnfermedad(id);
        if(enfermedad.eliminar())
            return Response.ok("{\"eliminado\":true}",MediaType.APPLICATION_JSON).build();
        return Response.ok("{\"eliminado\":false}",MediaType.APPLICATION_JSON).build();
    }
}
