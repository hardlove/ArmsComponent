package com.hardlove.cl.water.app;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

/**
 * Created by Chenlu on 2018/12/9
 * Email:chenlu@globalroam.com
 */
public class AcitivityLifecyclesImpl implements Application.ActivityLifecycleCallbacks {
    public AcitivityLifecyclesImpl() {
        super();
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }
}
