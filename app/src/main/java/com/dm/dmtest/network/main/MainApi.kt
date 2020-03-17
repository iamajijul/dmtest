package com.dm.dmtest.network.main

import com.dm.dmtest.models.repo_details.RepoDetails
import com.dm.dmtest.models.repo_list.RepoListItemModel
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MainApi {

    @GET("users/{username}/repos")
    fun getRepoList(@Path("username") userName: String): Flowable<List<RepoListItemModel?>>

    @GET("repos/{username}/{reponame}")
    fun getRepoDetails(@Path("username") userName: String,@Path("reponame") repoName: String)
            : Flowable<RepoDetails?>
}