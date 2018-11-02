package com.hardlove.cl.commonvedio.di.module;

import com.hardlove.cl.commonvedio.mvp.contract.VideoTestContract;
import com.hardlove.cl.commonvedio.mvp.model.VideoTestModel;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;


@Module
public class VideoTestModule {
    private VideoTestContract.View view;

    /**
     * 构建VideoTestModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public VideoTestModule(VideoTestContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    VideoTestContract.View provideVideoTestView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    VideoTestContract.Model provideVideoTestModel(VideoTestModel model) {
        return model;
    }
}