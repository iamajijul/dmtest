package com.dm.dmtest.di.main

import com.dm.dmtest.ui.main.repo_list.RepoListFragment
import com.dm.dmtest.ui.main.repo_details.RepoDetailsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun connectRepoListFragment(): RepoDetailsFragment

    @ContributesAndroidInjector
    abstract fun connectRepoDetailsFragment(): RepoListFragment
}