package by.wolterskluwer.rs.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import by.wolterskluwer.rs.model.Chapter;
import by.wolterskluwer.rs.model.Document;

public class DocumentManagerMemoryDao {

	private Map<Integer , Document> documents;
	
	
	public DocumentManagerMemoryDao() {
		this.documents = new HashMap<Integer , Document>();
		
		Document document1 = new Document();
		document1.setId(1);
		document1.setName("Inheritance.");
		document1.setChapters(new ArrayList<Chapter>());

		Document document2 = new Document();
		document2.setId(2);
		document2.setName("Classes.");
		document2.setChapters(new ArrayList<Chapter>());

		documents.put(1, document1);
		documents.put(2, document2);
	}
	
	public Document getDocument(int id) {
		return documents.get(id);
	}
	
	public Chapter getChapter(int documentId , int chapterId) {
		Chapter chapter = documents.get(documentId).getChapter(chapterId);
		return chapter;
	}
	
	public void createDocument(Document document) {
		documents.put(document.getId(), document);
	}

	public boolean deleteDocument(int id) {
		Document document = documents.remove(id);
		return document == null ? false : true;
	}
	
	public void updateDocument(Document document) {
		documents.put(document.getId(), document);
	}
	
	public boolean updateChapter(int documentId , Chapter chapter) {
		Document document = documents.get(documentId);
		Chapter docChapter = document.getChapter(chapter.getId());
		if (docChapter == null) {
			return false;
		} else {
			docChapter.setChapteNumber(chapter.getChapteNumber());
			docChapter.setNumberOfPage(chapter.getNumberOfPage());
		    return true;
		}
	}
}
