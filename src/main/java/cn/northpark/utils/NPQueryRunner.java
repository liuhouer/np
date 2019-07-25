
package cn.northpark.utils;

import java.sql.SQLException;
import java.util.Date;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

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
//		List<Map<String, Object>> query = runner.query(JdbcUtils.getConnection(), "select * from bc_soft limit 0,50",  new MapListHandler());
		
		String insSql = "INSERT INTO `northpark`.`bc_knowledge_test`(`id`, `addtime1`, `addtime2`, `addtime3`) VALUES (00000521735, '2019-07-23 02:37:19', '2019-07-23', '2019-07-23 02:37:19');\r\n" + 
				"";
		
		
		String insSql2 = "INSERT INTO `northpark`.`bc_knowledge_test`(`id`, `addtime1`, `addtime2`, `addtime3`) VALUES (00000521736, ?, ?, ?)";
		
		
		runner.update(JdbcUtils.getConnection(),insSql2,new Date(),new Date(),new Date());
	}
}

