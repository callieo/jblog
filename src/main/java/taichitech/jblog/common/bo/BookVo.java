package taichitech.jblog.common.bo;

import taichitech.common.db.Book;


public class BookVo {
	private Book book;
	

	public BookVo(Book book) {
		super();
		this.book = book;
	}

	public String toString() {
		return book.toString();
	}

	public String getAuthor() {
		return book.getAuthor();
	}

	public String getName() {
		return book.getName();
	}

}
