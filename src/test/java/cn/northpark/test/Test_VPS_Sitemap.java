//package cn.northpark.test;
//
//import java.io.File;
//import java.util.List;
//import java.util.Map;
//
//import org.apache.commons.io.FileUtils;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import cn.northpark.manager.SoftManager;
//
///**
// * @author zhangyang
// * <p>
// * 定时爬取今日情圣文章
// */
//public class Test_VPS_Sitemap  extends BaseTest{
//
//    //
//    @Autowired
//    public SoftManager softManager;
//
//
//    public void runTask(Integer lastNum) {
//    	
//    	//=========================================================新url的sitemap===========================================================================================
//
//    	
//		try {
//			 //添加新url的sitemap
//			StringBuilder sb = new StringBuilder();
//			List<Map<String, Object>> list = softManager.querySqlMap(" select id from bc_vps where id >"+ lastNum+ " order by id desc ");
//			for(Map<String, Object> map :list){
//				Object retcode = map.get("id");
//				sb.append("<url>");
//				sb.append("<loc>https://northpark.cn/vps/post-");
//				sb.append(retcode+".html</loc>");
//				sb.append("</url>");
//			}
//			System.out.println(sb.toString());
//			FileUtils.writeStringToFile(new File("d:\\vps.xml"), sb.toString());
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//
//
//
//    }
//
//
//    //测试多页
//
//
//    @Test
//    public void save() {
//        runTask(3198);
//    }
//
//}
