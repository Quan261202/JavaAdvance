package com.halloween.pagination;

public class Pagination {
    private Double itemPage;
    private Integer totalItem;
    private Integer currentPage;
    private Integer rangePage;
    private Integer totalPage;
    private Integer category;

    public Pagination(Double itemPage, Integer currentPage, Integer rangePage, Integer totalItem, Integer category) {
        super();
        this.itemPage = itemPage;
        this.currentPage = currentPage;
        this.rangePage = rangePage;
        this.category = category;
        this.totalItem = totalItem;
        this.totalPage = (int) Math.ceil(this.totalItem/itemPage);
    }

    public Double getItemPage() {
        return itemPage;
    }

    public void setItemPage(Double itemPage) {
        this.itemPage = itemPage;
    }

    public Integer getTotalItem() {
        return totalItem;
    }

    public void setTotalItem(Integer totalItem) {
        this.totalItem = totalItem;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getRangePage() {
        return rangePage;
    }

    public void setRangePage(Integer rangePage) {
        this.rangePage = rangePage;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public String showPagination() {
        StringBuilder pagination = new StringBuilder();
        if(this.totalPage > 1)
        {
            String prev = "<a class=''>&laquo;</a>";
            if(this.currentPage > 1) {
                prev = "<a href='loadProducts?page=" + (this.currentPage - 1) + "&category=" + this.category + "' class=''>&laquo;</a>";
            }
            pagination.append(prev);
            String next = "<a class=''>&raquo;</a>";
            if(this.currentPage < this.totalPage) {
                next = "<a href='loadProducts?page=" + (this.currentPage + 1) + "&category=" + this.category + "' class=''>&raquo;</a>";
            }
            int startPage;
            double endPage;
            if(this.rangePage < this.totalPage)
            {
                startPage = this.currentPage - (this.rangePage - 1)/2;
                endPage = this.currentPage + Math.ceil((this.rangePage - 1)/2.0);
            }
            else {
                startPage = 1;
                endPage = this.totalPage;
            }
            for(int i = startPage; i <= endPage; ++i)
            {
                String classList = "";
                if(i == startPage)
                {
                    classList += "start";
                }
                else if(i == endPage)
                {
                    classList += "end";
                }
                if(this.currentPage == i)
                {
                    classList += " active";
                }
                pagination.append("<a href='loadProducts?page=").append(i).append("&category=").append(this.category).append("' class='").append(classList).append("'>").append(i).append("</a>");
            }
            pagination.append(next);
        }
        return pagination.toString();
    }
}
