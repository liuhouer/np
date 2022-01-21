package cn.northpark.utils.datasource;

import cn.northpark.threadLocal.CustomerContextHolder;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author bruce
 * @date 2021年10月29日 17:07:21
 * 动态数据源实现
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    protected Object determineCurrentLookupKey() {
        return CustomerContextHolder.getCustomerType();
    }

}
