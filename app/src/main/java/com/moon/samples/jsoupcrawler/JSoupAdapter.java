package com.moon.samples.jsoupcrawler;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.moon.samples.R;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;


/**
 * author: moon
 * created on:
 * description:  jsoup 爬取数据显示的列表
 */
public class JSoupAdapter extends RecyclerView.Adapter<ViewHolder> {


    private List<String> mBannerList;

    private List<JSoupBody> mDataList;

    private ViewItemListener mcListener;

    private Banner mBanner;

    private static final int BANNER_TYPE = 0;
    private static final int ITEM_TYPE = 1;

    public void setMcListener(ViewItemListener mcListener) {
        this.mcListener = mcListener;

        mBannerList = new ArrayList<>();
        mDataList = new ArrayList<>();
    }




    public void refreshData(List<String> mBannerList, List<JSoupBody> mDataList){

        this.mBannerList.clear();
        this.mDataList.clear();

        this.mBannerList.addAll(mBannerList);
        this.mDataList.addAll(mDataList);

        notifyDataSetChanged();
    }


    public JSoupAdapter() {
        this.mBannerList = new ArrayList<>();
        this.mDataList = new ArrayList<>();
    }

    @Override
    public int getItemCount() {
        if (mDataList == null || mDataList.size() == 0){
            if (mBannerList == null || mBannerList.size() == 0){
                return 0;
            }else {
                return 1;
            }
        }

        if (mBannerList == null || mBannerList.size() == 0){
            return mDataList.size();
        }else {
            return mDataList.size() + 1;
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == BANNER_TYPE){
            return new BannerHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_jsoup_banner,parent,
                    false));
        }else {
            return new ItemHolder(LayoutInflater.from(parent.getContext()).
                    inflate(R.layout.item_jsoup_data, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        if (holder instanceof BannerHolder){

            BannerHolder bannerHolder = (BannerHolder) holder;
//            bannerHolder.banner.
            bannerHolder.banner.setImages(mBannerList);
            bannerHolder.banner.start();

        }else {

            ItemHolder itemHolder = (ItemHolder) holder;
            itemHolder.title.setText(mDataList.get(position-1).getTitle());
            itemHolder.content.setText(mDataList.get(position-1).getContent());
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0){
            return BANNER_TYPE;
        }else {
            return ITEM_TYPE;
        }
    }

    private class ItemHolder extends ViewHolder implements OnClickListener {

        private TextView title;

        private TextView content;

        public ItemHolder(View view) {
            super(view);

            title = (TextView) view.findViewById(R.id.item_jsoup_data_title);
            content = (TextView) view.findViewById(R.id.item_jsoup_data_content);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mcListener != null) {
                mcListener.itemClick(v, getLayoutPosition());
            }
        }

    }

    private class BannerHolder extends ViewHolder {

        private Banner banner ;

        public BannerHolder(View view) {
            super(view);

            banner = (Banner) view.findViewById(R.id.banner);
            banner.setImageLoader(new GlideImageLoader());

            mBanner = banner;

            // setting
            banner.setBannerAnimation(Transformer.DepthPage);
            //设置标题集合（当banner样式有显示title时）
            banner.setBannerTitles(null);
            //设置自动轮播，默认为true
            banner.isAutoPlay(true);
            //设置轮播时间
            banner.setDelayTime(5000);
            //设置指示器位置（当banner模式中有指示器时）
            banner.setIndicatorGravity(BannerConfig.CENTER);

            banner.setImages(mBannerList);

            banner.start();
        }



    }


    public interface ViewItemListener {
        void itemClick(View v, int position);
    }


    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            //Glide 加载图片简单用法
            Glide.with(context).load(path).into(imageView);

        }

//        //提供createImageView 方法，如果不用可以不重写这个方法，主要是方便自定义ImageView的创建
//        @Override
//        public ImageView createImageView(Context context) {
//            //使用fresco，需要创建它提供的ImageView，当然你也可以用自己自定义的具有图片加载功能的ImageView
//            SimpleDraweeView simpleDraweeView=new SimpleDraweeView(context);
//            return simpleDraweeView;
//        }
    }

    public void stopBanner(){
        if (mBanner!=null){
            mBanner.stopAutoPlay();
        }
    }

    public void startBanner(){
        if (mBanner!=null){
            mBanner.startAutoPlay();
        }
    }

}
