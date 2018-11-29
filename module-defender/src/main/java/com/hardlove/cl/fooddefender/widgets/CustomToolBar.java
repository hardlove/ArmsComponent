package com.hardlove.cl.fooddefender.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.hardlove.cl.fooddefender.R;


/**
 * Created by CL on 2016/12/15.
 * 通用toolbar
 */

public class CustomToolBar extends FrameLayout implements View.OnTouchListener, View.OnClickListener {


    public ImageView mLeftIv;
    TextView mLeftTv;
    ViewGroup mLeftParent;
    Spinner mCenterSpinner;
    TextView mCenterTv;
    FrameLayout mCenterParent;
    TextView mRightInsideTv;
    SwitchCompat mRightInsideSwitch;
    LinearLayout mRightInsideParent;
    TextView mRightTvMain;
    TextView mRightTvSub;
    TextView mLeftTitle;
    FrameLayout mRightParent;
    RelativeLayout mCustomLayer;
    Toolbar mToolbar;
    AppBarLayout mToolbarRoot;
    private float mAlpha;


    public CustomToolBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        LinearLayout title = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.custom_toolbar, null);

        findViews(title);

        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        addView(title, params);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomToolBar);
        int leftIconResId = typedArray.getResourceId(R.styleable.CustomToolBar_leftIcon, -1);
        if (leftIconResId != -1) {
            mLeftIv.setImageResource(leftIconResId);
        } else {
            mLeftIv.setVisibility(GONE);
            if (!isInEditMode()) {
                mLeftTv.setPadding(getResources().getDimensionPixelSize(R.dimen.x_ui_px_25_0), 0, getResources().getDimensionPixelSize(R.dimen.x_ui_px_25_0), 0);
            }
        }
        float textSize = 1 + typedArray.getDimensionPixelSize(R.styleable.CustomToolBar_titleTextSize, getResources().getDimensionPixelSize(R.dimen.font_ui_px_40_0));

//        int textSize = 1 + typedArray.getInt(R.styleable.CustomToolBar_titleTextSize, -1);

        int textColor = typedArray.getColor(R.styleable.CustomToolBar_titleTextColor, 0xFFFFFFFF);
        String leftText = typedArray.getString(R.styleable.CustomToolBar_leftText);
        mLeftTv.setText(leftText);
        if (TextUtils.isEmpty(leftText)) {
            mLeftTv.setVisibility(GONE);
        } else {
            mLeftTv.setVisibility(VISIBLE);
        }
        if (textSize > 0) {
            mLeftTv.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        }

        mLeftTv.setTextColor(textColor);

        String centerText = typedArray.getString(R.styleable.CustomToolBar_centerText);
        mCenterTv.setText(centerText);
        if (textSize > 0) {
            mCenterTv.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        }
        mCenterTv.setTextColor(textColor);

        String leftTitle = typedArray.getString(R.styleable.CustomToolBar_leftTitle);
        mLeftTitle.setText(leftTitle);
        if (textSize > 0) {
            mLeftTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        }
        mLeftTitle.setTextColor(textColor);

        Drawable rightIcon = typedArray.getDrawable(R.styleable.CustomToolBar_rightIcon);
        if (rightIcon != null) {
            mRightTvMain.setCompoundDrawablesWithIntrinsicBounds(rightIcon, null, null, null);
        } else {
            String rightText = typedArray.getString(R.styleable.CustomToolBar_rightText);
            if (textSize > 0) {
                mRightTvMain.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
            }
            mRightTvMain.setTextColor(textColor);
            mRightTvMain.setText(rightText);
            if (TextUtils.isEmpty(rightText)) {
                mRightTvMain.setVisibility(GONE);
            }
        }

        Drawable rightSubIcon = typedArray.getDrawable(R.styleable.CustomToolBar_rightSubIcon);
        if (rightSubIcon != null) {
            mRightTvSub.setCompoundDrawablesWithIntrinsicBounds(rightSubIcon, null, null, null);
        } else {
            String rightText = typedArray.getString(R.styleable.CustomToolBar_rightSubText);
            mRightTvSub.setText(rightText);
            if (textSize > 0) {
                mRightTvSub.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
            }
            mRightTvSub.setTextColor(textColor);
            if (TextUtils.isEmpty(rightText)) {
                mRightTvSub.setVisibility(GONE);
            }
        }

        mAlpha = typedArray.getFloat(R.styleable.CustomToolBar_pressAlpha, 0.6f);

        setOnViewTouchListener();

        typedArray.recycle();

    }

    private void findViews(LinearLayout title) {
        mLeftIv = (ImageView) title.findViewById(R.id.left_iv);
        mLeftTv = (TextView) title.findViewById(R.id.left_tv);
        mLeftParent = (ViewGroup) title.findViewById(R.id.left_parent);
        mCenterSpinner = (Spinner) title.findViewById(R.id.center_spinner);
        mCenterTv = (TextView) title.findViewById(R.id.center_tv);
        mCenterParent = (FrameLayout) title.findViewById(R.id.center_parent);
        mRightInsideTv = (TextView) title.findViewById(R.id.right_inside_tv);
        mRightInsideSwitch = (SwitchCompat) title.findViewById(R.id.right_inside_switch);
        mRightInsideParent = (LinearLayout) title.findViewById(R.id.right_inside_parent);
        mRightTvMain = (TextView) title.findViewById(R.id.right_tv_main);
        mRightTvSub = (TextView) title.findViewById(R.id.right_tv_sub);
        mRightParent = (FrameLayout) title.findViewById(R.id.right_parent);
        mCustomLayer = (RelativeLayout) title.findViewById(R.id.custom_layer);
        mToolbar = (Toolbar) title.findViewById(R.id.tool_bar);
        mToolbarRoot = (AppBarLayout) title.findViewById(R.id.toolbar_root);
        mLeftTitle = (TextView) title.findViewById(R.id.tv_left_title);

        mLeftParent.setOnClickListener(this);
        mCenterParent.setOnClickListener(this);
        mRightTvMain.setOnClickListener(this);
        mRightTvSub.setOnClickListener(this);

    }

    private void setOnViewTouchListener() {
        mLeftParent.setOnTouchListener(this);
        mCenterParent.setOnTouchListener(this);
        mRightTvMain.setOnTouchListener(this);
        mRightTvSub.setOnTouchListener(this);
    }

    public ImageView setLeftIcon(@DrawableRes int resId) {
        mLeftIv.setImageResource(resId);
        if (mLeftIv.getVisibility() != VISIBLE) {
            mLeftIv.setVisibility(VISIBLE);
        }
        return mLeftIv;
    }

    public TextView setLeftText(CharSequence text) {
        mLeftTv.setText(text);
        if (mLeftTv.getVisibility() != VISIBLE) {
            mLeftTv.setVisibility(VISIBLE);
        }
        return mLeftTv;
    }

    public TextView setLeftTitle(CharSequence text) {
        mLeftTitle.setText(text);
        if (mLeftTitle.getVisibility() != VISIBLE) {
            mLeftTitle.setVisibility(VISIBLE);
        }
        return mLeftTitle;
    }

    public TextView setCenterText(CharSequence text) {
        mCenterTv.setText(text);
        if (mCenterTv.getVisibility() != VISIBLE) {
            mCenterTv.setVisibility(VISIBLE);
        }
        return mCenterTv;
    }

    public TextView setRightText(CharSequence text) {
        mRightTvMain.setText(text);
        if (mRightTvMain.getVisibility() != VISIBLE) {
            mRightTvMain.setVisibility(VISIBLE);
        }
        return mRightTvMain;
    }

    public TextView setRightSubText(CharSequence text) {
        mRightTvSub.setText(text);
        if (mRightTvSub.getVisibility() != VISIBLE) {
            mRightTvSub.setVisibility(VISIBLE);
        }
        return mRightTvSub;
    }

    public void setRightIcon(@DrawableRes int resId) {
        if (mRightTvMain.getVisibility() != VISIBLE) {
            mRightTvMain.setVisibility(VISIBLE);
        }
        Drawable rightIcon = getResources().getDrawable(resId);
        mRightTvMain.setCompoundDrawablesWithIntrinsicBounds(rightIcon, null, null, null);
    }

    public void setRightSubIcon(@DrawableRes int resId) {
        if (mRightTvSub.getVisibility() != VISIBLE) {
            mRightTvSub.setVisibility(VISIBLE);
        }
        Drawable rightSubIcon = getResources().getDrawable(resId);
        mRightTvSub.setCompoundDrawablesWithIntrinsicBounds(rightSubIcon, null, null, null);
    }

    private SparseArray<View> views = new SparseArray<>();

    public <T> T findView(int id) {
        View v = views.get(id);
        if (v == null) {
            v = this.findViewById(id);
            views.put(id, v);
        }
        if (v.getVisibility() != VISIBLE) {
            v.setVisibility(VISIBLE);
        }
        return (T) v;
    }

    public TextView findTextView(int id) {
        return findView(id);
    }

    public ImageView findImageView(int id) {
        return findView(id);
    }

    public interface OnToolBarClickListener {
        /**
         * 左侧区域（Icon或文字区域）
         *
         * @param view
         */
        void onTitleLeftClick(View view);

        /**
         * 中间文字区域
         *
         * @param view
         */
        void onTitleCenterClick(View view);

        /**
         * 右侧主区域（靠近右侧）
         *
         * @param view
         */
        void onTitleRightClick(View view);

        /**
         * 右侧副区域（原理右侧）
         *
         * @param view
         */
        void onTitleRightSubClick(View view);

    }


    public void setOnCustomToolBarClickListener(OnToolBarClickListener onToolBarClickListener) {
        mOnToolBarClickListener = onToolBarClickListener;
    }

    private OnToolBarClickListener mOnToolBarClickListener;


    public void onClick(View view) {
        if (mOnToolBarClickListener != null) {
            int i = view.getId();
            if (i == R.id.left_parent) {
                mOnToolBarClickListener.onTitleLeftClick(view);

            } else if (i == R.id.center_parent) {
                mOnToolBarClickListener.onTitleCenterClick(view);

            } else if (i == R.id.right_tv_main) {
                mOnToolBarClickListener.onTitleRightClick(view);

            } else if (i == R.id.right_tv_sub) {
                mOnToolBarClickListener.onTitleRightSubClick(view);

            }

        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            v.setAlpha(mAlpha);
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            v.setAlpha(1.0f);
        }
        return false;
    }
}
