package com.moon.samples.main;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.moon.samples.R;


/**
 *
 * @author liangjt
 */
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyViewHolder> {

    private Activity mContext;

    private String[] mList;

    private ViewItemListener mcListener;

    public void setMcListener(ViewItemListener mcListener) {
        this.mcListener = mcListener;
    }


    public MainAdapter(Activity context, String[] list) {
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public int getItemCount() {
        return mList == null || mList.length == 0 ? 0 : mList.length;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {


        holder.numName.setText(mList[position]);


    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int arg1) {
        return new MyViewHolder(LayoutInflater.from(mContext).
                inflate(R.layout.item_main_fun, parent, false));
    }

    class MyViewHolder extends ViewHolder implements OnClickListener {

        TextView numName;


        public MyViewHolder(View view) {
            super(view);

            numName = (TextView) view.findViewById(R.id.item_main_textview);


            view.setOnClickListener(this);
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
