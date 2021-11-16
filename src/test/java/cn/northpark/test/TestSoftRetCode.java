//package cn.northpark.test;
//
//import java.util.List;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.util.CollectionUtils;
//
//import cn.northpark.manager.SoftManager;
//import cn.northpark.model.Soft;
//import cn.northpark.utils.IDUtils;
//
///**
// * @author zhangyang
// *         <p>
// *         处理软件中的文章代码重复的问题
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = { "classpath:application-dao.xml", "classpath:application-service.xml",
//		"classpath:application-transaction.xml"})
//public class TestSoftret_code {
//
//	@Autowired
//	public SoftManager softManager;
//
//	public void runTask() {
//
//
//		 List<Soft> list = softManager.querySql("select * from bc_soft where ret_code in(select  ret_code from bc_soft group by ret_code having count(ret_code)>1 )");
//		 
//		 if(!CollectionUtils.isEmpty(list)) {
//			 for (Soft soft : list) {
//				soft.setret_code(soft.getret_code()+"-"+IDUtils.getInstance().generateNumberString(3));
//				softManager.updateSoft(soft);
//			}
//		 }
//
//		// =========================================================软件===========================================================================================
//
//
//	}
//
//	// 测试多页
//
//	@Test
//	public void save() {
//		runTask();
//	}
//
//}
