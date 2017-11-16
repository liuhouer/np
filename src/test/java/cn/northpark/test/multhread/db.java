package cn.northpark.test.multhread;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import oracle.jdbc.driver.OracleTypes;

public class db  {
	public static Connection conn = null; // 数据库连接对象
	public static CallableStatement cs = null;// 存储过程执行对象
	public static ResultSet rs = null;// 结果集对象

	public static  synchronized void getConn() throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver"); // 加载Oracle驱动程序
		// System.out.println("开始尝试连接数据库！");
		String url = "jdbc:oracle:" + "thin:@localhost:1521:test";// 127.0.0.1是本机地址，XE是精简版Oracle的默认数据库名
		String user = "test";// 用户名,系统默认的账户名
		String password = "test";// 你安装时选设置的密码
		conn = DriverManager.getConnection(url, user, password);// 获取连接
		// System.out.println("连接成功！");
	}




	/**
	 *
	 * @Discription 执行有参数，有返回值的存储过程
	 * @return void
	 * @param conn
	 * @throws Exception
	 */
	/*
	 * 对应的存储过程语句 --有参数，有返回值 create or replace procedure deleteLine(byNo in
	 * number,getCount out number) as begin delete from emp e where e.empno =
	 * byNo; select count(*) into getCount from emp e; end;
	 */
	public static synchronized String callProcedureYY(Connection conn,String funcName,int index) throws Exception {
		// 指定调用的存储过程
		cs = conn.prepareCall("{?=call "+funcName+"(?)}");
		// 设置参数
		cs.setInt(2, index);
		// 这里需要配置OUT的参数新型
		cs.registerOutParameter(1, OracleTypes.FLOAT);
		// 执行调用
		cs.execute();
		// 输入返回值
		String result = String.valueOf(cs.getFloat(1));
		// System.out.println(cs.getFloat(1));

		return result;
	}






	/**
	 * 描述：
	 * 关闭连接
	 */
	public static void closeConn(){
		try {
			// 逐一将上面的几个对象关闭，因为不关闭的话会影响性能、并且占用资源
			// 注意关闭的顺序，最后使用的最先关闭
			if (rs != null)
				rs.close();
			if (cs != null)
				cs.close();
			if (conn != null)
				conn.close();
			// System.out.println("数据库连接已关闭！");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	//=========================================未使用的===========================================================================================
	/**
	 *
	 * @Discription 执行有参数，无返回值的存储过程
	 * @return void
	 * @param conn
	 * @throws Exception
	 */
	/*
	 * 对应的存储过程语句 --有参数无返回值 create or replace procedure updateName(byNo in
	 * number,useName in varchar2) as begin update emp e set e.ename = useName
	 * where e.empno = byNo; end;
	 */
	public static void callProcedureY(Connection conn) throws Exception {
		// 指定调用的存储过程
		cs = conn.prepareCall("{call updateName(?,?)}");
		cs.setInt(1, 7499);// 设置存储过程对应的输入参数
		cs.setString(2, "www");// 对应下标从1 开始
		// 执行存储过程调用
		cs.execute();
	}

	/**
	 *
	 * @Discription 执行无参数，无返回值的存储过程
	 * @return void
	 * @param conn
	 * @throws Exception
	 */
	/*
	 * 对应的存储过程语句 --无参数 create or replace procedure insertLine as begin insert
	 * into emp
	 * values(7333,'ALLEN','SAL',7698,to_date('2011/11/11','yyyy-MM-dd'),1600,
	 * 300,30); end;
	 */
	public static void callProcedure(Connection conn) throws Exception {
		// 指定调用的存储过程
		cs = conn.prepareCall("{call insertLine}");
		// 执行存储过程的调用
		cs.execute();
	}

	/**
	 *
	 * @Discription 执行有参数，有返回值的存储过程
	 * @return void
	 * @param conn
	 * @throws Exception
	 */
	/*
	 * 对应的存储过程语句 --有参数，有返回值 create or replace procedure deleteLine(byNo in
	 * number,getCount out number) as begin delete from emp e where e.empno =
	 * byNo; select count(*) into getCount from emp e; end;
	 */
	public static void callProcedureYY(Connection conn) throws Exception {
		// 指定调用的存储过程
		cs = conn.prepareCall("{call deleteLine(?,?)}");
		// 设置参数
		cs.setInt(1, 7839);
		// 这里需要配置OUT的参数新型
		cs.registerOutParameter(2, OracleTypes.NUMBER);
		// 执行调用
		cs.execute();
		// 输入返回值
		System.out.println(cs.getString(2));
	}


	/**
	 *
	 * @Discription 执行有参数的函数
	 * @return void
	 * @param conn
	 * @throws Exception
	 */
	/*
	 * 对应的存储过程语句 --创建函数，有参数 create or replace function useOther(byNo in number)
	 * return String as returnValue char(10); begin select count(*) into
	 * returnValue from emp e where e.empno > byNo; return returnValue; end;
	 */
	public static void callProcedureFY(Connection conn) throws Exception {
		// 指定调用的函数
		cs = conn.prepareCall("{? = call useOther(?)}");
		// 配置OUT参数信息
		cs.registerOutParameter(1, OracleTypes.CHAR);
		// 配置输入参数
		cs.setInt(2, 1111);
		// 执行过程调用
		cs.execute();
		// 输入返回值
		System.out.println(cs.getString(1));
	}



	/**
	 *
	 * @Discription 执行有参数，返回集合的存储过程
	 * @return void
	 * @param conn
	 * @throws Exception
	 */
	/*
	 * 对应的存储过程语句 --有参数返回一个列表，使用package create or replace package someUtils as
	 * type cur_ref is ref cursor; procedure selectRows(cur_ref out
	 * someUtils.cur_ref); end someUtils;
	 * 
	 * create or replace package body someUtils as procedure selectRows(cur_ref
	 * out someUtils.cur_ref) as begin open cur_ref for select * from emp e; end
	 * selectRows; end someUtils;
	 */
	public static void callProcedureYYL(Connection conn) throws Exception {
		// 执行调用的存储过程
		cs = conn.prepareCall("{call someUtils.selectRows(?)}");
		// 设置返回参数
		cs.registerOutParameter(1, OracleTypes.CURSOR);
		// 执行调用
		cs.execute();
		// 获取结果集 结果集是一个Object类型，需要进行强制转换 rs = (ResultSet)
		rs = (ResultSet) cs.getObject(1);
		// 输出返回值
		while (rs.next()) {
			System.out.println(rs.getInt(1) + "\t" + rs.getString(2));
		}
	}

}
