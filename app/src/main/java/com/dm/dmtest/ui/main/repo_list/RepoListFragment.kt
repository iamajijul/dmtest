package com.dm.dmtest.ui.main.repo_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dm.dmtest.R
import com.dm.dmtest.ui.main.MainActivity
import com.dm.dmtest.ui.main.RepoNavigator
import com.dm.dmtest.ui.main.Resource
import com.dm.dmtest.ui.main.SharedViewModel
import com.dm.dmtest.utils.VerticalSpacingItemDecoration
import com.dm.dmtest.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_repo_list.*
import javax.inject.Inject

class RepoListFragment : DaggerFragment(), RepoNavigator {

    lateinit var sharedViewModel: SharedViewModel
    val TAG = "RepoList FRAGMENT"

    @Inject
    lateinit var adapter: ReposRecyclerViewAdapter
    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_repo_list, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        sharedViewModel =
            ViewModelProvider(activity!!, viewModelProviderFactory).get(SharedViewModel::class.java)
        subscribeObserver()
        initRecyclerView()
    }

    private fun initRecyclerView() {

        recycler_view.layoutManager = LinearLayoutManager(activity)
        recycler_view.addItemDecoration(VerticalSpacingItemDecoration(12))
        adapter.setViewModel(sharedViewModel)
        recycler_view.adapter = adapter
    }

    private fun subscribeObserver() {
        sharedViewModel.observeRepos()?.observe(viewLifecycleOwner, Observer {

            if (it != null) {
                when (it.status) {
                    Resource.Status.SUCCESS -> {
                        adapter.setPosts(it.data ?: ArrayList())
                        handleProgress(false)

                    }
                    Resource.Status.ERROR -> {
                        handleProgress(false)

                    }
                    Resource.Status.LOADING -> {
                        handleProgress(true)
                    }
                }
            }
        })

        sharedViewModel.onItemClickObserver?.observe(viewLifecycleOwner, Observer {

            it.getEventIfNotHandled()?.let {
                (activity as MainActivity).itemClicked()
            }
        })
    }

    private fun handleProgress(isShow: Boolean) {
        if (isShow) {
            progress_bar.visibility = View.VISIBLE
        } else {
            progress_bar.visibility = View.GONE
        }
    }

    override fun onItemClick() {
        (activity as MainActivity).itemClicked()
    }
}