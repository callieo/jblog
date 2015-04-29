package taichitech.jblog.common.bo;

import java.util.Iterator;

import org.apache.commons.lang3.ArrayUtils;

import taichitech.common.db.Shelf;

public class BookShelf implements Iterable<BookVo> {
	private BookVo[] bookVos;
	private Shelf shelf;

	public String getName() {
		return shelf.getName();
	}

	public int size() {
		return bookVos.length;
	}

	public void setShelf(Shelf shelf) {
		this.shelf = shelf;
	}

	BookShelf(BookVo[] bookVos) {
		this.bookVos = ArrayUtils.clone(bookVos);
		this.size = bookVos.length;
	}

	public BookVo findBook(String bookName) {
		for (Iterator<BookVo> it = iterator(); it.hasNext();) {
			BookVo bookVo = it.next();
			if (bookVo.getName().equals(bookName)) {
				return bookVo;
			}
		}
		return null;
	}

	private int size;

	private class BookVoIterator implements Iterator<BookVo> {

		private int index = -1;

		@Override
		public boolean hasNext() {
			return index < size - 1;
		}

		@Override
		public BookVo next() {
			index++;
			return bookVos[index];
		}

		@Override
		public void remove() {
			bookVos = ArrayUtils.remove(bookVos, index - 1);
			size = bookVos.length;
		}

	}

	@Override
	public Iterator<BookVo> iterator() {
		return new BookVoIterator();
	}

}
