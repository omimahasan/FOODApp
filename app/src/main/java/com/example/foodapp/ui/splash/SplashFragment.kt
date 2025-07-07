package com.example.foodapp.ui.splash

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.foodapp.R
import com.example.foodapp.databinding.FragmentSplashBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment : Fragment() {

    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Start rotate animation on the image
        val rotateAnim = AnimationUtils.loadAnimation(requireContext(), R.anim.rotate)
        binding.animatedImgSplash.startAnimation(rotateAnim)

        lifecycleScope.launch {
            delay(1000)
            val slideInAnim = AnimationUtils.loadAnimation(requireContext(), R.anim.slide)
            binding.tvSplash.startAnimation(slideInAnim)

            delay(1000)

            if (onBoardingIsFinished()) {
                // Navigate to Home if onboarding is already done
                findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
            } else {
                // Navigate to OnBoard screen
                findNavController().navigate(R.id.action_splashFragment_to_onBoardFragment)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onBoardingIsFinished(): Boolean {
        val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        return sharedPref.getBoolean("finished", false)
    }
}