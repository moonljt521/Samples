package com.moon.samples.main;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.moon.samples.MainActivity;
import com.moon.samples.R;
import com.moon.samples.itemtouchhelper.ItemDragListener;
import com.moon.samples.itemtouchhelper.ItemMoveListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


/**
 *
 * @author liangjt
 */
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyViewHolder> implements ItemMoveListener {

    private Activity mContext;

    private List<MainBody> mList;

    private ViewItemListener mcListener;

    private ItemDragListener itemDragListener;

    public void setMcListener(ViewItemListener mcListener) {
        this.mcListener = mcListener;
    }


    public MainAdapter(Activity context, List<MainBody> list, ItemDragListener itemDragListener) {
        this.mContext = context;
        this.mList = list;
        this.itemDragListener = itemDragListener;
    }

    @Override
    public int getItemCount() {
        return mList == null || mList.size() == 0 ? 0 : mList.size();
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {


        holder.numName.setText(mList.get(position).title);


    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int arg1) {
        return new MyViewHolder(LayoutInflater.from(mContext).
                inflate(R.layout.item_main_fun, parent, false));
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {

        //1、交换数据
        Collections.swap(mList , fromPosition, toPosition);
        //2、刷新
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    @Override
    public boolean onItemRemove(int position) {
        //1、删除数据
//        new ArrayList<String>(Arrays.asList(mList)).remove(position);
        mList.remove(position);
        //2、刷新
        notifyItemRemoved(position);
        return true;
    }


    class MyViewHolder extends ViewHolder implements OnClickListener {

        TextView numName;

        private ImageView dragView;

        public MyViewHolder(View view) {
            super(view);

            numName = (TextView) view.findViewById(R.id.item_main_textview);
            dragView = (ImageView) view.findViewById(R.id.item_main_dragview);

            dragView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {

                    itemDragListener.onStartDrags(MyViewHolder.this);

                    return false;
                }
            });

            numName.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mcListener != null) {
                mcListener.itemClick(v, getLayoutPosition());
            }
        }

    }

    public interface ViewItemListener {
        void itemClick(View v, int position);
    }
}
