package cn.northpark.utils.page;

import java.util.List;
import java.util.Map;

public class PageView<T> {
//  用一个PageView封装了分页数据（从QueryResult里List拿出来的），
//	页码开始索引和结束索引（是一个封装了开始索引和结束索引的类PageIndex，这两个字段就像是百度搜索下端的页码开始和结束索引），
//	总页数totalPage（通过总记录数totalRecord和每页最大记录数maxResult计得），
//	每页记录数maxResult（使用默认值），
//	当前页码（和maxResult触发记录开始索引生成，Hibernate分页setFirstResult(...)参数），
//	总记录数totalRecord（从QueryResult的记录拿出来），
//	页码数量pageShowCount（比如第一页、上一页、下一页、末页的数量）：

    /**
     * 分页数据
     **/
    private List<T> records;

    /**
     * Map结构分页数据
     **/
    private List<Map<String, Object>> mapRecords;


    /**
     * 页码开始索引和结束索引
     **/
    private PageIndex pageIndex;

    /**
     * 总页数
     **/
    private int totalPage = 1;
    /**
     * 每页显示记录数
     **/
    private int maxResult = MyConstant.MAX_RESULT;
    /**
     * 当前页
     **/
    private int currentPage = 1;
    /**
     * 总记录数
     **/
    private int totalRecord;
    /**
     * 每次展示的页码数量比如12345  34567
     **/
    private int pageShowCount = MyConstant.PAGE_SHOW_COUNT;


    //構造函數==========================================
    public PageView() {
    }

    /**
     * 構造函數===========當前頁碼，每頁顯示記錄數============================
     *
     * @param currentPage
     * @param maxResult
     */
    public PageView(int currentPage, int maxResult) {
        this.maxResult = maxResult;
        //兼容错误
        if (currentPage <= 0) {
            currentPage = 1;
        }
        this.currentPage = currentPage;
    }

    /**
     * 構造函數===========當前頁碼，每頁顯示記錄數============================
     *
     * @param currentPage
     * @param maxResult
     * @param pageShowCount 限制前台展示的页码数量比如12345 : 5  345678:6
     */
    public PageView(int currentPage, int maxResult, int pageShowCount) {
        this.maxResult = maxResult;
        //兼容错误
        if (currentPage <= 0) {
            currentPage = 1;
        }
        this.currentPage = currentPage;
        this.pageShowCount = pageShowCount;
    }


    /**
     * 計算startIndex
     *
     * @return
     */
    public int getFirstResult() {
        return (this.currentPage - 1) * this.maxResult;
    }


    //总页数设置-----------------------------
    public int getPageShowCount() {
        return pageShowCount;
    }

    public void setPageShowCount(int pageShowCount) {
        this.pageShowCount = pageShowCount;
    }


    /**
     * 设置总条数
     * 设置结果集
     *
     * @param qr
     */
    public void setQueryResult(QueryResult<T> qr) {
        setTotalRecord(qr.getTotalRecord().intValue());
        setRecords(qr.getResultList());
    }

    public int getTotalRecord() {
        return totalRecord;
    }

    /**
     * 设置总条数+总页数
     *
     * @param totalRecord
     */
    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
        setTotalPage(this.totalRecord % this.maxResult == 0 ? this.totalRecord / this.maxResult : this.totalRecord / this.maxResult + 1);
    }


    public List<T> getRecords() {
        return records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }


    //=================================


    /**
     * 设置总页数和pageIndex
     *
     * @param totalPage
     */
    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
        this.pageIndex = PageIndex.getPageIndex(this.pageShowCount, currentPage, totalPage);
    }

    public PageIndex getPageIndex() {
        return pageIndex;
    }


    /**
     * 获取总页数
     *
     * @return
     */
    public int getTotalPage() {
        return totalPage;
    }

    public int getMaxResult() {
        return maxResult;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public List<Map<String, Object>> getMapRecords() {
        return mapRecords;
    }

    public void setMapRecords(List<Map<String, Object>> mapRecords) {
        this.mapRecords = mapRecords;
    }
    
    


}
