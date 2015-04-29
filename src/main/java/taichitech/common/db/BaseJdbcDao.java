package taichitech.common.db;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Component;
@Component
public class BaseJdbcDao extends JdbcDaoSupport {
	@Autowired
	@Qualifier("dataSource")
	public void setJdbcDataSource(DataSource dataSource){
		setDataSource(dataSource);
	}
	protected Logger logger = LoggerFactory.getLogger(getClass());

}
