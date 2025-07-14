package com.example.foodapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.foodapp.R
import com.example.foodapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. NavController
        val navHostFragment = childFragmentManager
            .findFragmentById(R.id.home_fragment_host) as NavHostFragment
        val navController = navHostFragment.navController

        // 2. Toolbar setup
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)

        // 3. AppBarConfiguration with DrawerLayout
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.homeMainFragment,
                R.id.profileFragment,
                R.id.cartFragment,
                R.id.chatFragment
            ),
            binding.drawerLayout
        )

        // 4. Link toolbar with nav controller
        NavigationUI.setupWithNavController(binding.toolbar, navController, appBarConfiguration)
        navController.addOnDestinationChangedListener { _, _, _ ->
            binding.toolbar.navigationIcon?.setTint(ContextCompat.getColor(requireContext(), R.color.black))
        }



        // 5. Link BottomNav with nav controller
        NavigationUI.setupWithNavController(binding.btmNav, navController)

        // 6. Link NavDrawer with nav controller
        NavigationUI.setupWithNavController(binding.navView, navController)
    }
}
