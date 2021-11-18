package cn.northpark.utils.page;

import java.util.List;

public class QueryResult<T> {

    public QueryResult() {

    }

    private List<T> result_list;

    private Long totalrecord;

    public List<T> getResultlist() {
        return result_list;
    }

    public void setResultlist(List<T> result_list) {
        this.result_list = result_list;
    }

    public Long getTotalrecord() {
        return totalrecord;
    }

    public void setTotalrecord(Long totalrecord) {
        this.totalrecord = totalrecord;
    }

}
