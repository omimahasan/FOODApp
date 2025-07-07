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
import com.example.foodapp.databinding.FragmentThirdOnboardBinding
import com.example.foodapp.viewmodel.onboarding.OnboardingViewModel
import com.example.foodapp.viewmodel.onboarding.OnboardingViewModelFactory

class ThirdOnboardFragment : Fragment() {

    private lateinit var binding: FragmentThirdOnboardBinding
    private lateinit var viewModel: OnboardingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentThirdOnboardBinding.inflate(inflater, container, false)

        val repository = OnboardingRepository(requireContext())
        val factory = OnboardingViewModelFactory(repository)
        viewModel = ViewModelProvider(requireActivity(), factory)[OnboardingViewModel::class.java]

        val viewPager = activity?.findViewById<ViewPager2>(R.id.view_pager)

        binding.back1.setOnClickListener {
            viewPager?.currentItem = viewModel.onBackClicked(2)
        }

        binding.nextBut3.setOnClickListener {
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
