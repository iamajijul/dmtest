package com.dm.dmtest.ui.main

import androidx.lifecycle.LiveData
import com.dm.dmtest.models.repo_details.RepoDetails
import com.dm.dmtest.models.repo_list.RepoListItemModel

interface RepoListListener {

    fun onGetRepoList(): LiveData<Resource<List<RepoListItemModel?>?>?>
}