package com.example.foodapp.ui.login

import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.foodapp.databinding.FragmentLoginViewPagerBinding
import com.example.foodapp.ui.adapters.LoginViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class LoginViewPagerFragment : Fragment() {

    private lateinit var binding: FragmentLoginViewPagerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginViewPagerBinding.inflate(inflater, container, false)
        binding.viewpagerLogin.adapter = LoginViewPagerAdapter(this)

        TabLayoutMediator(binding.tabLayout, binding.viewpagerLogin) { tab, index ->
            tab.text = when (index) {
                0 -> "Create Account"
                1 -> "Login"
                else -> throw Resources.NotFoundException("Tab not found")
            }
        }.attach()

        return binding.root
    }
}

