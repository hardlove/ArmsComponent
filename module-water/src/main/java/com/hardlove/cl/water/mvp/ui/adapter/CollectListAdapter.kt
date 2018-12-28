package com.hardlove.cl.water.mvp.ui.adapter

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.TextView
import com.hardlove.cl.water.R
import com.hardlove.cl.water.mvp.model.entity.CollectListResult
import com.hardlove.cl.water.mvp.presenter.FavoritePresenter
import com.jess.arms.base.BaseHolder
import com.jess.arms.base.DefaultAdapter
import me.jessyan.armscomponent.commonres.ui.WebActivity
import org.jetbrains.anko.toast

/**
 * Created by Chenlu on 2018/12/15
 * Email:chenlu@globalroam.com
 */
class CollectListAdapter(infos: List<CollectListResult.Data.Article>?, val mPresenter: FavoritePresenter) : DefaultAdapter<CollectListResult.Data.Article>(infos),
        DefaultAdapter.OnRecyclerViewItemClickListener<CollectListResult.Data.Article> {

    init {
        setOnItemClickListener(this)
    }

    override fun onItemClick(view: View, viewType: Int, data: CollectListResult.Data.Article?, position: Int) {
        WebActivity.lunchActivity(view.context as Activity, data!!.link, null)
    }

    override fun getLayoutId(viewType: Int): Int = R.layout.item_collect_article

    override fun getHolder(v: View, viewType: Int): BaseHolder<CollectListResult.Data.Article> =
            CollectListAdapter.Companion.ViewHolder(v, mPresenter)


    companion object {
        class ViewHolder(view: View, val mPresenter: FavoritePresenter) : BaseHolder<CollectListResult.Data.Article>(view) {
            private lateinit var context: Context
            override fun setData(data: CollectListResult.Data.Article, position: Int) {
                context = itemView.context

                itemView.findViewById<TextView>(R.id.tvTitle).text = data.title
                itemView.findViewById<TextView>(R.id.tvAuthor).text = data.author
                itemView.findViewById<TextView>(R.id.tvNiceDate).text = data.niceDate

                itemView.setOnLongClickListener {
                    AlertDialog.Builder(context)
                            .setMessage("是否要取消收藏？")
                            .setCancelable(false)
                            .setPositiveButton("确定", object : DialogInterface.OnClickListener {
                                override fun onClick(dialog: DialogInterface?, which: Int) {
                                    context.toast("取消收藏")
                                    unCollectRequest(data)
                                }
                            })
                            .setNegativeButton("取消", null)
                            .create().show()
                    true
                }
            }

            private fun unCollectRequest(data: CollectListResult.Data.Article) {
                mPresenter.unCollectRequest(data)
            }

        }


    }
}
