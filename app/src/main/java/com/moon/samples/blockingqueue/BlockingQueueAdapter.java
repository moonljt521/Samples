package com.moon.samples.blockingqueue;

import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.moon.samples.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;


/**
 * 阻塞队列 - adapter
 *
 * @author liangjt
 */
public class BlockingQueueAdapter extends RecyclerView.Adapter<BlockingQueueAdapter.MyViewHolder> {

    private List<String> mList;

    private ExecutorService service;

    private BlockingQueue<String> blockingQueue;

    private Handler handler;
    private AddMsgThread addMsgThread;
    private ReadMsgThread readMsgThread;

    public BlockingQueueAdapter() {
        mList = new ArrayList<>();
        service = Executors.newCachedThreadPool();
        blockingQueue = new LinkedBlockingDeque<>(1000);

        handler = new Handler();
    }

    @Override
    public int getItemCount() {
        return mList == null || mList.size() == 0 ? 0 : mList.size();
    }

    public void add20() {
        int index = mList.size();
        for (int i = index; i < (index + 20); i++) {
            mList.add(i + "");
        }

        notifyDataSetChanged();
    }

    public void stopQueue() {
        addMsgThread.quitAddMsgQueue();
        readMsgThread.quitRedMsgQueue();
        service.shutdown();
    }

    public void executeQueue() {
        addMsgThread = new AddMsgThread(blockingQueue);
        service.execute(addMsgThread);

        readMsgThread = new ReadMsgThread(blockingQueue);
        service.execute(readMsgThread);

        readMsgThread.setMsg(new ReadMsgThread.IReadMsg() {
            @Override
            public void getMsg(List<String> list) {

                handler.post(new Runnable() {
                    @Override
                    public void run() {


                        mList.addAll(list);

                        notifyDataSetChanged();

                        if (scrolled != null) {
                            scrolled.scroll();
                        }

                    }
                });

            }
        });


    }


    public void clearList() {
        mList.clear();
        notifyDataSetChanged();
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {


        holder.numName.setText("亲，您收到了一条消息：" + mList.get(position));

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int arg1) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_animation_recyclerview_layout, parent, false));
    }


    class MyViewHolder extends ViewHolder {

        TextView numName;

        private ImageView dragView;

        public MyViewHolder(View view) {
            super(view);

            numName = (TextView) view.findViewById(R.id.item_main_textview);
            dragView = (ImageView) view.findViewById(R.id.item_main_dragview);

            view.setBackground(null);

        }


    }

    public void setScrolled(IScrolled scrolled) {
        this.scrolled = scrolled;
    }

    private IScrolled scrolled;

    public interface IScrolled {
        void scroll();
    }
}
