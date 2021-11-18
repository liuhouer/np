package cn.northpark.utils.page;

import java.util.List;
import java.util.Map;

public class PageView<T> {
//  用一个PageView封装了分页数据（从QueryResult里List拿出来的），
//	页码开始索引和结束索引（是一个封装了开始索引和结束索引的类PageIndex，这两个字段就像是百度搜索下端的页码开始和结束索引），
//	总页数totalpage（通过总记录数totalrecord和每页最大记录数maxresult计得），
//	每页记录数maxresult（使用默认值），
//	当前页码（和maxresult触发记录开始索引生成，Hibernate分页setFirstResult(...)参数），
//	总记录数totalrecord（从QueryResult的记录拿出来），
//	页码数量pagecode（比如第一页、上一页、下一页、末页的数量）：


    /**
     * 分页数据
     **/
    private List<T> records;

    /**
     * Map结构分页数据
     **/
    private List<Map<String, Object>> maprecords;


    /**
     * 页码开始索引和结束索引
     **/
    private PageIndex pageindex;

    /**
     * 总页数
     **/
    private int totalpage = 1;
    /**
     * 每页显示记录数
     **/
    private int maxresult = MyConstant.MAXRESULT;
    /**
     * 当前页
     **/
    private int currentPage = 1;
    /**
     * 总记录数
     **/
    private int totalrecord;
    /**
     * 每次展示的页码数量比如12345  34567
     **/
    private int pagecode = MyConstant.PAGECODE;


    //構造函數==========================================
    public PageView() {
    }

    /**
     * 構造函數===========當前頁碼，每頁顯示記錄數============================
     *
     * @param currentPage
     * @param maxresult
     */
    public PageView(int currentPage, int maxresult) {
        this.maxresult = maxresult;
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
     * @param maxresult
     * @param pagecode 限制前台展示的页码数量比如12345 : 5  345678:6
     */
    public PageView(int currentPage, int maxresult, int pagecode) {
        this.maxresult = maxresult;
        //兼容错误
        if (currentPage <= 0) {
            currentPage = 1;
        }
        this.currentPage = currentPage;
        this.pagecode = pagecode;
    }


    /**
     * 計算startindex
     *
     * @return
     */
    public int getFirstResult() {
        return (this.currentPage - 1) * this.maxresult;
    }


    //总页数设置-----------------------------
    public int getPagecode() {
        return pagecode;
    }

    public void setPagecode(int pagecode) {
        this.pagecode = pagecode;
    }


    /**
     * 设置总条数
     * 设置结果集
     *
     * @param qr
     */
    public void setQueryResult(QueryResult<T> qr) {
        setTotalrecord(qr.getTotalrecord().intValue());
        setRecords(qr.getResultlist());
    }

    public int getTotalrecord() {
        return totalrecord;
    }

    /**
     * 设置总条数+总页数
     *
     * @param totalrecord
     */
    public void setTotalrecord(int totalrecord) {
        this.totalrecord = totalrecord;
        setTotalpage(this.totalrecord % this.maxresult == 0 ? this.totalrecord / this.maxresult : this.totalrecord / this.maxresult + 1);
    }


    public List<T> getRecords() {
        return records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }


    //=================================


    /**
     * 设置总页数和pageindex
     *
     * @param totalpage
     */
    public void setTotalpage(int totalpage) {
        this.totalpage = totalpage;
        this.pageindex = PageIndex.getPageIndex(this.pagecode, currentPage, totalpage);
    }

    public PageIndex getPageindex() {
        return pageindex;
    }


    /**
     * 获取总页数
     *
     * @return
     */
    public int getTotalpage() {
        return totalpage;
    }

    public int getMaxresult() {
        return maxresult;
    }

    public int getCurrentpage() {
        return currentPage;
    }

    public void setCurrentpage(int currentPage) {
        this.currentPage = currentPage;
    }

    public List<Map<String, Object>> getMaprecords() {
        return maprecords;
    }

    public void setMaprecords(List<Map<String, Object>> maprecords) {
        this.maprecords = maprecords;
    }
    
    


}
