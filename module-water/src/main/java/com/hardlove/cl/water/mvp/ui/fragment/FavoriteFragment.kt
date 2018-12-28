package com.hardlove.cl.water.mvp.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hardlove.cl.water.R
import com.hardlove.cl.water.di.component.DaggerFavoriteComponent
import com.hardlove.cl.water.di.module.FavoriteModule
import com.hardlove.cl.water.mvp.contract.FavoriteContract
import com.hardlove.cl.water.mvp.model.entity.CollectListResult
import com.hardlove.cl.water.mvp.presenter.FavoritePresenter
import com.hardlove.cl.water.mvp.ui.adapter.CollectListAdapter
import com.jess.arms.base.BaseFragment
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils
import kotlinx.android.synthetic.main.fragment_favorite.*
import me.jessyan.armscomponent.commonsdk.utils.MLogger
import org.jetbrains.anko.toast


/**
 * 如果没presenter
 * 你可以这样写
 *
 * @FragmentScope(請注意命名空間) class NullObjectPresenterByFragment
 * @Inject constructor() : IPresenter {
 * override fun onStart() {
 * }
 *
 * override fun onDestroy() {
 * }
 * }
 */
class FavoriteFragment : BaseFragment<FavoritePresenter>(), FavoriteContract.View {
    companion object {
        fun newInstance(): FavoriteFragment {
            val fragment = FavoriteFragment()
            return fragment
        }
    }


    override fun setupFragmentComponent(appComponent: AppComponent) {
        DaggerFavoriteComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .favoriteModule(FavoriteModule(this))
                .build()
                .inject(this)
    }

    override fun initView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_favorite, container, false);
    }

    private val datas: ArrayList<CollectListResult.Data.Article> = ArrayList()
    private var isAddMore: Boolean = true
    private var currentPage: Int = 0
    override fun initData(savedInstanceState: Bundle?) {
        swipeRefresh.setOnRefreshListener(object : SwipeRefreshLayout.OnRefreshListener {
            /**
             * Called when a swipe gesture triggers a refresh.
             */
            override fun onRefresh() {
                activity?.toast("正在刷新")
                pullToRefresh()
            }

        })
        recyclerView2.layoutManager = LinearLayoutManager(this.activity)
        recyclerView2.adapter = CollectListAdapter(datas, this.mPresenter!!)

        recyclerView2.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            var lastVisibleItem: Int = 0
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                MLogger.tag(TAG).d("onScrollStateChanged--newState:$newState")
                //判断RecyclerView的状态 是空闲时，同时，是最后一个可见的ITEM时才加载
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == datas.size) {
                    isAddMore = true
                    currentPage++
                    mPresenter!!.queryCollectArticles(currentPage)//上拉加载更多数据
                    MLogger.tag(TAG).d("上拉加载更多，currentpage:$currentPage")

                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                MLogger.tag(TAG).d("onScrolled--dy:$dy")

                //最后一个可见的ITEM
                lastVisibleItem = (recyclerView2.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
            }


        })
        swipeRefresh.isRefreshing = true//首次刷新
        pullToRefresh()
    }

    override fun onUnCollectSucceed(data: CollectListResult.Data.Article) {
        this.datas.remove(data)
        recyclerView2.adapter.notifyDataSetChanged()
    }

    /**
     * 获取数据时回调
     */
    override fun onQueryCollectArticlesSucceed(datas: List<CollectListResult.Data.Article>) {
        if (swipeRefresh.isRefreshing) {
            swipeRefresh.isRefreshing = false //结束刷新UI
        }
        if (!isAddMore) {
            this.datas.clear()
        }
        var start: Int = this.datas.size
        this.datas.addAll(datas)
        recyclerView2.adapter.notifyItemRangeChanged(start, datas.size)
    }

    //下拉刷新
    private fun pullToRefresh() {
        isAddMore = false
        currentPage = 0
        mPresenter!!.queryCollectArticles(currentPage)
        MLogger.tag(TAG).d("正在刷新。。。page:$currentPage")
    }

    override fun setData(data: Any?) {

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

    }
}
