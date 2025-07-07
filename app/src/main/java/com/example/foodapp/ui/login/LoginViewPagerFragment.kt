package com.example.foodapp.ui.login

import android.content.res.Resources
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.foodapp.databinding.FragmentLoginViewPagerBinding
import com.example.foodapp.ui.adapters.LoginViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator


class LoginViewPagerFragment : Fragment() {
    lateinit var binding: FragmentLoginViewPagerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginViewPagerBinding.inflate(layoutInflater, container, false)
        binding.viewpagerLogin.adapter = LoginViewPagerAdapter(this)
        TabLayoutMediator(binding.tabLayout, binding.viewpagerLogin) { tab, index ->
            tab.text = when (index) {
                0 -> "Create Account"
                1 -> "Login"
                else -> throw Resources.NotFoundException("Not found")
            }
        }.attach()
        return binding.root
    }
}