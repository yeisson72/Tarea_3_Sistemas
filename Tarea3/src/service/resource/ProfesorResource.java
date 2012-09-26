package service.resources;
import java.util.ArrayList;
import java.util.Map;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;
import service.beans.Grupo;
import service.beans.Profesor;
import service.storage.ProfesorStore;
import service.util.ParamUtil;

import com.sun.jersey.api.NotFoundException;

public class ProfesorResource {
    @Context
    UriInfo uriInfo;
    @Context
    Request request;
    String profesor;

    public ProfesorResource(UriInfo uriInfo, Request request, String profesor) {
        this.uriInfo = uriInfo;
        this.request = request;
        this.profesor = profesor;
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Profesor getProfesor() {
        Profesor prof = ProfesorStore.getStore().get(profesor);
        if(prof==null)
            throw new NotFoundException("No such Profesor.");
        return prof;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public Response putProfesor(JAXBElement<Profesor> jaxbProfesor) {
        Profesor p = jaxbProfesor.getValue();
        return putAndGetResponse(p);
    }

    @PUT
    public Response putProfesor(@Context HttpHeaders herders, byte[] in) {
        Map<String,String> params = ParamUtil.parse(new String(in));
        Profesor p = new Profesor(params.get("id"), params.get("cedula"), params.get("nombre"), params.get("titulo"), params.get("area"), params.get("correo"), params.get("telefono"),
                new ArrayList<Grupo>());
        return putAndGetResponse(p);
    }

    private Response putAndGetResponse(Profesor p) {
        Response res;
        if(ProfesorStore.getStore().containsKey(p.getId())) {
            res = Response.noContent().build();
        } else {
            res = Response.created(uriInfo.getAbsolutePath()).build();
        }
        ProfesorStore.getStore().put(p.getId(), p);
        return res;
    }

    @DELETE
    public void deleteProfesor() {
        Profesor p = ProfesorStore.getStore().remove(profesor);
        if(p==null)
            throw new NotFoundException("No such Profesor.");
    }
}