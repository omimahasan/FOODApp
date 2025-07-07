package com.example.foodapp.ui.onboarding

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.foodapp.R
import com.example.foodapp.data.Repository.OnboardingRepository
import com.example.foodapp.databinding.FragmentFirstOnboardBinding
import com.example.foodapp.viewmodel.onboarding.OnboardingViewModel
import com.example.foodapp.viewmodel.onboarding.OnboardingViewModelFactory

class FirstOnboardFragment : Fragment() {

    private lateinit var binding: FragmentFirstOnboardBinding
    private lateinit var viewModel: OnboardingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFirstOnboardBinding.inflate(inflater, container, false)

        val repository = OnboardingRepository(requireContext())
        val factory = OnboardingViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[OnboardingViewModel::class.java]

        val viewPager = activity?.findViewById<ViewPager2>(R.id.view_pager)

        binding.nextBut1.setOnClickListener {
            viewPager?.currentItem = 1
        }

        binding.skip.setOnClickListener {
            findNavController().navigate(R.id.action_onBoardFragment_to_loginFragment)
            saveOnBoardingFinished()
        }

        return binding.root
    }

    private fun saveOnBoardingFinished() {
        val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        sharedPref.edit().putBoolean("finished", true).apply()
    }
}
