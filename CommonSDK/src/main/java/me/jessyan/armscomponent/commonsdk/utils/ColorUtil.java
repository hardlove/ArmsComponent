package me.jessyan.armscomponent.commonsdk.utils;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.v4.graphics.drawable.DrawableCompat;

/**
 * Created by Chenlu on 2018/12/26
 * Email:chenlu@globalroam.com
 */
public class ColorUtil {
    /**
     * for example: imageView.setImageDrawable(states);
     * @param selectedDrawable
     * @param unSelectedDrawable
     * @return
     */
    public static StateListDrawable createStateListDrawable(Drawable selectedDrawable, Drawable unSelectedDrawable) {
        StateListDrawable states = new StateListDrawable();
        states.addState(new int[]{android.R.attr.state_selected},
                selectedDrawable);
        states.addState(new int[]{-android.R.attr.state_selected},
                unSelectedDrawable);
        states.addState(new int[]{},
                unSelectedDrawable);
        return states;
    }

    /**
     * Specifies a tint for {@code drawable} as a color state list
     * for example: imageView.setImageDrawable(states);
     * @param targetDrawable
     * @param selectedColor
     * @param unSelectedColor
     * @return
     */
    public static Drawable createTintListForDrawable(@NonNull Drawable targetDrawable, @ColorInt int selectedColor, @ColorInt int unSelectedColor) {
        DrawableCompat.setTintList(targetDrawable, new ColorStateList(
                new int[][]{
                        new int[]{android.R.attr.state_selected}, //1
                        new int[]{-android.R.attr.state_selected}, //2
                        new int[]{}
                },
                new int[]{
                        selectedColor, //1
                        unSelectedColor, //2
                        unSelectedColor //3
                }
        ));
        return targetDrawable;
    }
}
