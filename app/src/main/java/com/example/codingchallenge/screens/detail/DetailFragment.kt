package com.example.codingchallenge.screens.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.codingchallenge.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentDetailBinding.inflate(inflater)

        val application = requireNotNull(activity).application
        binding.lifecycleOwner = this

        val parcelableRepo = DetailFragmentArgs.fromBundle(requireArguments()).selectedProperty

        val viewModelFactory = DetailViewModelFactory(parcelableRepo, application)

        val viewModel = ViewModelProvider(this, viewModelFactory)[DetailViewModel::class.java]

        binding.viewModel = viewModel

        viewModel.selectedProperty.observe(viewLifecycleOwner) { detail ->
            if (detail != null) (activity as AppCompatActivity).supportActionBar?.title =
                "Repository '" + detail.name + "' Details"
        }

        return binding.root
    }
}