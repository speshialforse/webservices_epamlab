package by.wolterskluwer.rs.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class ExceptionHandler implements ExceptionMapper<WebServiceException>{

	@Override
	public Response toResponse(WebServiceException e) {
		return Response.serverError().entity(e.getMessage()).build();
	}
}
