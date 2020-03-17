package com.dm.dmtest.ui.main.repo_list

import android.util.Log
import androidx.lifecycle.*
import com.dm.dmtest.models.repo_details.RepoDetails
import com.dm.dmtest.models.repo_list.RepoListItemModel
import com.dm.dmtest.network.main.MainApi
import com.dm.dmtest.ui.main.RepoNavigator
import com.dm.dmtest.ui.main.Resource
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RepoListViewModel @Inject constructor(var mainApi: MainApi) :
    ViewModel() {

    private var repos: MediatorLiveData<Resource<List<RepoListItemModel?>?>>? = null
    val TAG = "RepoList ViewModel"


    init {
        Log.d(TAG, "RepoList ViewModel")
    }

    private var onItemClickObserver: MutableLiveData<RepoListItemModel> = MutableLiveData()
    fun observeRepos(): LiveData<Resource<List<RepoListItemModel?>?>>? {

        if (repos == null) {
            repos = MediatorLiveData()
            repos?.value = Resource.loading(null as List<RepoListItemModel>?)
            val source = LiveDataReactiveStreams.fromPublisher(
                mainApi.getRepoList("christie1phl")
                    .onErrorReturn(object : Function<Throwable, List<RepoListItemModel?>> {
                        override fun apply(t: Throwable?): List<RepoListItemModel?> {
                            Log.e(TAG, "Apply :$t")
                            val failedPosts = ArrayList<RepoListItemModel?>()
                            failedPosts.add(null)
                            return failedPosts
                        }

                    })
                    .map(object :
                        Function<List<RepoListItemModel?>, Resource<List<RepoListItemModel?>?>?> {
                        override fun apply(it: List<RepoListItemModel?>?): Resource<List<RepoListItemModel?>?>? {
                            if (it?.isNotEmpty() == true) {
                                if (it.get(0) == null) {
                                    return Resource.error(
                                        "Something went worng",
                                        null as List<RepoListItemModel>?
                                    )
                                }
                            }

                            return Resource.success(it)
                        }

                    })
                    .subscribeOn(Schedulers.io())
            )
            repos?.addSource(
                source
            ) {
                repos?.value = it
                repos?.removeSource(source)
            }

        }

        return repos
    }

    fun getOnItemClickObserver():LiveData<RepoListItemModel>?{
        return onItemClickObserver
    }

    fun onItemClick(listItem: RepoListItemModel) {
        onItemClickObserver.value = listItem
    }

}