package by.wolterskluwer.rs.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "chapter")
public class Chapter {
	
	private int id;
	private int chapterNumber;
	private int numberOfPages;
	
	public Chapter() {
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getChapteNumber() {
		return chapterNumber;
	}
	
	public void setChapteNumber(int chapteNumber) {
		this.chapterNumber = chapteNumber;
	}
	
	public int getNumberOfPage() {
		return numberOfPages;
	}
	
	public void setNumberOfPage(int numberOfPage) {
		this.numberOfPages = numberOfPage;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + chapterNumber;
		result = prime * result + id;
		result = prime * result + numberOfPages;
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
		Chapter other = (Chapter) obj;
		if (chapterNumber != other.chapterNumber)
			return false;
		if (id != other.id)
			return false;
		if (numberOfPages != other.numberOfPages)
			return false;
		return true;
	}

}
