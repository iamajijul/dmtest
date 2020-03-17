package com.dm.dmtest.ui.main

import androidx.lifecycle.LiveData
import com.dm.dmtest.models.repo_details.RepoDetails

interface RepoDetailsListener {
    fun onRepoDetailsFetch(userName : String, repoName :String):  LiveData<Resource<RepoDetails?>?>?

}