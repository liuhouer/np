package cn.northpark.utils.page;

import java.util.List;

public class QueryResult<T> {

    public QueryResult() {

    }

    private List<T> resultList;

    private Long totalRecord;

    public List<T> getResultList() {
        return resultList;
    }

    public void setResultList(List<T> resultList) {
        this.resultList = resultList;
    }

    public Long getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(Long totalRecord) {
        this.totalRecord = totalRecord;
    }

}
