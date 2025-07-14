package com.example.foodapp.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.foodapp.databinding.FragmentSignUpBinding
import com.example.foodapp.utils.ValidationHelper
import com.example.foodapp.viewmodel.LoginViewModel
import com.example.foodapp.viewmodel.LoginViewModelFactory

class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignUpBinding
    private val viewModel: LoginViewModel by activityViewModels {
        LoginViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)

        binding.signUp.setOnClickListener {
            val name = binding.edName.text.toString()
            val email = binding.edEmailAddress.text.toString()
            val password = binding.edPassword.text.toString()

            val nameError = ValidationHelper.validateName(name)
            val emailError = ValidationHelper.validateEmail(email)
            val passwordError = ValidationHelper.validatePassword(password)

            when {
                nameError != null -> Toast.makeText(requireContext(), nameError, Toast.LENGTH_SHORT).show()
                emailError != null -> Toast.makeText(requireContext(), emailError, Toast.LENGTH_SHORT).show()
                passwordError != null -> Toast.makeText(requireContext(), passwordError, Toast.LENGTH_SHORT).show()
                else -> {
                    Toast.makeText(requireContext(), "Registering...", Toast.LENGTH_SHORT).show()
                    viewModel.signUp(email, password)
                }
            }
        }

        return binding.root
    }
}