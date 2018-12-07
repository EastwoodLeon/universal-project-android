package com.pxkeji.util;

/**
 * Created by Administrator on 2018/1/11.
 */

public class PageController {

    private int currentPage = 1;
    private int totalPages = 1;


    public int getCurrentPage() {
        return currentPage;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public void firstPage() {
        currentPage = 1;
        totalPages = 1;
    }

    public boolean isFirstPage() {
        return currentPage == 1;
    }

    public boolean hasNextPage() {
        if (currentPage < totalPages) {
            return true;
        } else {
            return false;
        }

    }


    public void nextPage() {
        currentPage++;
    }
}
