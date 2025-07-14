package com.example.foodapp.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.foodapp.R
import com.example.foodapp.databinding.FragmentHomeMainBinding


class HomeMainFragment : Fragment() {
    lateinit var binding: FragmentHomeMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentHomeMainBinding.inflate(layoutInflater, container, false)

        onRandomMealClick()














        return binding.root
    }



    private fun onRandomMealClick(){
        binding.homeCard.setOnClickListener {
            findNavController().navigate(R.id.action_homeMainFragment_to_mealDetailFragment)
        }
    }

}
