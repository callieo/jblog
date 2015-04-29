package taichitech.jblog.dp;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import taichitech.common.db.Book;
import taichitech.jblog.common.bo.BookShelf;
import taichitech.jblog.common.bo.BookVo;
import taichitech.jblog.common.bo.ShelfFactory;

public class RunDp {
	Logger logger = LoggerFactory.getLogger(getClass());
	ApplicationContext context;

	public RunDp() {
		try {
			context = new ClassPathXmlApplicationContext(new String[] {
					"applicationContext.xml", "applicationContext-ap.xml" });
		} catch (Exception e) {
			logger.error("Init Spring fail~", e);
		}
	}

	public BookVo borrowBook(String bookName) {
		ShelfFactory boFactory = context.getBean(ShelfFactory.class);
		BookShelf bookShelf = boFactory.getBookShelf("POMO");
		for (BookVo book : bookShelf) {
			if(book.getName().equals(bookName)){
				return book;
			}
		}
		return null;
	}
	

	public void testIterator() {
		int size = 10;
		Book[] books = new Book[size];
		for (int i = 0; i < 10; i++) {
			String bookName = RandomStringUtils.randomAlphabetic(10);
			books[i] = new Book(bookName.toUpperCase());
		}
	}

	public static void main(String[] args) {
		RunDp rd = new RunDp();
		rd.borrowBook("美麗世界");

	}

}
