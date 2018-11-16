package com.hardlove.cl.im.di.component;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.hardlove.cl.im.di.module.IMModule;

import com.jess.arms.di.scope.ActivityScope;
import com.hardlove.cl.im.mvp.ui.activity.IMActivity;

@ActivityScope
@Component(modules = IMModule.class, dependencies = AppComponent.class)
public interface IMComponent {
    void inject(IMActivity activity);
}