package taichitech.common.db;

public class Book {
	@Override
	public String toString() {
		return "Book [name=" + name + "]";
	}

	private String author;

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	private String name;

	public Book() {

	}

	public Book(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
