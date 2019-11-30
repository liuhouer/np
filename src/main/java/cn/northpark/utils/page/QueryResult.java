package cn.northpark.utils.page;

import java.util.List;

public class QueryResult<T> {

    public QueryResult() {

    }

    private List<T> resultlist;

    private Long totalrecord;

    public List<T> getResultlist() {
        return resultlist;
    }

    public void setResultlist(List<T> resultlist) {
        this.resultlist = resultlist;
    }

    public Long getTotalrecord() {
        return totalrecord;
    }

    public void setTotalrecord(Long totalrecord) {
        this.totalrecord = totalrecord;
    }

}
