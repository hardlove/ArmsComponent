package com.hardlove.cl.login.mvp.model.api.service;

import com.hardlove.cl.login.mvp.model.entity.LoginEntity;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

import static com.hardlove.cl.login.mvp.model.api.Api.LOGIN_DOMAIN_NAME;
import static me.jessyan.retrofiturlmanager.RetrofitUrlManager.DOMAIN_NAME_HEADER;


/**
 * Created by Chenlu on 2018/12/11
 * Email:chenlu@globalroam.com
 */
public interface LoginService {

    @Headers({DOMAIN_NAME_HEADER + LOGIN_DOMAIN_NAME})
    @FormUrlEncoded
    @POST("/user/login")
    Observable<LoginEntity> loginByPassword(@Field("username") String userName, @Field("password") String password);

    @POST
    @Headers({DOMAIN_NAME_HEADER + LOGIN_DOMAIN_NAME})
    Observable<Response> sendCode(String phoneNumber);
}
