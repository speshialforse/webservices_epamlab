package by.wolterskluwer.rs.model;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "document")
public class Document {
	
	private int id;
	private String name;
	private List<Chapter> chapters;
	
	public Document() {
	}	

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@XmlElementWrapper
    @XmlElement(name="chapter")
	public List<Chapter> getChapters() {
		return chapters;
	}
	public void setChapters(List<Chapter> chapters) {
		this.chapters = chapters;
	}

	public Chapter getChapter(int id) {
		Chapter chapter = null;
		chapter = chapters.stream().filter((c) -> c.getId() == id).findFirst().get();
		return chapter;
	}
	
	@Override
	public String toString() {
		
		return id + " ; " + name + " ; " + chapters;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((chapters == null) ? 0 : chapters.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Document other = (Document) obj;
		if (chapters == null) {
			if (other.chapters != null)
				return false;
		} else if (!chapters.equals(other.chapters))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}