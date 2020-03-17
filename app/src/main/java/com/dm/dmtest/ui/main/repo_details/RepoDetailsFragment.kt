package com.dm.dmtest.ui.main.repo_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dm.dmtest.R
import com.dm.dmtest.models.repo_details.RepoDetails
import com.dm.dmtest.ui.main.SharedViewModel
import com.dm.dmtest.ui.main.Resource
import com.dm.dmtest.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_repo_details.*
import kotlinx.android.synthetic.main.fragment_repo_details.progress_bar
import javax.inject.Inject

class RepoDetailsFragment : DaggerFragment() {

    lateinit var sharedViewModel: SharedViewModel
    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_repo_details, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        sharedViewModel =
            ViewModelProvider(activity!!, viewModelProviderFactory).get(SharedViewModel::class.java)
        subscribeToObserver()


    }

    private fun subscribeToObserver() {

        sharedViewModel.getRepoDetails()?.observe(viewLifecycleOwner, Observer {
            if (it != null) {

                if (it != null) {
                    when (it.status) {
                        Resource.Status.SUCCESS -> {
                            setRepoDetails(it.data)
                            handleProgress(false)

                        }
                        Resource.Status.ERROR -> {
                            setErrorData(it.message)
                            handleProgress(false)

                        }
                        Resource.Status.LOADING -> {
                            handleProgress(true)

                        }
                    }
                }
            }
        })
    }

    private fun setErrorData(message: String?) {
        fullname.text = message
        name.text = message
        forkUrl.text = message

    }

    private fun setRepoDetails(data: RepoDetails?) {
        fullname.text = data?.full_name
        name.text = data?.name
        forkUrl.text = data?.forks_url

    }

    private fun handleProgress(isShow: Boolean) {
        if (isShow) {
            progress_bar.visibility = View.VISIBLE
        } else {
            progress_bar.visibility = View.GONE
        }
    }
}