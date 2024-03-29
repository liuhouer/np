//package cn.northpark.test;
//
//import cn.northpark.manager.SoftManager;
//import cn.northpark.model.Soft;
//import cn.northpark.utils.HTMLParserUtil;
//import cn.northpark.utils.IDUtils;
//import cn.northpark.utils.TimeUtils;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.util.CollectionUtils;
//
//import java.util.List;
//import java.util.Map;
//
///**
// * @author zhangyang
// * <p>
// * 定时爬取今日情圣文章
// */
//@Slf4j
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath:application-dao.xml", "classpath:application-service.xml",
//        "classpath:application-transaction.xml"})
//public class TestRetSql {
//
//    @Autowired
//    public SoftManager softManager;
//
//    public void runTask() {
//
//
//        // =========================================================软件===========================================================================================
//        try {
//
//            log.info("soft task==============start=" + TimeUtils.getNowTime());
//            Map<String, String> map = null;
//
//            for (int k = 1; k <= 1; k++) {
//
//                try {
//
//
//                    List<Map<String, String>> list = HTMLParserUtil.retSoftChaoxzCom(k);
//
//                    if (!CollectionUtils.isEmpty(list)) {
//                        for (int i = 0; i < list.size(); i++) {
//                            map = list.get(i);
//
//                            String title = map.get("title");
//                            String a_url = map.get("a_url");
//                            String brief = map.get("brief");
//                            String date = map.get("date");
//                            String article = map.get("article");
//                            String tag = map.get("tag");
//                            String code = map.get("code") + "-" + IDUtils.getInstance().generateNumberString(3);
//                            String os = map.get("os");
//                            String month = map.get("month");
//                            String year = map.get("year");
//                            String tag_code = map.get("tag_code");
//                            String path = map.get("path");
//
//                            // 是不存在的文章
//                            int flag = softManager.countHql(" where o.title= '" + title + "' or o.ret_code = '" + code + "' ");
//
//                            if (flag <= 0) {
//
//                                Soft model = Soft.builder().brief(brief).content(article).os(os).post_date(date)
//                                        .ret_code(code).ret_url(a_url).tags(tag).title(title).month(month).year(year)
//                                        .tags_code(tag_code).path(path).build();
//                                softManager.addSoft(model);
//                            }
//                        }
//                    }
//                } catch (Exception e) {
//
//                    continue;
//                }
//
//                try {
//                    Thread.sleep(1000);
//                    log.info("第" + k + "页================");
//                } catch (InterruptedException e) {
//                    // TODO Auo-generated catch block
//                    e.printStackTrace();
//                }
//
//            }
//
//        } catch (Exception e) {
//
//        }
//
//        log.info("soft task==============end=" + TimeUtils.getNowTime());
//
//        log.info("软件定时任务结束" + TimeUtils.getNowTime());
//
//        // =========================================================软件===========================================================================================
//
//
//    }
//
//    // 测试多页
//
//    @Test
//    public void save() {
//        runTask();
//    }
//
//}
