package com.example.foodapp.ui.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.foodapp.R
import com.example.foodapp.data.Repository.OnboardingRepository
import com.example.foodapp.databinding.FragmentSecondOnboardBinding
import com.example.foodapp.viewmodel.onboarding.OnboardingViewModel
import com.example.foodapp.viewmodel.onboarding.OnboardingViewModelFactory

class SecondOnboardFragment : Fragment() {

    private lateinit var binding: FragmentSecondOnboardBinding
    private lateinit var viewModel: OnboardingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSecondOnboardBinding.inflate(inflater, container, false)

        val repository = OnboardingRepository(requireContext())
        val factory = OnboardingViewModelFactory(repository)
        viewModel = ViewModelProvider(requireActivity(), factory)[OnboardingViewModel::class.java]

        val viewPager = activity?.findViewById<ViewPager2>(R.id.view_pager)

        binding.nextBut2.setOnClickListener {
            viewPager?.currentItem = viewModel.onNextClicked(1)
        }

        binding.back1.setOnClickListener {
            viewPager?.currentItem = viewModel.onBackClicked(1)
        }

        binding.skip2.setOnClickListener {
            viewModel.onSkipClicked()
        }

        viewModel.navigateToLogin.observe(viewLifecycleOwner) {
            if (it) {
                findNavController().navigate(R.id.action_onBoardFragment_to_loginFragment)
                viewModel.doneNavigating()
            }
        }

        return binding.root
    }
}
