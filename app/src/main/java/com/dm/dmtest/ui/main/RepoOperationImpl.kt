package com.dm.dmtest.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import com.dm.dmtest.models.repo_details.RepoDetails
import com.dm.dmtest.models.repo_list.RepoListItemModel
import com.dm.dmtest.network.main.MainApi
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RepoOperationImpl @Inject constructor(var mainApi: MainApi) : RepoDetailsListener,
    RepoListListener {

    override fun onRepoDetailsFetch(userName : String, repoName :String): LiveData<Resource<RepoDetails?>?> {

        return LiveDataReactiveStreams.fromPublisher(
            mainApi.getRepoDetails(
                userName,
                repoName
            )
                .onErrorReturn(object : Function<Throwable, RepoDetails?> {
                    override fun apply(t: Throwable?): RepoDetails? {
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

    }

    override fun onGetRepoList(): LiveData<Resource<List<RepoListItemModel?>?>?> {

        return LiveDataReactiveStreams.fromPublisher(
            mainApi.getRepoList("christie1phl")
                .onErrorReturn(object : Function<Throwable, List<RepoListItemModel?>> {
                    override fun apply(t: Throwable?): List<RepoListItemModel?> {
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
    }
}