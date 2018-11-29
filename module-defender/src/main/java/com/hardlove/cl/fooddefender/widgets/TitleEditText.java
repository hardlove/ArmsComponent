package com.hardlove.cl.fooddefender.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hardlove.cl.fooddefender.R;


/**
 * Created by CL on 2017/8/19.
 */

public class TitleEditText extends LinearLayout {

    private TextView tvTitle;
    private int titleSize = 30;//标题文字大小
    private int gaps = 20;
    private String title;
    private int normalColor = Color.parseColor("#FF0000");
    private int activeColor = Color.parseColor("#00FF00");
    private String hint;
    private AppCompatEditText editText;


    public TitleEditText(Context context) {
        super(context);
        init(context, null);
    }


    public TitleEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public TitleEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        addChilds();
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TitleEditText);
        titleSize = typedArray.getDimensionPixelSize(R.styleable.TitleEditText_mTitleSize, titleSize);
        title = typedArray.getString(R.styleable.TitleEditText_mTitle);
        hint = typedArray.getString(R.styleable.TitleEditText_hint);
        normalColor = typedArray.getColor(R.styleable.TitleEditText_titleNormalColor,normalColor);
        activeColor = typedArray.getColor(R.styleable.TitleEditText_titleActiveColor,activeColor);

        tvTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, titleSize);
        tvTitle.setText(title);
        tvTitle.setTextColor(normalColor);

        editText.setHint(hint);




    }

    private void addChilds() {
        setOrientation(VERTICAL);
        tvTitle = new TextView(getContext());
        LinearLayoutCompat.LayoutParams param = new LinearLayoutCompat.LayoutParams(LinearLayoutCompat.LayoutParams.WRAP_CONTENT, LinearLayoutCompat.LayoutParams.WRAP_CONTENT);
        addView(tvTitle, param);

        editText = new AppCompatEditText(getContext());
        LinearLayoutCompat.LayoutParams param2 = new LinearLayoutCompat.LayoutParams(LinearLayoutCompat.LayoutParams.MATCH_PARENT, LinearLayoutCompat.LayoutParams.WRAP_CONTENT);
        addView(editText, param2);
        param2.topMargin = gaps;

    }
}
