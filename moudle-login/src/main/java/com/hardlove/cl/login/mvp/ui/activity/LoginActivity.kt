package com.hardlove.cl.login.mvp.ui.activity

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import com.hardlove.cl.login.R
import com.hardlove.cl.login.di.component.DaggerLoginComponent
import com.hardlove.cl.login.di.module.LoginModule
import com.hardlove.cl.login.mvp.contract.LoginContract
import com.hardlove.cl.login.mvp.presenter.LoginPresenter
import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils
import kotlinx.android.synthetic.main.activity_login.*
import me.jessyan.armscomponent.commonres.utils.LoadingDialogUtils.showLoadingDialog
import me.jessyan.armscomponent.commonsdk.core.RouterHub
import me.jessyan.armscomponent.commonsdk.utils.Utils
import org.jetbrains.anko.toast

open class LoginActivity : BaseActivity<LoginPresenter>(), LoginContract.View {
    override fun onErrorTips(code: Int, msg: String) {
        toast("code:$code msg:$msg")
    }

    override fun onSucceed() {
        Utils.navigation(RouterHub.WATER_MAINACTIVITY)
        killMyself()
        toast("登陆成功")
    }

    override fun onFailed(code: Int, errorMsg: String) {
        toast("code: $code msg:$errorMsg")
    }

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerLoginComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .loginModule(LoginModule(this))
                .build()
                .inject(this)

    }


    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_login //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }


    override fun initData(savedInstanceState: Bundle?) {
        login.setOnClickListener {
            val userName = userName.text.toString().trim()
            val password = password.text.toString().trim()
            mPresenter?.loginByPassword(userName, password)
        }
    }

    private var loadingDialog: Dialog? = null
    override fun showLoading() {
        loadingDialog = showLoadingDialog(this)
    }

    override fun hideLoading() {
        loadingDialog?.hide()
    }

    override fun showMessage(message: String) {
        ArmsUtils.snackbarText(message)
    }

    override fun launchActivity(intent: Intent) {
        ArmsUtils.startActivity(intent)
    }

    override fun killMyself() {
        finish()
    }
}
