package com.dm.dmtest.di

import com.dm.dmtest.di.main.MainFragmentBuildersModule
import com.dm.dmtest.di.main.MainModule
import com.dm.dmtest.di.main.MainScope
import com.dm.dmtest.di.main.MainViewModelsModule
import com.dm.dmtest.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @MainScope
    @ContributesAndroidInjector(
        modules = [MainFragmentBuildersModule::class,
            MainViewModelsModule::class, MainModule::class]
    )
    abstract fun connectMainActivity(): MainActivity

}