package com.hardlove.cl.commonvedio.di.component;

import com.hardlove.cl.commonvedio.di.module.VideoTestModule;
import com.hardlove.cl.commonvedio.mvp.ui.activity.VideoTestActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(modules = VideoTestModule.class, dependencies = AppComponent.class)
public interface VideoTestComponent {
    void inject(VideoTestActivity activity);
}