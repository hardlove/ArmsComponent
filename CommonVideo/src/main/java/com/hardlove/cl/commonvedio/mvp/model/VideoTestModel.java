package com.hardlove.cl.commonvedio.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.hardlove.cl.commonvedio.mvp.contract.VideoTestContract;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import javax.inject.Inject;


@ActivityScope
public class VideoTestModel extends BaseModel implements VideoTestContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public VideoTestModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }
}