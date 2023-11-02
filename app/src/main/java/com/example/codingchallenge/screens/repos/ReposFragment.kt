package com.example.codingchallenge.screens.repos

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.codingchallenge.databinding.FragmentReposBinding

class ReposFragment : Fragment() {
    private lateinit var binding: FragmentReposBinding

    private val viewModel: ReposViewModel by lazy {
        val activity = requireNotNull(this.activity)
        ViewModelProvider(this,ReposViewModelFactory(activity.application))[ReposViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentReposBinding.inflate(inflater)

        binding.root.setOnClickListener {
            hideKeyboard(binding.searchView)
        }

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        binding.repoGrid.adapter = RepoGridAdapter(RepoGridAdapter.OnClickListener {
            hideKeyboard(binding.searchView)
            viewModel.displayPropertyDetails(it)
        })

        viewModel.navigateToSelectedProperty.observe(viewLifecycleOwner) {
            if (null != it) {
                this.findNavController().navigate(ReposFragmentDirections.actionReposFragmentToDetailFragment(it))
                viewModel.displayPropertyDetailsComplete()
            }
        }

        binding.repoGrid.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as GridLayoutManager
                val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
                val totalItemCount = layoutManager.itemCount

                if (lastVisibleItemPosition == totalItemCount - 1 && viewModel.moreItems) {
                    viewModel.page++
                    viewModel.refreshRepos(viewModel.page)
                }
            }
        })

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.performSearch(query)
                hideKeyboard(binding.searchView)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                viewModel.performSearch(newText)
                return true
            }
        })

        return binding.root
    }

    private fun hideKeyboard(view: View) {
        val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

}