package com.carmona.ws.rest.service;

import com.carmona.ws.rest.vo.Enfermedad;
import com.carmona.ws.rest.vo.Medicina;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/medicina")
public class MedicinaService {
    @GET
    @Path("/{id}")
    public Response obtenerMedicina(@PathParam("id") int id)
    {
        Medicina medicina = new Medicina();
        medicina.setIdMedicina(id);
        if(medicina.buscar()) {
            return Response.ok(medicina, MediaType.APPLICATION_JSON).build();
        }
        return Response.ok("{\"mensaje\":\"No se encontro la medicina\"}").build();
    }
    @GET
    public Response obtenerMedicina()
    {
        Medicina medicina = new Medicina();
        List<Medicina> medicinas = medicina.todos();
        if(medicinas != null)
            return Response.ok(medicinas,MediaType.APPLICATION_JSON).build();
        return Response.status(Response.Status.NOT_FOUND).build();

    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response guardarMedicina(Medicina medicina)
    {
        if(medicina.guardar())
            return Response.ok("{\"guardado\":true}",MediaType.APPLICATION_JSON).build();
        return Response.ok("{\"guardado\":false}",MediaType.APPLICATION_JSON).build();
    }

    @DELETE
    @Path("{id}")
    public Response eliminar(@PathParam("id") int id)
    {
        Medicina medicina = new Medicina();

        medicina.setIdMedicina(id);
        if(medicina.eliminar())
            return Response.ok("{\"eliminado\":true}",MediaType.APPLICATION_JSON).build();
        return Response.ok("{\"eliminado\":false}",MediaType.APPLICATION_JSON).build();
    }
}
