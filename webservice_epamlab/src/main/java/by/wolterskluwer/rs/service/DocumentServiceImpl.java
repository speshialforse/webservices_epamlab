package by.wolterskluwer.rs.service;

import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import by.wolterskluwer.rs.dao.DocumentManagerMemoryDao;
import by.wolterskluwer.rs.model.Chapter;
import by.wolterskluwer.rs.model.Document;


public class DocumentServiceImpl implements IDocumentService{

	private DocumentManagerMemoryDao documents;
	
	public void init() {

	documents = new DocumentManagerMemoryDao();

	}

	public DocumentServiceImpl() {
		init();
	}
	
	@Override
	public Response getDocument(@PathParam("id") int id) {
		Document d = documents.getDocument(id);
		if (d == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
		return Response.ok(d).build();
	}

	@Override
	public Response getChapter(int id, int chapterId) {
		Chapter c = documents.getChapter(id, chapterId);
		if (c == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
		return Response.ok(c).build();
	}

	@Override
	public Response createDocument(Document document) {
		documents.createDocument(document);
		return Response.status(Response.Status.CREATED).entity(document).build();
	}

	@Override
	public Response updateDocument(Document document) {

		Document currentDocument = documents.getDocument(document.getId());
        if (currentDocument == null) {
        	documents.createDocument(document);
            return Response.status(Response.Status.CREATED).entity(document).build();
        }
        if (currentDocument.equals(document)) {
            return Response.notModified().build();
        }
        documents.updateDocument(document);
        return Response.status(Response.Status.NO_CONTENT).build();
		
	}

	@Override
	public Response deleteDocument(int id) {
		boolean result = documents.deleteDocument(id);
		if (!result) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.NO_CONTENT).build();
	}

	@Override
	public Response updateChapter(int id, Chapter chapter) {
		boolean result = documents.updateChapter(id, chapter);
        if (!result) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.NO_CONTENT).build();
	}

	
}
