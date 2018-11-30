package com.hardlove.cl.fooddefender.mvp.contract

import com.hardlove.cl.fooddefender.mvp.model.entity.BaseResult
import com.hardlove.cl.fooddefender.mvp.model.entity.LoginObj
import com.jess.arms.mvp.IModel
import com.jess.arms.mvp.IView
import io.reactivex.Observable


interface LoginContract {
    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View : IView {
        fun onCustomValid()
        fun onUserNameValid()
        fun onPasswordValid()
        fun onLoginSucceed()
        fun onLoginFaild(reson: String?)
    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model : IModel {
        fun login(custom: String, userName: String, password: String): Observable<BaseResult<LoginObj>>
    }

}
