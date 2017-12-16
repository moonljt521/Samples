package com.moon.samples.jsoupcrawler.view;

import com.ljq.mvpframework.view.BaseMvpView;
import com.moon.samples.jsoupcrawler.JSoupBody;

import java.util.List;

import okhttp3.ResponseBody;

/**
 * author: moon
 * created on: 17/12/16 下午5:45
 * description:
 */
public interface JSoupView extends BaseMvpView {


    void requestLoading();

    void requestSuccess(List<String> mBannerList, List<JSoupBody> mDataList);

    void fail();


}
