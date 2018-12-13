package com.hardlove.cl.login.mvp.model

import android.app.Application
import com.google.gson.Gson
import com.hardlove.cl.login.mvp.contract.LoginContract
import com.hardlove.cl.login.mvp.model.api.service.LoginService
import com.hardlove.cl.login.mvp.model.entity.LoginEntity
import com.jess.arms.di.scope.ActivityScope
import com.jess.arms.integration.IRepositoryManager
import com.jess.arms.mvp.BaseModel
import io.reactivex.Observable
import javax.inject.Inject


@ActivityScope
class LoginModel
@Inject
constructor(repositoryManager: IRepositoryManager) : BaseModel(repositoryManager), LoginContract.Model {


    @Inject
    lateinit var mGson: Gson;
    @Inject
    lateinit var mApplication: Application;

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun loginByPassword(userName: String, password: String): Observable<LoginEntity> {
        return mRepositoryManager.obtainRetrofitService(LoginService::class.java)
                .loginByPassword(userName, password)
    }

    override fun sendCode(phoneNumber: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
