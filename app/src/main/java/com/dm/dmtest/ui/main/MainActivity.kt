package com.dm.dmtest.ui.main

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.dm.dmtest.R
import com.dm.dmtest.BaseActivity
import com.dm.dmtest.models.repo_details.RepoDetails
import com.dm.dmtest.models.repo_list.RepoListItemModel
import com.dm.dmtest.ui.main.repo_list.RepoListViewModel

class MainActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun itemClicked() {
        Navigation.findNavController(this, R.id.nav_host_fragment)
            .navigate(R.id.repo_details_screen)

    }

}