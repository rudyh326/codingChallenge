package com.example.codingchallenge.screens.repos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.codingchallenge.databinding.GridViewItemBinding
import com.example.codingchallenge.domain.Repo

class RepoGridAdapter(val onClickListener: OnClickListener) : ListAdapter<Repo, RepoGridAdapter.RepoPropertyViewHolder>(
    DiffCallback
) {

    class RepoPropertyViewHolder(private var binding: GridViewItemBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(repoProperty: Repo) {
            binding.property = repoProperty
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Repo>() {
        override fun areItemsTheSame(oldItem: Repo, newItem: Repo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Repo, newItem: Repo): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoPropertyViewHolder {
        return RepoPropertyViewHolder(GridViewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: RepoPropertyViewHolder, position: Int) {
        val repoProperty = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(repoProperty)
        }
        holder.bind(repoProperty)
    }

    class OnClickListener(val clickListener: (repoProperty: Repo) -> Unit) {
        fun onClick(repoProperty: Repo) = clickListener(repoProperty)
    }

}

