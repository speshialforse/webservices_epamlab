package by.wolterscluwer.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.entity.StringEntity;
import org.codehaus.jackson.map.ObjectMapper;
import org.apache.http.impl.client.HttpClientBuilder;
import by.wolterscluwer.beans.Chapter;
import by.wolterscluwer.beans.Document;


    
public class ClientRest {

	
	private static final String URL = "http://localhost:8080/webservices_epamlab/service/documentservice";
    private final HttpClient client= HttpClientBuilder.create().build();
    private static final String CONTENT_XML = "application/xml";

    private static final int HTTP_CODE_CREATED = 201;
    private static final int HTTP_CODE_NOT_FOUND = 404;

	
	public static void main(String[] args) {

		
		ClientRest client; 
		try {
		client = new ClientRest();
        Document doc1 = new Document();
		doc1.setId(5);
		doc1.setName("Java core");
		doc1.setChapters(new ArrayList<Chapter>());
		
		List<Chapter> chapters = new ArrayList<Chapter>();
		Chapter chapter = new Chapter();
		chapter.setId(1);
		chapter.setChapteNumber(2);
		chapter.setNumberOfPage(3);
		chapters.add(chapter);
		doc1.setChapters(chapters);
		
		Document doc2 = new Document();
		doc2.setId(9);
		doc2.setName("Servlets.");
		doc2.setChapters(new ArrayList<Chapter>());
		
		client.createDocument(doc1);
		client.createDocument(doc2);

		client.getDocument(5);
		client.getDocument(9);
				
		doc2.setName("Thinking of java.");
		
		client.updateDocument(doc2);
		client.getDocument(9);
		
		client.deleteDocument(9);
		
		chapter.setNumberOfPage(999);
		client.updateChapter(1, chapter);
        } catch (Exception e) {
            e.printStackTrace(); //To change body of catch statement use File | Settings | File Templates.
        }
	}
	public void getDocument(int id) {
        String url = URL + "/document/" + id;
        HttpGet request = new HttpGet(url);
        try {
            HttpResponse response = client.execute(request);
            System.out.println("out method GET getDocument() : " + url);
            System.out.println("HTTP code : " + response.getStatusLine().getStatusCode());
            if (response.getStatusLine().getStatusCode() == HTTP_CODE_NOT_FOUND) {
              System.out.println("Document not found!");
            }
            String json = readFromStream(response.getEntity().getContent());
            ObjectMapper mapper = new ObjectMapper();
            System.out.println(mapper.readValue(json, Document.class));
        } catch (IOException e) {
        	System.err.println("System error!");
        }
    }


    public void createDocument(Document document) throws UnsupportedEncodingException {
        String url = URL + "/document";
        HttpPost request = new HttpPost(url);
        StringEntity requestData = new StringEntity(convertToXml(document), StandardCharsets.UTF_8);
        requestData.setContentType(CONTENT_XML);
        request.setEntity(requestData);
        try {
            HttpResponse response = client.execute(request);
            System.out.println("out method POST createDocument() : " + url);
            System.out.println("HTTP code : " + response.getStatusLine().getStatusCode());
            String json = readFromStream(response.getEntity().getContent());
            ObjectMapper mapper = new ObjectMapper();
            System.out.println(mapper.readValue(json, Document.class));
        } catch (IOException e) {
        	System.err.println("System error!");
        }
    }

    public void updateDocument( Document document) throws UnsupportedEncodingException {
        String url = URL + "/document";
        HttpPut request = new HttpPut(url);
        StringEntity requestData = new StringEntity(convertToXml(document), StandardCharsets.UTF_8);
        requestData.setContentType(CONTENT_XML);
        request.setEntity(requestData);
        try {
            HttpResponse response = client.execute(request);
            System.out.println("out method PUT updateDocument() : " + url);
            System.out.println("HTTP code : " + response.getStatusLine().getStatusCode());
            if (response.getStatusLine().getStatusCode() == HTTP_CODE_CREATED) {
                String json = readFromStream(response.getEntity().getContent());
                ObjectMapper mapper = new ObjectMapper();
                System.out.println(mapper.readValue(json, Document.class));
            }
        } catch (IOException e) {
        	System.err.println("System error!");
        }
    }


    public void updateChapter(int id,  Chapter chapter) throws UnsupportedEncodingException {
        String url = URL + "/document/" + id + "/chapter";
        HttpPut request = new HttpPut(url);
        StringEntity requestData = new StringEntity(convertToXml(chapter), StandardCharsets.UTF_8);
        requestData.setContentType(CONTENT_XML);
        request.setEntity(requestData);
        try {
            HttpResponse response = client.execute(request);
            System.out.println("out method PUT updateChapter() : " + url);
            System.out.println("HTTP code : " + response.getStatusLine().getStatusCode());
            if (response.getStatusLine().getStatusCode() == HTTP_CODE_CREATED) {
                String json = readFromStream(response.getEntity().getContent());
                ObjectMapper mapper = new ObjectMapper();
                System.out.println(mapper.readValue(json, Document.class));
            }
        } catch (IOException e) {
        	System.err.println("System error!");
        }
    }


    public void deleteDocument(int id) {
        String url = URL + "/document/deleteDocument/" + id;
        HttpDelete request = new HttpDelete(url);
        try {
            HttpResponse response = client.execute(request);
            System.out.println("out method DELETE deleteDocument() : " + url);
            System.out.println("HTTP code : " + response.getStatusLine().getStatusCode());
        } catch (IOException e) {
        	System.err.println("System error!");
        }
    }

    private static String readFromStream(InputStream stream) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
        } catch (IOException e) {
        	System.err.println("System error!");
        }
        return builder.toString();
    }

    private static <T> String convertToXml(T object) {
        StringWriter xmlString = new StringWriter();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(object.getClass());
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(object, xmlString);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return xmlString.toString();
    }

}