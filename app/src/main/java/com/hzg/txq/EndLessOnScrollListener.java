package com.hzg.txq;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by hzg on 2017/6/6.
 * recyclerView 上拉刷新
 */

public abstract class EndLessOnScrollListener extends RecyclerView.OnScrollListener {
    private LinearLayoutManager mLinearLayoutManager;
    private  int totalNum,preTotalNum,visibleNum,fristItemId;
    //当前页
    private int currentPage=1;
    //是否在上拉数据
    boolean loading=true;
    //是否正在加载数据
    boolean isloading=false;

    public EndLessOnScrollListener(LinearLayoutManager mLinearLayoutManager) {
        this.mLinearLayoutManager = mLinearLayoutManager;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        //屏幕可见item数量
        visibleNum=recyclerView.getChildCount();
        //已加载的数量
        totalNum=mLinearLayoutManager.getItemCount();
        //当前屏幕的第一个可见item的位置下标
        System.out.println("Last Com position:"  +mLinearLayoutManager.findLastCompletelyVisibleItemPosition());
        System.out.println("Lastposition:"  +mLinearLayoutManager.findLastVisibleItemPosition());
        System.out.println("Frist Com position:"  +mLinearLayoutManager.findFirstCompletelyVisibleItemPosition());
        System.out.println("Frist position:"  +mLinearLayoutManager.findFirstVisibleItemPosition());

        fristItemId=mLinearLayoutManager.findFirstVisibleItemPosition();
       int  lastItemId=mLinearLayoutManager.findLastVisibleItemPosition();

        if (loading)
        {
            if (totalNum>preTotalNum)
            {
                loading=false;
                preTotalNum=totalNum;
            }
        }
        else  if (totalNum==lastItemId+1)
        {
                            currentPage++;
                loading = true;
                onLoadMore(currentPage);
        }
//        else if (totalNum==fristItemId)
//        {
//            if(!isloading) {
//
//                currentPage++;
//                loading = true;
//                onLoadMore(currentPage);
//            }
//
//        }
    }
    abstract void onLoadMore(int currentPage);

    public void setIsloading(boolean isloading) {
        this.isloading = isloading;
    }
}
