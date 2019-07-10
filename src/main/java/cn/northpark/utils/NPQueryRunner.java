
package cn.northpark.utils;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import lombok.extern.slf4j.Slf4j;

/**
 * NorthPark 使用JDBC+hikariCp-pool 来便捷的操作数据
 * @author w_zhangyang
 *
 */
@Slf4j
public class NPQueryRunner extends QueryRunner{


	@Override
	public <T> T query(String sql, ResultSetHandler<T> rsh) throws SQLException {
		// TODO Auto-generated method stub
		return super.query(sql, rsh);
	}


	
	public static void main(String[] args) throws SQLException {
		NPQueryRunner runner = new NPQueryRunner();
		List<Map<String, Object>> query = runner.query(JdbcUtils.getConnection(), "select * from bc_soft limit 0,50",  new MapListHandler());
		log.info("--->,{}",JsonUtil.object2json(query));
	}
}

