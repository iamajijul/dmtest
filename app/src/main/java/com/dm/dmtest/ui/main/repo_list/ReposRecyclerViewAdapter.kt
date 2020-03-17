package com.dm.dmtest.ui.main.repo_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dm.dmtest.BR
import com.dm.dmtest.databinding.LayoutRepoListItemBinding
import com.dm.dmtest.models.repo_list.RepoListItemModel
import com.dm.dmtest.ui.main.SharedViewModel
import java.util.*


class ReposRecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var posts: List<RepoListItemModel?> = ArrayList<RepoListItemModel>()
    private var viewModel : SharedViewModel? = null

    fun setViewModel(viewModel : SharedViewModel){
        this.viewModel = viewModel
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder = RepoViewHolder(LayoutRepoListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))


    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        (holder as RepoViewHolder).bind(posts[position]?:return)
    }

    override fun getItemCount(): Int =  posts.size


    fun setPosts(posts: List<RepoListItemModel?>) {
        this.posts = posts
        notifyDataSetChanged()
    }

    inner class RepoViewHolder(var binding: LayoutRepoListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(repo: RepoListItemModel) {
            binding.setVariable(BR.data,repo)
            binding.setVariable(BR.viewModel,viewModel)
            binding.executePendingBindings()
        }

    }
}