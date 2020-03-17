package com.dm.dmtest.ui.main

import android.util.Log
import androidx.lifecycle.*
import com.dm.dmtest.models.repo_details.RepoDetails
import com.dm.dmtest.models.repo_list.RepoListItemModel
import com.dm.dmtest.network.main.MainApi
import com.dm.dmtest.utils.Event
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SharedViewModel @Inject constructor(var repository : RepoOperationImpl) : ViewModel() {
    val TAG = "SharedViewModel"
    private var repoDetails: MediatorLiveData<Resource<RepoDetails?>?>? = null
    private var reposList: MediatorLiveData<Resource<List<RepoListItemModel?>?>>? = null
    private var _onItemClickObserver: MutableLiveData<Event<RepoListItemModel>>? = MutableLiveData()

    val onItemClickObserver: LiveData<Event<RepoListItemModel>>?
        get() = _onItemClickObserver

    init {
        Log.i(TAG, "Main View Model")
    }


    fun observeRepos(): LiveData<Resource<List<RepoListItemModel?>?>>? {

        if (reposList == null) {
            reposList = MediatorLiveData()
            reposList?.value = Resource.loading(null as List<RepoListItemModel>?)

            var source = repository.onGetRepoList()
            reposList?.addSource(
                source
            ) {
                reposList?.value = it
                reposList?.removeSource(source)
            }

        }

        return reposList
    }




    fun onItemClick(listItem: RepoListItemModel) {

        _onItemClickObserver?.value = Event(listItem)
    }


    fun getRepoDetails(): LiveData<Resource<RepoDetails?>?>? {
        // if (repos == null)
        repoDetails = MediatorLiveData()
        repoDetails?.value = Resource.loading(null as RepoDetails?)

        val source = repository.onRepoDetailsFetch(onItemClickObserver?.value?.peekContent()?.owner?.login ?: return null,onItemClickObserver?.value?.peekContent()?.name ?: return null)
        repoDetails?.addSource(
            source
        ) {
            repoDetails?.value = it
            repoDetails?.removeSource(source)
        }

//        onItemClickObserver?.value=null

        return repoDetails
    }
}