package com.hardlove.cl.water.mvp.ui.adapter

import android.app.Activity
import android.support.graphics.drawable.VectorDrawableCompat
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.hardlove.cl.water.R
import com.hardlove.cl.water.mvp.model.entity.ArticleResult
import com.hardlove.cl.water.mvp.presenter.CategoryPresenter
import com.jess.arms.base.BaseHolder
import com.jess.arms.base.DefaultAdapter
import com.jess.arms.base.DefaultAdapter.OnRecyclerViewItemClickListener
import com.jess.arms.utils.ArmsUtils
import me.jessyan.armscomponent.commonres.ui.WebActivity
import me.jessyan.armscomponent.commonsdk.utils.MLogger
import me.jessyan.armscomponent.commonsdk.utils.TimeUtil

/**
 * Created by Chenlu on 2018/12/15
 * Email:chenlu@globalroam.com
 */
class CategoryAdapter(infos: List<ArticleResult.Data.Article>?, val mPresenter: CategoryPresenter) : DefaultAdapter<ArticleResult.Data.Article>(infos), OnRecyclerViewItemClickListener<ArticleResult.Data.Article> {
    init {

        setOnItemClickListener(this)
    }

    override fun onItemClick(view: View, viewType: Int, data: ArticleResult.Data.Article?, position: Int) {
        WebActivity.lunchActivity(view.context as Activity, data!!.link, null)
    }

    override fun getLayoutId(viewType: Int): Int = R.layout.item_article

    override fun getHolder(v: View, viewType: Int): BaseHolder<ArticleResult.Data.Article> =
            CategoryAdapter.Companion.ViewHolder(v, mPresenter)


    companion object {
        class ViewHolder(view: View, val mPresenter: CategoryPresenter) : BaseHolder<ArticleResult.Data.Article>(view) {
            override fun setData(data: ArticleResult.Data.Article, position: Int) {
                itemView.findViewById<TextView>(R.id.tvTitle).text = data.title
                itemView.findViewById<TextView>(R.id.tvAuthor).text = data.author
                itemView.findViewById<TextView>(R.id.tvPublishTime).text = TimeUtil.getStringByFormat(data.publishTime, TimeUtil.dateFormat)
                val ivCollect = itemView.findViewById<ImageView>(R.id.ivCollect)
                refreshCollectState(data, ivCollect)
                MLogger.tag(TAG).d("Position:${position}---collect:${data.isCollect}")
                ivCollect.setOnClickListener {
                    if (data.isCollect) {//取消收藏
                        mPresenter.unCollectArticl(data.id).subscribe { it ->
                            if (it.errorCode == 0) {
                                data.isCollect = false
                                refreshCollectState(data, ivCollect)
                            } else {
                                MLogger.tag(TAG).e("取消收藏--》id:${data.id}")
                            }
                        }
                    } else {//收藏
                        mPresenter.collectArticl(data.id).subscribe { it ->
                            if (it.errorCode == 0) {
                                data.isCollect = true
                                refreshCollectState(data, ivCollect)
                            } else {
                                MLogger.tag(TAG).e("收藏失败--》id:${data.id}")
                            }
                        }
                    }
                }
                var iv = itemView.findViewById<ImageView>(R.id.iv)
                //可以在任何可以拿到 Context 的地方,拿到 AppComponent,从而得到用 Dagger 管理的单例对象
                val mAppComponent = ArmsUtils.obtainAppComponentFromContext(itemView.getContext());
                val mImageLoader = mAppComponent.imageLoader()

            }

            private fun refreshCollectState(data: ArticleResult.Data.Article, ivCollect: ImageView) {
                if (data.isCollect) {
                    val drawableCompat = VectorDrawableCompat.create(ivCollect.resources, R.drawable.ic_favorite, itemView.context.theme)
                    ivCollect.setImageDrawable(drawableCompat!!)
                } else {
                    val drawableCompat = VectorDrawableCompat.create(ivCollect.resources, R.drawable.ic_un_favorite, itemView.context.theme)
                    ivCollect.setImageDrawable(drawableCompat!!)
                }
            }


        }
    }
}