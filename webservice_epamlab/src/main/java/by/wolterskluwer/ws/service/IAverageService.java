package by.wolterskluwer.ws.service;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import by.wolterskluwer.ws.exceptions.WSWebserviceException;

@WebService
public interface IAverageService {
	@WebResult(name = "averageResult")
	double getAverageResult(@WebParam(name = "doubleArray") double[] arr) throws WSWebserviceException;
}
