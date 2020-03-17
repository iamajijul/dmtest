package com.dm.dmtest.ui.main.repo_details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.dm.dmtest.models.repo_details.RepoDetails
import com.dm.dmtest.network.main.MainApi
import com.dm.dmtest.ui.main.SharedViewModel
import com.dm.dmtest.ui.main.Resource
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

//Not in Use
class RepoDetailsViewModel @Inject constructor(var mainApi: MainApi) : ViewModel() {
    private var repos: MediatorLiveData<Resource<RepoDetails?>>? = null

    val TAG = "RepoDetailsViewModel"

    init {

        Log.d(TAG, "Repo Details View Model Injected")
    }

    fun getRepoDetails(userName:String,repoName : String): LiveData<Resource<RepoDetails?>>? {
        if (repos == null) {
            repos = MediatorLiveData()
            repos?.value = Resource.loading(null as RepoDetails?)
            val source = LiveDataReactiveStreams.fromPublisher(
                mainApi.getRepoDetails(userName,repoName)
                    .onErrorReturn(object : Function<Throwable, RepoDetails?> {
                        override fun apply(t: Throwable?): RepoDetails? {
                            Log.e(TAG, "Apply :$t")
                            return null
                        }

                    })
                    .map(object :
                        Function<RepoDetails?, Resource<RepoDetails?>> {
                        override fun apply(it: RepoDetails?): Resource<RepoDetails?>? {
                            if (it == null) {
                                return Resource.error(
                                    "Something went worng",
                                    null as RepoDetails
                                )
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
}