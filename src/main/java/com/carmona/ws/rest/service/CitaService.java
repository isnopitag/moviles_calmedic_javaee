package com.carmona.ws.rest.service;

import com.carmona.ws.rest.vo.Cita;
import com.carmona.ws.rest.vo.Paciente;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/cita")
public class CitaService {
    @GET
    @Path("/{id}")
    public Response obtenerCita(@PathParam("id") int id)
    {
        Cita cita = new Cita();
        cita.setIdCita(id);
        if(cita.buscar()) {
            return Response.ok(cita, MediaType.APPLICATION_JSON).build();
        }
        return Response.ok("{\"mensaje\":\"No se encontro el cliente\"}").build();
    }
    @GET
    public Response obtenerCita()
    {
        Cita cita = new Cita();
        List<Cita> pacientes = cita.todos();
        if(pacientes != null)
            return Response.ok(pacientes,MediaType.APPLICATION_JSON).build();
        return Response.status(Response.Status.NOT_FOUND).build();

    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response guardarCita(Cita cita)
    {
        if(cita.guardar())
            return Response.ok("{\"guardado\":true}",MediaType.APPLICATION_JSON).build();
        return Response.ok("{\"guardado\":false}",MediaType.APPLICATION_JSON).build();
    }

    @DELETE
    @Path("{id}")
    public Response eliminar(@PathParam("id") int id)
    {
        Cita cita = new Cita();
        cita.setIdCita(id);
        if(cita.eliminar())
            return Response.ok("{\"eliminado\":true}",MediaType.APPLICATION_JSON).build();
        return Response.ok("{\"eliminado\":false}",MediaType.APPLICATION_JSON).build();
    }
}
