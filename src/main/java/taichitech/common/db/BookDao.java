package taichitech.common.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class BookDao extends BaseJdbcDao {
	public List<Book> findAllBook() {
		String sql = "select id, name, author from book ";
		List<Book> list = this.getJdbcTemplate().query(sql,
				new RowMapper<Book>() {
					@Override
					public Book mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Book book = new Book(rs.getString("name"));
						return book;
					}
				});
		return list;
	}

	public List<Book> findBookBy(String shelfId) {
		String sql = "select id, name, author from book, book_shelf_rel rel "
				+ "where rel.book_id =book.id and rel.shelf_id =? ";
		List<Book> list = this.getJdbcTemplate().query(sql,
				new Object[] { shelfId }, new int[] { Types.VARCHAR },
				new RowMapper<Book>() {
					@Override
					public Book mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Book book = new Book(rs.getString("name"));
						book.setAuthor(rs.getString("author"));
						return book;
					}
				});
		return list;
	}
}
