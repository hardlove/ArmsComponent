package com.hardlove.cl.fooddefender.mvp.ui.activity

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import com.hardlove.cl.fooddefender.R
import com.hardlove.cl.fooddefender.di.component.DaggerLoginComponent
import com.hardlove.cl.fooddefender.di.module.LoginModule
import com.hardlove.cl.fooddefender.mvp.contract.LoginContract
import com.hardlove.cl.fooddefender.mvp.presenter.LoginPresenter
import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils
import kotlinx.android.synthetic.main.activity_login.*
import me.jessyan.armscomponent.commonres.utils.LoadingDialogUtils
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class LoginActivity : BaseActivity<LoginPresenter>(), LoginContract.View {


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
        btn_login.setOnClickListener {
            val custom = edt_company.text.toString().trim()
            val userName = edt_user_name.text.toString().trim()
            val password = edt_password.text.toString().trim()
            mPresenter?.login(custom, userName, password)

        }
    }

    var loadingDialog: ProgressDialog? = null

    override fun showLoading() {
        loadingDialog = LoadingDialogUtils.showLoadingDialog(this)
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

    override fun onCustomValid() = toast("Custom不合法")

    override fun onUserNameValid() = toast("UserName不合法")

    override fun onPasswordValid() = toast("密码以字母开头，长度在6~18之间，只能包含字符、数字和下划线")
    override fun onLoginSucceed() {
        toast("登陆成功")
        startActivity<MainActivity>()
        finish()
    }

    override fun onLoginFaild(reson: String?) {
        toast("登陆失败  $reson")
    }

}
