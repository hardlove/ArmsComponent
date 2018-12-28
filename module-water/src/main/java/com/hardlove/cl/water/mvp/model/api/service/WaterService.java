package com.hardlove.cl.water.mvp.model.api.service;

import com.hardlove.cl.water.mvp.model.entity.ArticleResult;
import com.hardlove.cl.water.mvp.model.entity.BaseResult;
import com.hardlove.cl.water.mvp.model.entity.Chapter;
import com.hardlove.cl.water.mvp.model.entity.CollectListResult;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

import static com.hardlove.cl.water.mvp.model.api.Api.WATER_DOMAIN_NAME;
import static me.jessyan.retrofiturlmanager.RetrofitUrlManager.DOMAIN_NAME_HEADER;

/**
 * Created by Chenlu on 2018/12/15
 * Email:chenlu@globalroam.com
 */
public interface WaterService {
    /**
     * @return 获取公众号列表
     */
    @Headers({DOMAIN_NAME_HEADER+WATER_DOMAIN_NAME})
    @GET("/wxarticle/chapters/json ")
    Observable<BaseResult<List<Chapter>>> queryChapters();

    /**
     *查看某个公众号历史数据
     * @param id
     * @param page
     * @return
     */
    @Headers({DOMAIN_NAME_HEADER+WATER_DOMAIN_NAME})
    @GET("/wxarticle/list/{id}/{page}/json")
    Observable<ArticleResult> getArtiles(@Path("id") int id, @Path("page") int page);

    /**
     * 收藏站内文章
     * @param id
     * @return
     */
    @Headers({DOMAIN_NAME_HEADER+WATER_DOMAIN_NAME})
    @POST("/lg/collect/{id}/json")
    Observable<BaseResult<Object>> collectArticle(@Path("id") int id);

    /**
     * 取消收藏(文章列表的收藏)
     * @param id
     * @return
     */
    @Headers({DOMAIN_NAME_HEADER + WATER_DOMAIN_NAME})
    @POST("/lg/uncollect_originId/{id}/json")
    Observable<BaseResult<Object>> unCollectArticle(@Path("id") int id);

    /**
     *获取文章收藏列表
     */
    @Headers({DOMAIN_NAME_HEADER + WATER_DOMAIN_NAME})
    @GET("/lg/collect/list/{page}/json")
    Observable<CollectListResult> queryCollectArticles(@Path("page") int currentPage);


}
