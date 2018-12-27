package com.hardlove.cl.water.mvp.ui.activity

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.DrawerLayout
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.hardlove.cl.water.R
import com.hardlove.cl.water.di.component.DaggerMainComponent
import com.hardlove.cl.water.di.module.MainModule
import com.hardlove.cl.water.mvp.contract.MainContract
import com.hardlove.cl.water.mvp.presenter.MainPresenter
import com.hardlove.cl.water.mvp.ui.fragment.FavoriteFragment
import com.hardlove.cl.water.mvp.ui.fragment.HomeFragment
import com.hardlove.cl.water.mvp.ui.fragment.MineFragment
import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils
import kotlinx.android.synthetic.main.activity_main.*
import me.jessyan.armscomponent.commonsdk.core.RouterHub
import me.jessyan.armscomponent.commonsdk.utils.MLogger


/**
 * 如果没presenter
 * 你可以这样写
 *
 * @ActivityScope(請注意命名空間) class NullObjectPresenterByActivity
 * @Inject constructor() : IPresenter {
 * override fun onStart() {
 * }
 *
 * override fun onDestroy() {
 * }
 * }
 */
@Route(path = RouterHub.WATER_MAINACTIVITY)
class MainActivity : BaseActivity<MainPresenter>(), MainContract.View {


    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerMainComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .mainModule(MainModule(this))
                .build()
                .inject(this)
    }


    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_main //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }


    override fun initData(savedInstanceState: Bundle?) {
        drayerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
        drayerLayout.addDrawerListener(object : DrawerLayout.DrawerListener {
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {


            }

            override fun onDrawerOpened(drawerView: View) {

            }

            override fun onDrawerClosed(drawerView: View) {

            }

            override fun onDrawerStateChanged(newState: Int) {

            }
        })

        bottomNavigationBar
                .setActiveColor(R.color.public_color_0F79FD)
                .setInActiveColor(R.color.public_color_06CB7E)
                .setBarBackgroundColor(R.color.public_white)
                .setMode(BottomNavigationBar.MODE_FIXED)
                .addItem(BottomNavigationItem(R.drawable.ic_home, "Home"))
                .addItem(BottomNavigationItem(R.drawable.ic_favorite, "Favorite"))
                .addItem(BottomNavigationItem(R.drawable.ic_news, "Mine"))
                .setTabSelectedListener(object : BottomNavigationBar.OnTabSelectedListener {
                    override fun onTabReselected(position: Int) {
                        MLogger.tag(TAG).d("onTabReselected:$position")

                    }

                    override fun onTabUnselected(position: Int) {
                        MLogger.tag(TAG).d("onTabUnselected:$position")
                        hideFragment(position)
                    }

                    override fun onTabSelected(position: Int) {
                        MLogger.tag(TAG).d("onTabSelected:$position")
                        showFragment(position)
                    }
                })
                .setFirstSelectedPosition(0)
                .initialise()
        showFragment(0)//初始化

    }


    private fun getFragment(position: Int): Fragment {
        val tag = getFragmentTag(position)
        var fragment = supportFragmentManager.findFragmentByTag(tag)
        if (fragment == null) {
            fragment = when (position) {
                0 -> HomeFragment.newInstance()
                1 -> FavoriteFragment.newInstance()
                else -> MineFragment.newInstance()
            }
        }
        return fragment

    }

    private fun getFragmentTag(position: Int): String {
        return when (position) {
            0 -> HomeFragment::class.java.simpleName
            1 -> FavoriteFragment::class.java.simpleName
            else -> MineFragment::class.java.simpleName
        }
    }

    private fun hideFragment(position: Int) {
        val fragment: Fragment = getFragment(position)
        val transaction = supportFragmentManager.beginTransaction()
        if (!fragment.isAdded) {
            transaction.add(R.id.container, fragment, fragment.javaClass.simpleName)
        }
        transaction.hide(fragment)
        transaction.commit()
    }

    private fun showFragment(position: Int) {
        val fragment: Fragment = getFragment(position)
        val transaction = supportFragmentManager.beginTransaction()
        if (!fragment.isAdded) {
            transaction.add(R.id.container, fragment, fragment.javaClass.simpleName)
        }
        transaction.show(fragment)
        transaction.commit()
    }


    override fun showLoading() {

    }

    override fun hideLoading() {

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
