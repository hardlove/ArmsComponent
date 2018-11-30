package com.hardlove.cl.fooddefender.mvp.model

import android.app.Application
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.hardlove.cl.fooddefender.mvp.contract.LoginContract
import com.hardlove.cl.fooddefender.mvp.model.api.service.DefenderService
import com.hardlove.cl.fooddefender.mvp.model.entity.BaseResult
import com.hardlove.cl.fooddefender.mvp.model.entity.LoginObj
import com.jess.arms.di.scope.ActivityScope
import com.jess.arms.integration.IRepositoryManager
import com.jess.arms.mvp.BaseModel
import io.reactivex.Observable
import okhttp3.MediaType
import okhttp3.RequestBody
import javax.inject.Inject


@ActivityScope
class LoginModel
@Inject
constructor(val repositoryManager: IRepositoryManager) : BaseModel(repositoryManager), LoginContract.Model {
    @Inject
    lateinit var mGson: Gson;
    @Inject
    lateinit var mApplication: Application;

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun login(custom: String, userName: String, password: String): Observable<BaseResult<LoginObj>> {
        val  obj = JsonObject()
        obj.addProperty("customer", custom)
        obj.addProperty("username", userName)
        obj.addProperty("password", password)
        val content = obj.toString()

        val body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), content)
        return repositoryManager.obtainRetrofitService(DefenderService::class.java).login(body)
    }
}
