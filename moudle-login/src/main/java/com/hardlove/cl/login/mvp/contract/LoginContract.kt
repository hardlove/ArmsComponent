package com.hardlove.cl.login.mvp.contract

import com.hardlove.cl.login.mvp.model.entity.LoginEntity
import com.jess.arms.mvp.IModel
import com.jess.arms.mvp.IView
import io.reactivex.Observable


interface LoginContract {
    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View : IView {
        fun onErrorTips(code: Int, msg: String)
        fun onSucceed()
        fun onFailed(code: Int, errorMsg: String)

    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model : IModel {
        fun loginByPassword(userName: String, password: String):Observable<LoginEntity>
        fun sendCode(phoneNumber: String)
    }

}
