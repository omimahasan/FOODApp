package com.example.foodapp.ui.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.foodapp.databinding.FragmentOnBoardBinding
import com.example.foodapp.ui.adapters.OnboardingViewPagerAdapter
import com.example.foodapp.ui.animations.DepthPageTransformer
import com.example.foodapp.viewmodel.onboarding.OnboardingViewModel

class OnBoardFragment : Fragment() {

    private lateinit var binding: FragmentOnBoardBinding
    private val viewModel: OnboardingViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnBoardBinding.inflate(inflater, container, false)

        val fragmentList = arrayListOf(
            FirstOnboardFragment(),
            SecondOnboardFragment(),
            ThirdOnboardFragment()
        )

        val adapter = OnboardingViewPagerAdapter(this,
            fragmentList
        )

        binding.viewPager.adapter = adapter
        binding.dotsIndicator.attachTo(binding.viewPager)
        binding.viewPager.setPageTransformer(DepthPageTransformer())

        return binding.root
    }
}
