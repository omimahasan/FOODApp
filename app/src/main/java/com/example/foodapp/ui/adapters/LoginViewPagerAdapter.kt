package com.example.foodapp.ui.adapters

import android.content.res.Resources
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.foodapp.ui.login.LoginViewPagerFragment
import com.example.foodapp.ui.login.SignInFragment
import com.example.foodapp.ui.login.SignUpFragment

class LoginViewPagerAdapter (fragmentActivity: LoginViewPagerFragment): FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount()=2

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> SignUpFragment()
            1 -> SignInFragment()
            else-> throw Resources.NotFoundException("Position is NOT found")
        }
    }
}