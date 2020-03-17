package com.dm.dmtest.di.main

import androidx.lifecycle.ViewModel
import com.dm.dmtest.di.ViewModelKey
import com.dm.dmtest.ui.main.SharedViewModel
import com.dm.dmtest.ui.main.repo_list.RepoListViewModel
import com.dm.dmtest.ui.main.repo_details.RepoDetailsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(RepoDetailsViewModel::class)
    abstract fun bindRepoViewModel(repoListViewModel: RepoDetailsViewModel):ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RepoListViewModel::class)
    abstract fun bindRepoDetailsViewModel(repoDetailsViewModel: RepoListViewModel):ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SharedViewModel::class)
    abstract fun bindMainViewModel(sharedViewModel: SharedViewModel):ViewModel
}