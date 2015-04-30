package taichitech.common.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ShelfDao extends BaseJdbcDao {
	public Shelf findShelf(String shelfId) {
		String sql = "select name  from shelf where id =?";
		Shelf shelf;
		try {
			shelf = this.getJdbcTemplate().queryForObject(sql,
					new Object[] { shelfId }, new int[] { Types.VARCHAR },
					new RowMapper<Shelf>() {

						@Override
						public Shelf mapRow(ResultSet rs, int rowNum)
								throws SQLException {
							Shelf shelf = new Shelf();
							shelf.setName(rs.getString("name"));
							return shelf;
						}
					});
			return shelf;
		} catch (DataAccessException e) {
			logger.error("查詢錯誤", e);
			return null;
		}

	}

}
