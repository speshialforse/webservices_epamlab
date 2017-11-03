package by.wolterskluwer.rs.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import by.wolterskluwer.rs.model.Chapter;
import by.wolterskluwer.rs.model.Document;

@Path("/documentservice/")
@Consumes(MediaType.APPLICATION_XML)
@Produces(MediaType.APPLICATION_JSON)
public interface IDocumentService {
	
	@GET
	@Path("/document/{id}")
	public Response getDocument(@PathParam("id") int id);	

	@GET
	@Path("/document/{id}/chapter/{chapterId}")
	public Response getChapter(@PathParam("id") int id , @PathParam("chapterId") int chapterId);

	@POST
	@Path("/document")
	public Response createDocument(Document document);

	@PUT
	@Path("/document/updateDocument")
	public Response updateDocument(Document document);

	@DELETE
	@Path("/document/deleteDocument/{id}")
	public Response deleteDocument(@PathParam("id") int id);
	
	@PUT
    @Path("/document/{id}/chapter")
    public Response updateChapter(@PathParam("id") int id , Chapter chapter);

}
