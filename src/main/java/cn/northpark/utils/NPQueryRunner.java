
package cn.northpark.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.dbutils.*;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * NorthPark 使用JDBC+hikariCp-pool 来便捷的操作数据
 * @author bruce
 *
 */
@Slf4j
public class NPQueryRunner {

	/** 唯一dateSource，保证全局只有一个数据库连接池 */
	public static DataSource dataSource = null;


	private static QueryRunner queryRunner;


	static{

		try {
			dataSource = HikariUtils.getDataSource();
			queryRunner = new QueryRunner(dataSource);
		}catch (Exception e) {
			log.error("获取连接异常 ", e);
		}
	}


	public static <T> T query(String sql ,ResultSetHandler<T> resultSetHandler,Object... params){
		T result = null;
		try {
			result = queryRunner.query(sql,resultSetHandler,params);
		}catch (Exception e){
			log.error("",e);
		}
		return result;
	}


	public static int update(String sql,Object... params){
		int result = 0;
		try {
			result = queryRunner.update(sql,params);
		}catch (Exception e){
			log.error("",e);
		}
		return result;
	}
	public static int insert(String sql,Object... params ){
		int result = 0;
		try {
			result = queryRunner.execute(sql,params);
		}catch (Exception e){
			log.error("",e);
		}
		return result;
	}
	public static Map<String,Object> findById(String table, int id){
		String sql = "select * from "+table +" where id = ?";
		return query(sql, new MapHandler(),id);
	}
	public static <T> T findById(String table , int id , BeanHandler<T> beanHandler){
		String sql = "select * from "+table +" where id = ?";
		return query(sql, beanHandler,id);
	}
	public static List<Map<String,Object>> findByCondition(String table, String condition){
		String sql = "select * from "+table +" where "+ condition;
		return query(sql, new MapListHandler());
	}

	public static <T> List<T> findByCondition(String table, String condition , BeanListHandler<T> beanListHandler ){
		String sql = "select * from "+table +" where "+ condition;
		return query(sql, beanListHandler);
	}

	public static List<Map<String,Object>> findByCondition(String table, String condition,String sort){
		String sql = "select * from "+table +" where "+ condition + "order by "+ sort;
		return query(sql, new MapListHandler());
	}
	public static List<Map<String,Object>> findByCondition(String table, String condition,String sort,String limit){
		String sql = "select * from "+table +" where "+ condition + "order by "+ sort + limit;
		return query(sql, new MapListHandler());
	}


	public static void close(){

	}

	
	public static void main(String[] args) throws SQLException {




//		List<Map<String, Object>> query = runner.query(JdbcUtils.getConnection(), "select * from bc_soft limit 0,50",  new MapListHandler());
		
		String insSql = "INSERT INTO `northpark`.`bc_knowledge_test`(`id`, `add_time1`, `add_time2`, `add_time3`) VALUES (00000521735, '2019-07-23 02:37:19', '2019-07-23', '2019-07-23 02:37:19');\r\n" +
				"";
		
		
		String insSql2 = "INSERT INTO `flink`.`t_word_counts`(`id`, `word`, `times`) VALUES (?, ?, ?);";


		NPQueryRunner.update(insSql2,IDUtils.getInstance().getUniqueSceneid(),IDUtils.getInstance().generateString(6),TimeUtils.nowTime());
	}
}

