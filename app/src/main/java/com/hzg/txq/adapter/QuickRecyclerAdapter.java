package com.hzg.txq.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.List;

/**
 * Created by hzg on 2017/5/19.
 * 自定义 RecyclerAdapter
 */

public abstract class QuickRecyclerAdapter<T, T1 extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<T1> {
   //数据
    List<T> mDatas;



    public QuickRecyclerAdapter(List<T> mDatas) {
        this.mDatas = mDatas;
    }

    public List<T> getDatas() {
        return mDatas;
    }

    public void setDatas(List<T> mDatas) {
        this.mDatas = mDatas;
    }

    @Override
    public T1 onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(getLayoutId(viewType), parent, false);
        return getViewHodler(view, viewType);
    }

    @Override
    public void onBindViewHolder(T1 holder, int position) {
        convert(holder, position, mDatas.size()==position?null:mDatas.get(position));
    }

    protected abstract void convert(T1 holder, int position, T bean);

    protected abstract T1 getViewHodler(View view, int type);

    protected abstract int getLayoutId(int viewType);

    @Override
    public int getItemCount() {
        if (mDatas == null) return 0;
        return mDatas.size();
    }



    public static class VH extends RecyclerView.ViewHolder {
        ViewDataBinding mBinding;

        public ViewDataBinding getBinding() {
            return mBinding;
        }

        public VH(View itemView) {
            super(itemView);
            mBinding = DataBindingUtil.bind(itemView);
        }
    }

    public static class OnRecyclerItemClickListener implements RecyclerView.OnItemTouchListener {
        private RecyclerView mRecyclerView;
        private GestureDetectorCompat mGestureDetector;

        public OnRecyclerItemClickListener(Context context, final RecyclerView mRecyclerView) {
            this.mRecyclerView = mRecyclerView;

            mGestureDetector = new GestureDetectorCompat(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    if (mRecyclerView != null) {
                        View view = mRecyclerView.findChildViewUnder(e.getX(), e.getY());
                        if (view != null) {

                            RecyclerView.ViewHolder viewHolder = mRecyclerView.getChildViewHolder(view);
                            int position = viewHolder.getAdapterPosition();
                            viewHolder.itemView.onTouchEvent(e);
                            onClick(viewHolder, position);
                        }
                    }
                    return true;

                }
            });
        }

        public void onClick(RecyclerView.ViewHolder holder, int position) {

        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            //不能返回true，因为拦截后recyclerview不能处理滑动操作
            mGestureDetector.onTouchEvent(e);
            Log.d("110", "onInterceptTouchEvent");
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
            //不会执行，因为事件没有被拦截
            mGestureDetector.onTouchEvent(e);
            Log.d("110", "onTouchEvent");
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }

    }
}
