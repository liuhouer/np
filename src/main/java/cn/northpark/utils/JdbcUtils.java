package cn.northpark.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;


/**
 * @author w_zhangyang
 *	Hikaricp数据库连接池工具类的设计
 */
public class JdbcUtils {
	
	private JdbcUtils() {
		
	}

	 // 定义HikariDataSource类型的dataSource 
    // 注意： 因为HikariDataSource类 实现了DataSource 接口。 因此 dataSource 即是HikariDataSource类型也是DataSource类型
	public  static HikariDataSource dataSource;
	
	
	/**
     * 获取数据源
     * @return
     * @throws SQLException
     * @throws IOException
     */
    public static DataSource getDataSource()  {
 
        try {
            // 因为dataSource是全局变量、默认初始化值为null 
            if (dataSource == null){
                // 通过字节输入流 读取 配置文件  hikaricp.properties
                InputStream is = JdbcUtils.class.getClassLoader().getResourceAsStream("hikaricp.properties");
                // 因为HikariConfig类不可以加载io，但是可以加载Properties。因此：将输入流is封装到props
                Properties props = new Properties();
                props.load(is);
                // 再将封装好的props 传入到HikariConfig 类中，得到 config对象
                HikariConfig config = new HikariConfig(props);
                // 将config对象传入给HikariDataSource ，返回dataSource
                
    			dataSource = new HikariDataSource(config);
            }
            // 返回dataSource
            return  dataSource;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
 
    }
 
    /**
     * 通过数据源获取连接
     * @return
     * @throws SQLException
     */
    public static  Connection getConnection() throws SQLException {
        return getDataSource().getConnection();
    }
    
    
}

