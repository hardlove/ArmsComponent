package com.hardlove.cl.fooddefender.mvp.model.api.service

import com.hardlove.cl.fooddefender.mvp.model.api.Api.DEFENDER_DOMAIN_NAME
import com.hardlove.cl.fooddefender.mvp.model.entity.BaseResult
import com.hardlove.cl.fooddefender.mvp.model.entity.LoginObj
import io.reactivex.Observable
import me.jessyan.retrofiturlmanager.RetrofitUrlManager.DOMAIN_NAME_HEADER
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

/**
 * Created by Chenlu on 2018/11/29
 * Email:chenlu@globalroam.com
 */
interface DefenderService {
    @Headers(DOMAIN_NAME_HEADER + DEFENDER_DOMAIN_NAME)
    @POST("api/mobile/login")
    fun login(@Body requestBody: RequestBody): Observable<BaseResult<LoginObj>>

}