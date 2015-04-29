package taichitech.jblog.common.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import taichitech.common.db.Book;
import taichitech.common.db.BookDao;
import taichitech.common.db.Shelf;
import taichitech.common.db.ShelfDao;

@Service
public class ShelfFactory {
	@Autowired
	private BookDao bookDao;
	@Autowired
	private ShelfDao shelfDao;

	public BookShelf getBookShelf(String shelfId) {
		List<Book> books = bookDao.findBookBy(shelfId);
		BookVo[] bookVos = new BookVo[books.size()];
		for (int i = 0; i < bookVos.length; i++) {
			bookVos[i] = new BookVo(books.get(i));
		}
		BookShelf bookShelf = new BookShelf(bookVos);
		Shelf shelf = shelfDao.findShelf(shelfId);
		bookShelf.setShelf(shelf);
		return bookShelf;
	}

}
