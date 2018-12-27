package com.hardlove.cl.water.mvp.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hardlove.cl.water.R
import com.hardlove.cl.water.di.component.DaggerCategoryComponent
import com.hardlove.cl.water.di.module.CategoryModule
import com.hardlove.cl.water.mvp.contract.CategoryContract
import com.hardlove.cl.water.mvp.model.entity.ArticleResult
import com.hardlove.cl.water.mvp.model.entity.Chapter
import com.hardlove.cl.water.mvp.presenter.CategoryPresenter
import com.hardlove.cl.water.mvp.ui.adapter.CategoryAdapter
import com.jess.arms.base.BaseFragment
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils
import kotlinx.android.synthetic.main.fragment_category.*
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
class CategoryFragment : BaseFragment<CategoryPresenter>(), CategoryContract.View {
    companion object {
        fun newInstance(chapter: Chapter): CategoryFragment {
            val fragment = CategoryFragment()
            fragment.setData(chapter)
            return fragment
        }
    }


    override fun setupFragmentComponent(appComponent: AppComponent) {
        DaggerCategoryComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .categoryModule(CategoryModule(this))
                .build()
                .inject(this)
    }

    override fun initView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_category, container, false)
    }

    var page = 1
    override fun initData(savedInstanceState: Bundle?) {
        swipeRefreshLayout.setOnRefreshListener {
            activity?.toast("正在刷新")
            pullToRefresh()
        }
        recyclerView.layoutManager = LinearLayoutManager(this.activity)
        recyclerView.adapter = CategoryAdapter(datas, this.mPresenter!!)

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            var lastVisibleItem: Int = 0
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                //判断RecyclerView的状态 是空闲时，同时，是最后一个可见的ITEM时才加载
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == datas.size) {
                    isAddMore = true
                    mPresenter?.getArtiles(type.id, ++page)//上拉加载更多数据
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                //最后一个可见的ITEM
                lastVisibleItem = (recyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
            }


        })
        swipeRefreshLayout.isRefreshing = true//首次刷新
        pullToRefresh()
    }

    private fun pullToRefresh() {
        page = 1
        mPresenter?.getArtiles(type.id, page)
        isAddMore = false
    }

    var isAddMore: Boolean = true

    val datas: MutableList<ArticleResult.Data.Article> = ArrayList()
    override fun onLoadArticlesSucceed(data: List<ArticleResult.Data.Article>?) {
        if (swipeRefreshLayout.isRefreshing) {
            swipeRefreshLayout.isRefreshing = false //结束刷新UI
        }
        if (isAddMore) {
            datas.addAll(data!!.toList())
        } else {
            datas.clear()
            datas.addAll(data!!.toList())
        }
        recyclerView.adapter.notifyDataSetChanged()
    }

    /**
     * 通过此方法可以使 Fragment 能够与外界做一些交互和通信, 比如说外部的 Activity 想让自己持有的某个 Fragment 对象执行一些方法,
     * 建议在有多个需要与外界交互的方法时, 统一传 {@link Message}, 通过 what 字段来区分不同的方法, 在 {@link #setData(Object)}
     * 方法中就可以 {@code switch} 做不同的操作, 这样就可以用统一的入口方法做多个不同的操作, 可以起到分发的作用
     * <p>
     * 调用此方法时请注意调用时 Fragment 的生命周期, 如果调用 {@link #setData(Object)} 方法时 {@link Fragment#onCreate(Bundle)} 还没执行
     * 但在 {@link #setData(Object)} 里却调用了 Presenter 的方法, 是会报空的, 因为 Dagger 注入是在 {@link Fragment#onCreate(Bundle)} 方法中执行的
     * 然后才创建的 Presenter, 如果要做一些初始化操作,可以不必让外部调用 {@link #setData(Object)}, 在 {@link #initData(Bundle)} 中初始化就可以了
     * <p>
     * Example usage:
     * <pre>
     *fun setData(data:Any) {
     *   if(data is Message){
     *       when (data.what) {
     *           0-> {
     *               //根据what 做你想做的事情
     *           }
     *           else -> {
     *           }
     *       }
     *   }
     *}
     *
     *
     *
     *
     *
     * // call setData(Object):
     * val data = Message();
     * data.what = 0;
     * data.arg1 = 1;
     * fragment.setData(data);
     * </pre>
     *
     * @param data 当不需要参数时 {@code data} 可以为 {@code null}
     */

    lateinit var type: Chapter

    override fun setData(data: Any?) {
        type = data as Chapter
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
