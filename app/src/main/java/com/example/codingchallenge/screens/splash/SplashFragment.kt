package com.example.codingchallenge.screens.splash

import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.codingchallenge.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {
    private lateinit var binding: FragmentSplashBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentSplashBinding.inflate(inflater)

        val viewModel = ViewModelProvider(this)[SplashViewModel::class.java]

        viewModel.remainingTime.observe(viewLifecycleOwner) {
            if (it!=0L) binding.tick.text = viewModel.remainingTime.value.toString()
            else this.findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToReposFragment())
        }

        return binding.root
    }

}