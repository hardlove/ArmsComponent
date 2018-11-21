package com.hardlove.cl.im.di.component;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.hardlove.cl.im.di.module.ConversionListModule;

import com.jess.arms.di.scope.ActivityScope;
import com.hardlove.cl.im.mvp.ui.activity.ConversationListActivity;

@ActivityScope
@Component(modules = ConversionListModule.class, dependencies = AppComponent.class)
public interface ConversionListComponent {
    void inject(ConversationListActivity activity);
}