package com.moon.samples.jsoupcrawler;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;

/**
 * author: moon
 * created on: 17/9/5 上午10:07
 * description:
 */
public interface IJsoupAPI {

    @GET("c501ec8ec7f5")
    Observable<ResponseBody> getList();


}
