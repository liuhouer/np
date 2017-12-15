/***************************************************************************/
/*                                                                         */
/* 			Copyright (c) TAIKANG ASSET MANAGEMENT CO., LTD.               */
/*                 泰康资产管理有限责任公司    版权所有		                   */
/*                                                                         */
/* PROPRIETARY RIGHTS of TAIKANG ASSET MANAGEMENT CO.,LTD. are involved in */
/* the subject matter of this material.  All manufacturing, reproduction,  */
/* use, and sales rights pertaining to this subject matter are governed by */
/* the license agreement.  The recipient of this software implicitly  	   */
/* accept the terms of the license.                                        */
/* 本软件文档资料是泰康资产管理有限责任公司的资产,任何人士阅读使用必须获得         */
/* 相应的书面授权,承担保密责任和接受相应的法律约束.                           */
/*                                                                         */
/***************************************************************************/
/************************************************************
  Copyright (C), TAIKANG ASSET MANAGEMENT. Co., Ltd.
  FileName: ConnThread.java
  Author: 张洋       Version : 1.0         Date:2017年11月8日
  Description:     // 模块描述      
  Version:         // 版本信息
  History:         // 历史修改记录
      <author>  <time>   <version >   <desc>
***********************************************************/
package cn.northpark.test.multhread;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Vector;

import oracle.jdbc.OracleTypes;

public class db  {
	
	//添加数据库连接池的写法
	private static final Vector<Connection> pool=new Vector<Connection>();
	private static final int MAX_SIZE=10;
	private static final int MIN_SIZE=3;
	private static Connection createConnection() {
		Connection conn = null;
		try{
		Class.forName("oracle.jdbc.driver.OracleDriver"); // 加载Oracle驱动程序
		String url = "jdbc:oracle:" + "thin:@10.88.147.6:1521:cmstest2";// 127.0.0.1是本机地址，XE是精简版Oracle的默认数据库名
		String user = "cms";// 用户名,系统默认的账户名
		String password = "cmsdb";// 你安装时选设置的密码
		conn = DriverManager.getConnection(url, user, password);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return conn;
	}
	
	
	static {
		for(int i=0;i<MIN_SIZE;i++){
			pool.add(createConnection());
		}
	}
	
	
	public static synchronized Connection getConnection() {
		Connection conn = null;
		if (pool.isEmpty()) {
			conn = createConnection();
		} else {
			int last_idx = pool.size() - 1;
			conn = (Connection) pool.get(last_idx);
			pool.remove(conn);
		}
		return conn;
	}
	public static synchronized void close(Connection conn){
		if(pool.size()<MAX_SIZE){
			pool.add(conn);
		}else{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}
	
	/**
	 * 描述：清空连接池
	 *
	 */
	public static synchronized void emptyPool(){
		for (int i = 0; i < pool.size(); i++) {
			try {
				pool.get(i).close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		pool.clear();
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
	public static String callProcedureYY(Connection conn,String funcName,int index) throws Exception {
		// 指定调用的存储过程
		CallableStatement cs = conn.prepareCall("{?=call "+funcName+"(?)}");
		// 设置参数
		cs.setInt(2, index);
		// 这里需要配置OUT的参数新型
		cs.registerOutParameter(1, OracleTypes.FLOAT);
		// 执行调用
		cs.execute();
		// 输入返回值
		String result = String.valueOf(cs.getFloat(1));
//		System.out.println(cs.getFloat(1));
		
		return result;
	}

	

	

	
	
	
//	/** 
//     * 插入或者更新数据 by bruce  
//     * @param sql
//     * @param a
//     * @param b
//     * @return 
//     */  
//    public static int saveOrUpdate(String sql,String a,String b){  
//        //String insert = "insert into t_department values('D004','金融部')";  
//        int re = 0;  
//        try{  
//            conn.setAutoCommit(false);//事物开始  
//              
//            ps = conn.prepareStatement(sql);
//            ps.setBigDecimal(1, BigDecimal.valueOf(Integer.valueOf(a)));
//            ps.setBigDecimal(2, BigDecimal.valueOf(Float.valueOf(b)));
//            ps.setBigDecimal(3, BigDecimal.valueOf(Integer.valueOf(a)));
//            ps.setBigDecimal(4, BigDecimal.valueOf(Float.valueOf(b)));
//           
//            System.out.println(sql);
//            System.out.println("a================"+a);
//            System.out.println("b================"+b);
//            re = ps.executeUpdate();  
//            if(re < 0){               //插入失败  
//                conn.rollback();      //回滚  
//                return re;  
//            }  
//            conn.commit();            //插入正常  
//            return re;  
//        }  
//        catch(Exception e){  
//            e.printStackTrace();  
//        }  
//        return 0;  
//          
//    } 
//    
//    
//    /**
//	 * 描述：
//	 * 关闭连接
//	 */
//	public static void closeConn(){
//		try {
//			// 逐一将上面的几个对象关闭，因为不关闭的话会影响性能、并且占用资源
//			// 注意关闭的顺序，最后使用的最先关闭
//			if (rs != null)
//				rs.close();
//			if (cs != null)
//				cs.close();
//			if (ps != null)
//				ps.close();
//			if (conn != null)
//				conn.close();
////			System.out.println("数据库连接已关闭！");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
    
}
