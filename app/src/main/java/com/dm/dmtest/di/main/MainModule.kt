package com.dm.dmtest.di.main

import com.dm.dmtest.network.main.MainApi
import com.dm.dmtest.ui.main.RepoOperationImpl
import com.dm.dmtest.ui.main.repo_list.ReposRecyclerViewAdapter
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit


@Module
class MainModule {

    @MainScope
    @Provides
    fun provideRepoListAdapter (): ReposRecyclerViewAdapter {
        return ReposRecyclerViewAdapter()
    }

    @MainScope
    @Provides
    fun provideMainApi(retrofit: Retrofit): MainApi {
        return retrofit.create(MainApi::class.java)
    }

    @MainScope
    @Provides
    fun provideMainRepository(mainApi: MainApi): RepoOperationImpl {
        return RepoOperationImpl(mainApi)
    }
}