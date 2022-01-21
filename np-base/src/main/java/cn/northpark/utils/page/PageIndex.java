package cn.northpark.utils.page;

/**
 * 计算首页和尾页的页码
 *
 * @author yang zhang
 */
public class PageIndex {
    private int startIndex;
    private int endIndex;

    public PageIndex() {
        // TODO Auto-generated constructor stub
    }

    public PageIndex(int startIndex, int endIndex) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(int endIndex) {
        this.endIndex = endIndex;
    }


    /**
     * 根据每次展示的页码数量、 当前页、 总页数   计算开始页码和结束页码，使当前页码处于居中位置
     *
     * @param viewPageCount
     * @param currentPage
     * @param totalPage
     * @return
     */
    public static PageIndex getPageIndex(int viewPageCount, int currentPage, int totalPage) {
		/*
		当前页10
		每页展示页码数10
		总页数80
		
		10 -（4） = 6 */

        int startPage = currentPage - (viewPageCount % 2 == 0 ? viewPageCount / 2 - 1 : viewPageCount / 2);
        int endPage = currentPage + viewPageCount / 2;
        if (startPage < 1) {
            startPage = 1;
            if (totalPage >= viewPageCount) endPage = viewPageCount;
            else endPage = totalPage;
        }
        if (endPage > totalPage) {
            endPage = totalPage;
            if ((endPage - viewPageCount) > 0) startPage = endPage - viewPageCount + 1;
            else startPage = 1;
        }
        return new PageIndex(startPage, endPage);
    }


}
	 
