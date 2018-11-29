package com.hardlove.cl.fooddefender.widgets;


import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hardlove.cl.fooddefender.R;

/**
 * Created by Chenlu on 2017/10/27
 */

public class NetWorkView extends LinearLayout implements View.OnClickListener {

    private Button mReload;
    private TextView mTips;
    private ProgressBar mProgressBarLayer;
    private View mNoNetLayer;

    public NetWorkView(Context context) {
        super(context);
        init(context);
    }

    public NetWorkView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public NetWorkView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        setGravity(Gravity.CENTER);
        inflate(context, R.layout.statu_change_view, this);
        mProgressBarLayer = (ProgressBar) findViewById(R.id.progressbar);
        mNoNetLayer = findViewById(R.id.no_network_layer);

        mReload = (Button) findViewById(R.id.btn_reload);
        mTips = (TextView) findViewById(R.id.tv_tips);
        changeLayer(Layer.DATA);

        mReload.setOnClickListener(this);
    }

    public void changeLayer(Layer layer) {
        setVisibility(VISIBLE);
        mProgressBarLayer.setVisibility(GONE);
        mNoNetLayer.setVisibility(GONE);
        switch (layer) {
            case LOADING:
                mProgressBarLayer.setVisibility(VISIBLE);
                break;
            case NONET:
                mNoNetLayer.setVisibility(VISIBLE);
                break;
            case DATA:
            case GONE:
                setVisibility(GONE);
            default:
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_reload) {
            if (mReloadListener != null) {
                mReloadListener.onNetReload();
            }
        }
    }

    public void setReloadListener(OnNetReloadListener reloadListener) {
        mReloadListener = reloadListener;
    }

    private OnNetReloadListener mReloadListener;
    public interface OnNetReloadListener {
        void onNetReload();
    }


    public enum Layer {
        LOADING, NONET, DATA,GONE
    }


}
