package by.wolterskluwer.ws.service;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;

import by.wolterskluwer.ws.exceptions.WSWebserviceException;

public class AverageResultImpl implements IAverageService {

	private static final String SERVICE_METHOD = "GetStatistics";
	private static final String X_ELEMENT = "X";
	private static final String DOUBLE_ELEMENT = "double";
	private static final String AVERAGE_ELEMENT = "Average";
	private static final String SUMS_ELEMENT = "Sums";
	private static final String NAMESPACE = "web";
	private static final String NAMESPACE_URI = "http://www.webserviceX.NET";
	private static final String SOAP_ENDPOINT_URL = "http://www.webservicex.net/Statistics.asmx";
	private static final String SOAP_ACTION = "http://www.webserviceX.NET/GetStatistics";
	private static final String ERROR_CONNECT = "Error occurred while sending SOAP Request to Server!\nMake sure you have the correct endpoint URL and SOAPAction!";
	private static final String ERROR_RESULT = "Error result!";
	
	@Override
	public double getAverageResult(double[] arr) throws WSWebserviceException {
		return callSoapWebService(SOAP_ENDPOINT_URL, SOAP_ACTION , arr);
	}

	private  void createSoapEnvelope(SOAPMessage soapMessage ,  double[] arr) throws SOAPException {
	    SOAPPart soapPart = soapMessage.getSOAPPart();
	    SOAPEnvelope envelope = soapPart.getEnvelope();
	    envelope.addNamespaceDeclaration(NAMESPACE, NAMESPACE_URI);
	    SOAPBody soapBody = envelope.getBody();   
	    SOAPElement soapBodyElemMethod = soapBody.addChildElement(SERVICE_METHOD , NAMESPACE);
	    SOAPElement soapBodyElem = soapBodyElemMethod.addChildElement(X_ELEMENT, NAMESPACE);   

	    for(Double d : arr){
	    	SOAPElement doubleElem = soapBodyElem.addChildElement(DOUBLE_ELEMENT, NAMESPACE);
	    	doubleElem.addTextNode(Double.toString(d));
	    }
	}

	private  double callSoapWebService(String soapEndpointUrl, String soapAction , double[] arr) throws WSWebserviceException {
		double result = 0.0;
		double sum = 0.0;
	    try {
	        SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
	        SOAPConnection soapConnection = soapConnectionFactory.createConnection();
	        SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(soapAction , arr), soapEndpointUrl);     
	        result = Double.parseDouble(soapResponse.getSOAPPart().getEnvelope().getElementsByTagName(AVERAGE_ELEMENT).item(0).getTextContent());
	        sum = Double.parseDouble(soapResponse.getSOAPPart().getEnvelope().getElementsByTagName(SUMS_ELEMENT).item(0).getTextContent());
	        soapConnection.close();
	        if (sum < 0) {
	        	throw new WSWebserviceException(ERROR_RESULT);
	        }
	    } catch (Exception e) {
	        throw new WSWebserviceException(ERROR_CONNECT);
	    }
	    return result;
	}

	private  SOAPMessage createSOAPRequest(String soapAction , double[] arr) throws Exception {
	    MessageFactory messageFactory = MessageFactory.newInstance();
	    SOAPMessage soapMessage = messageFactory.createMessage();
	    createSoapEnvelope(soapMessage , arr);
	    MimeHeaders headers = soapMessage.getMimeHeaders();
	    headers.addHeader("SOAPAction", soapAction);
	    soapMessage.saveChanges();
	    return soapMessage;
	}
}
