package com.hzg.txq;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by hzg on 2017/6/6.
 */

public abstract class EndLessOnScrollListener extends RecyclerView.OnScrollListener {
    private LinearLayoutManager mLinearLayoutManager;
    private  int totalNum,preTotalNum,visibleNum,fristItemId;
    //当前页
    private int currentPage=1;
    //是否在上拉数据
    boolean loading=true;

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        //屏幕可见item数量
        visibleNum=recyclerView.getChildCount();
        //已加载的数量
        totalNum=mLinearLayoutManager.getItemCount();
        //当前屏幕的第一个可见item的位置下标
        fristItemId=mLinearLayoutManager.findFirstVisibleItemPosition();

        if (loading)
        {
            if (totalNum>preTotalNum)
            {
                loading=false;
                preTotalNum=totalNum;
            }
        }
        else if (totalNum-visibleNum<=fristItemId)
        {
            currentPage++;
            loading=true;
            onLoadMore(currentPage);

        }
    }
    abstract void onLoadMore(int currentPage);

}
