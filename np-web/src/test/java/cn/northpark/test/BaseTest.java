package cn.northpark.test;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author bruce
 * 单元测试基类
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-dao.xml", "classpath:application-service.xml", "classpath:application-transaction.xml"})
public class BaseTest {

}

