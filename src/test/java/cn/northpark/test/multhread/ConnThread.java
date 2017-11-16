package cn.northpark.test.multhread;

import java.util.concurrent.CountDownLatch;

public class ConnThread implements Runnable {

	public  int threadNo;
	public  CountDownLatch latch;

	//设置线程的编号
	public ConnThread(int threadNo,CountDownLatch latch){
		this.threadNo = threadNo;
		this.latch = latch;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		// 建立连接，并且请求存储过程

		try {
			db.getConn();

			// 每个线程查询三次
			//调用存储过程 
			for (int i = 0; i < 3; i++) {

				String result = db.callProcedureYY(db.conn,"cms.sp_get_fundasset",threadNo + 500*i);

				test.map.put(String.valueOf(threadNo + 500*i), result);
			}
			//存放结果



		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// db.closeConn();
			latch.countDown();
			System.out.println("线程"+threadNo+"执行完毕");
		}

	}





	public static void main(String[] args) {
		try {
			db.getConn();
			String result = db.callProcedureYY(db.conn,"cms.sp_get_fundasset",1);
			System.out.println("result--->"+result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.closeConn();
		}
	}
}