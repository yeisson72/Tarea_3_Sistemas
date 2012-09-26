package service.resources;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import service.beans.Grupo;
import service.beans.Profesor;
import service.storage.ProfesorStore;

@Path("/profesores")
public class ProfesoresResource {

    @Context
    UriInfo uriInfo;
    @Context
    Request request;

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Profesor> getProfesores() {
        List<Profesor> profesores = new ArrayList<Profesor>();
        profesores.addAll( ProfesorStore.getStore().values() );
        return profesores;
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String getCount() {
        int count = ProfesorStore.getStore().size();
        return String.valueOf(count);
    }

    @POST
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void newProfesor(
            @FormParam("id") String id,
            @FormParam("cedula") String cedula,
            @FormParam("nombre") String nombre,
            @FormParam("titulo") String titulo,
            @FormParam("area") String area,
            @FormParam("correo") String correo,
            @FormParam("telefono") String telefono,
            @Context HttpServletResponse servletResponse
    ) throws IOException {
        Profesor p = new Profesor(id,cedula,nombre,titulo,area,correo,telefono,new ArrayList<Grupo>());
        ProfesorStore.getStore().put(id, p);

        URI uri = uriInfo.getAbsolutePathBuilder().path(id).build();
        Response.created(uri).build();

        servletResponse.sendRedirect("../pages/new_profesor.html");
    }

    @Path("{profesor}")
    public ProfesorResource getProfesor(
            @PathParam("profesor") String profesor) {
        return new ProfesorResource(uriInfo, request, profesor);
    }
}