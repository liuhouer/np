package cn.northpark.utils.page;

/**
 * 计算首页和尾页的页码
 *
 * @author yang zhang
 */
public class PageIndex {
    private int startindex;
    private int endindex;

    public PageIndex() {
        // TODO Auto-generated constructor stub
    }

    public PageIndex(int startindex, int endindex) {
        this.startindex = startindex;
        this.endindex = endindex;
    }

    public int getStartindex() {
        return startindex;
    }

    public void setStartindex(int startindex) {
        this.startindex = startindex;
    }

    public int getEndindex() {
        return endindex;
    }

    public void setEndindex(int endindex) {
        this.endindex = endindex;
    }


    /**
     * 根据每次展示的页码数量、 当前页、 总页数   计算开始页码和结束页码，使当前页码处于居中位置
     *
     * @param viewpagecount
     * @param currentPage
     * @param totalpage
     * @return
     */
    public static PageIndex getPageIndex(int viewpagecount, int currentPage, int totalpage) {
		/*
		当前页10
		每页展示页码数10
		总页数80
		
		10 -（4） = 6 */

        int startpage = currentPage - (viewpagecount % 2 == 0 ? viewpagecount / 2 - 1 : viewpagecount / 2);
        int endpage = currentPage + viewpagecount / 2;
        if (startpage < 1) {
            startpage = 1;
            if (totalpage >= viewpagecount) endpage = viewpagecount;
            else endpage = totalpage;
        }
        if (endpage > totalpage) {
            endpage = totalpage;
            if ((endpage - viewpagecount) > 0) startpage = endpage - viewpagecount + 1;
            else startpage = 1;
        }
        return new PageIndex(startpage, endpage);
    }


}
	 
