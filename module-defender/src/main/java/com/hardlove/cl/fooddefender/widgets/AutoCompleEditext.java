package com.hardlove.cl.fooddefender.widgets;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Created by Chenlu on 2017/11/10
 */

public class AutoCompleEditext extends android.support.v7.widget.AppCompatAutoCompleteTextView {

    public AutoCompleEditext(Context context) {
        super(context);
    }

    public AutoCompleEditext(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AutoCompleEditext(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean enoughToFilter() {
        return true;
    }
}
