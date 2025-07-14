package com.example.foodapp.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.foodapp.databinding.FragmentLoginBinding
import com.example.foodapp.viewmodel.LoginViewModel
import com.example.foodapp.viewmodel.LoginViewModelFactory

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val viewModel: LoginViewModel by activityViewModels {
        LoginViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        viewModel.navigateToHome.observe(viewLifecycleOwner) { navigate ->
            if (navigate == true) {
                findNavController().navigate(com.example.foodapp.R.id.action_loginFragment_to_homeFragment)
                viewModel.doneNavigating()
            }
        }

        return binding.root
    }
}
